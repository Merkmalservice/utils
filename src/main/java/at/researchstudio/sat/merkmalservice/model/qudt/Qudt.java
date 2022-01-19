package at.researchstudio.sat.merkmalservice.model.qudt;

import static java.util.stream.Collectors.*;

import at.researchstudio.sat.merkmalservice.model.qudt.exception.InconvertibleQuantitiesException;
import at.researchstudio.sat.merkmalservice.model.qudt.exception.NonUniqueResultException;
import at.researchstudio.sat.merkmalservice.model.qudt.exception.NotFoundException;
import at.researchstudio.sat.merkmalservice.model.qudt.exception.QudtInitializationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.impl.LinkedHashModel;
import org.eclipse.rdf4j.model.util.Values;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFParser;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.rio.helpers.StatementCollector;
import org.eclipse.rdf4j.sail.memory.MemoryStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Qudt {
    private static final Logger logger =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final String quantityKindBaseIri = "http://qudt.org/vocab/quantitykind/";
    public static final String unitBaseIri = "http://qudt.org/vocab/unit/";
    public static final String prefixBaseIri = "http://qudt.org/vocab/prefix/";
    private static final Repository qudtRepository;
    private static final String queryLoadUnit;
    private static final String queryLoadQuantityKind;
    private static final String queryLoadPrefix;
    private static final String queryGetScaledUnit;
    private static final String queryGetScaledUnitByLabel;
    private static final String queryGetUnitByLabel;
    private static final String queryLoadFactorUnits;
    private static final Map<IRI, Unit> units;
    private static final Map<IRI, QuantityKind> quantityKinds;
    private static final Map<IRI, Prefix> prefixes;

    /*
     * The constants for units, quantity kinds and prefixes are kept in separate package-protected classes as they are
     * quite numerous and would clutter this class too much. They are made available
     */
    public abstract static class Units
            extends at.researchstudio.sat.merkmalservice.model.qudt.Units {}

    public abstract static class QuantityKinds
            extends at.researchstudio.sat.merkmalservice.model.qudt.QuantityKinds {}

    public abstract static class Prefixes
            extends at.researchstudio.sat.merkmalservice.model.qudt.Prefixes {}

    /*
     * public methods
     */

    public static Unit unitFromLocalname(String localname) {
        return unit(unitIriFromLocalname(localname));
    }

    public static Unit unitFromLabel(String label) {
        try (RepositoryConnection con = qudtRepository.getConnection()) {
            TupleQuery query = con.prepareTupleQuery(queryGetUnitByLabel);
            query.setBinding("unitLabel", Values.literal(label));
            Map<IRI, Unit> matchingUnits = queryUnits(query);
            if (matchingUnits.size() > 1) {
                throw new NonUniqueResultException(
                        String.format(
                                "Expected to get exactly one result for a unit with label %s, but got %d: %s",
                                label,
                                matchingUnits.size(),
                                matchingUnits.values().stream()
                                        .map(u -> u.getIri().toString())
                                        .collect(joining(",", "[", "]"))));
            }
            return units.get(matchingUnits.values().stream().findFirst().get().getIri());
        }
    }

    public static Unit unit(String iriString) {
        return unit(Values.iri(iriString));
    }

    public static Unit unit(IRI iri) {
        Unit unit = units.get(iri);
        if (unit == null) {
            throw new NotFoundException("Unit not found: " + iri);
        }
        return unit;
    }

    public static IRI unitIriFromLocalname(String localname) {
        return Values.iri(unitBaseIri + localname);
    }

    public static Unit scaledUnit(String prefix, String baseUnit) {
        try (RepositoryConnection con = qudtRepository.getConnection()) {
            TupleQuery query = con.prepareTupleQuery(queryGetScaledUnitByLabel);
            query.setBinding("prefixLabel", Values.literal(prefix));
            query.setBinding("baseUnitLabel", Values.literal(baseUnit));
            return units.get(queryUnits(query).values().stream().findFirst().get().getIri());
        }
    }

    public static Unit scaledUnit(Prefix prefix, Unit baseUnit) {
        try (RepositoryConnection con = qudtRepository.getConnection()) {
            TupleQuery query = con.prepareTupleQuery(queryGetScaledUnit);
            query.setBinding("prefix", prefix.getIri());
            query.setBinding("baseUnit", baseUnit.getIri());
            return units.get(queryUnits(query).values().stream().findFirst().get().getIri());
        }
    }

    public static Unit unscale(Unit unit) {
        if (unit.getScalingOfIri().isEmpty()) {
            return unit;
        }
        return unit(unit.getScalingOfIri().get());
    }

    public static List<FactorUnit> factorUnits(String unitIri) {
        return factorUnits(Values.iri(unitIri));
    }

    public static List<FactorUnit> factorUnits(IRI unitIri) {
        return factorUnits(unit(unitIri));
    }

    public static List<FactorUnit> factorUnits(Unit unit) {
        return simplifyFactorUnits(unit.getLeafFactorUnitsWithCumulativeExponents());
    }

    public static List<FactorUnit> simplifyFactorUnits(List<FactorUnit> factorUnits) {
        return new ArrayList<>(
                factorUnits.stream()
                        .collect(
                                Collectors.toMap(
                                        FactorUnit::getKind,
                                        Function.identity(),
                                        FactorUnit::combine))
                        .values());
    }

    public static List<FactorUnit> unscaleFactorUnits(List<FactorUnit> unitFactors) {
        return unitFactors.stream()
                .map(uf -> new FactorUnit(unscale(uf.getUnit()), uf.getExponent()))
                .collect(toList());
    }

    /**
     * Obtains a unit from factor units.
     *
     * @param factorUnits a map containing unit -> exponent entries.
     */
    public static Set<Unit> derivedUnit(List<Map.Entry<Unit, Integer>> factorUnits) {
        Object[] arr = new Object[factorUnits.size() * 2];
        return derivedUnitFromFactors(
                factorUnits.stream()
                        .flatMap(e -> Stream.of(e.getKey(), e.getValue()))
                        .collect(Collectors.toList())
                        .toArray(arr));
    }

    /**
     * Vararg method, must be an even number of arguments, always alternating types of
     * Unit|IRI|String and Integer.
     *
     * @param factorUnitSpecs
     */
    static Set<Unit> derivedUnitFromFactors(final Object... factorUnitSpecs) {
        if (factorUnitSpecs.length % 2 != 0) {
            throw new IllegalArgumentException("An even number of arguments is required");
        }
        if (factorUnitSpecs.length > 14) {
            throw new IllegalArgumentException(
                    "No more than 14 arguments (7 factor units) supported");
        }
        List<Unit> matchingUnits =
                units.values().stream()
                        .filter(d -> d.matches(factorUnitSpecs))
                        .collect(Collectors.toList());
        /*
        if (matchingUnits.isEmpty()) {
            // try unscaling (except for GM - unscales to KiloGM), and try again
            Object[] scaledFactorUnits = new Object[factorUnitSpecs.length];
            double multiplier = 1;
            for (int i = 0; i < factorUnitSpecs.length; i++) {
                if (i % 2 == 0) {
                    Map.Entry<Unit, Double> scaled = scaleToBaseUnit((Unit) factorUnitSpecs[i]);
                    multiplier /= scaled.getValue();
                    scaledFactorUnits[i] = scaled.getKey();
                } else {
                    scaledFactorUnits[i] = factorUnitSpecs[i];
                }
            }
            final double unitMulitiplier = multiplier;
            Optional<Prefix> scalingPrefix =
                    prefixes.values().stream()
                            .filter(p -> p.getMultiplier() == unitMulitiplier)
                            .findFirst();
            if (scalingPrefix.isPresent()) {
                matchingUnits =
                        derivedUnits.values().stream()
                                .filter(d -> d.matches(scaledFactorUnits))
                                .map(u -> scale(unit(u.getUnitIri()), scalingPrefix.get()))
                                .collect(Collectors.toList());
            }
        }*/
        if (matchingUnits.isEmpty()) {
            throw new NotFoundException(
                    "No derived unit found for factors " + Arrays.toString(factorUnitSpecs));
        }
        return new HashSet<>(matchingUnits);
    }

    private static Unit scale(Unit unit, Prefix prefix) {
        Optional<Unit> scaled =
                units.values().stream()
                        .filter(
                                u ->
                                        unit.getIri().equals(u.getScalingOfIri().orElse(null))
                                                && prefix.getIri()
                                                        .equals(u.getPrefixIri().orElse(null)))
                        .findFirst();
        return scaled.orElseThrow(
                () ->
                        new NotFoundException(
                                String.format(
                                        "Qudt does not contain the unit %s, scaled with prefix %s",
                                        unit, prefix)));
    }

    // returns the base unit and the factor we had to scale by
    private static Map.Entry<Unit, Double> scaleToBaseUnit(Unit unit) {
        if (unit.getScalingOfIri().isPresent() && unit.getPrefixIri().isPresent()) {
            Unit base = unit(unit.getScalingOfIri().get());
            Prefix prefx = prefix(unit.getPrefixIri().get());
            if (Units.GM.getIri().equals(base.getIri())) {
                return Map.entry(Units.KiloGM, prefx.getMultiplier() * 1000);
            }
            return Map.entry(base, prefx.getMultiplier());
        }
        if (Units.GM.getIri().equals(unit.getIri())) {
            return Map.entry(Units.KiloGM, 1000d);
        }
        return Map.entry(unit, 1d);
    }

    public static Set<Unit> derivedUnit(Unit baseUnit, int exponent) {
        return derivedUnitFromFactors(baseUnit, exponent);
    }

    public static Set<Unit> derivedUnit(IRI baseUnit, int exponent) {
        return derivedUnitFromFactors(unit(baseUnit), exponent);
    }

    public static Set<Unit> derivedUnit(String baseUnit, int exponent) {
        return derivedUnitFromFactors(unit(baseUnit), exponent);
    }

    public static Set<Unit> derivedUnit(
            Unit baseUnit1, int exponent1, Unit baseUnit2, int exponent2) {
        return derivedUnitFromFactors(baseUnit1, exponent1, baseUnit2, exponent2);
    }

    public static Set<Unit> derivedUnit(
            Unit baseUnit1,
            int exponent1,
            Unit baseUnit2,
            int exponent2,
            Unit baseUnit3,
            int exponent3) {
        return derivedUnitFromFactors(
                baseUnit1, exponent1, baseUnit2, exponent2, baseUnit3, exponent3);
    }

    public static Set<Unit> derivedUnit(
            IRI baseUnit1,
            int exponent1,
            IRI baseUnit2,
            int exponent2,
            IRI baseUnit3,
            int exponent3) {
        return derivedUnitFromFactors(
                unit(baseUnit1), exponent1, unit(baseUnit2), exponent2, unit(baseUnit3), exponent3);
    }

    public static Set<Unit> derivedUnit(
            String baseUnit1,
            int exponent1,
            String baseUnit2,
            int exponent2,
            String baseUnit3,
            int exponent3) {
        return derivedUnitFromFactors(
                unit(baseUnit1), exponent1, unit(baseUnit2), exponent2, unit(baseUnit3), exponent3);
    }

    public static Set<Unit> derivedUnit(
            Unit baseUnit1,
            int exponent1,
            Unit baseUnit2,
            int exponent2,
            Unit baseUnit3,
            int exponent3,
            Unit baseUnit4,
            int exponent4) {
        return derivedUnitFromFactors(
                baseUnit1, exponent1, baseUnit2, exponent2, baseUnit3, exponent3, baseUnit4,
                exponent4);
    }

    public static Set<Unit> derivedUnit(
            IRI baseUnit1,
            int exponent1,
            IRI baseUnit2,
            int exponent2,
            IRI baseUnit3,
            int exponent3,
            IRI baseUnit4,
            int exponent4) {
        return derivedUnitFromFactors(
                unit(baseUnit1),
                exponent1,
                unit(baseUnit2),
                exponent2,
                unit(baseUnit3),
                exponent3,
                unit(baseUnit4),
                exponent4);
    }

    public static Set<Unit> derivedUnit(
            String baseUnit1,
            int exponent1,
            String baseUnit2,
            int exponent2,
            String baseUnit3,
            int exponent3,
            String baseUnit4,
            int exponent4) {
        return derivedUnitFromFactors(
                unit(baseUnit1),
                exponent1,
                unit(baseUnit2),
                exponent2,
                unit(baseUnit3),
                exponent3,
                unit(baseUnit4),
                exponent4);
    }

    public static Set<Unit> derivedUnit(
            Unit baseUnit1,
            int exponent1,
            Unit baseUnit2,
            int exponent2,
            Unit baseUnit3,
            int exponent3,
            Unit baseUnit4,
            int exponent4,
            Unit baseUnit5,
            int exponent5) {
        return derivedUnitFromFactors(
                baseUnit1, exponent1, baseUnit2, exponent2, baseUnit3, exponent3, baseUnit4,
                exponent4, baseUnit5, exponent5);
    }

    public static Set<Unit> derivedUnit(
            IRI baseUnit1,
            int exponent1,
            IRI baseUnit2,
            int exponent2,
            IRI baseUnit3,
            int exponent3,
            IRI baseUnit4,
            int exponent4,
            IRI baseUnit5,
            int exponent5) {
        return derivedUnitFromFactors(
                unit(baseUnit1),
                exponent1,
                unit(baseUnit2),
                exponent2,
                unit(baseUnit3),
                exponent3,
                unit(baseUnit4),
                exponent4,
                unit(baseUnit5),
                exponent5);
    }

    public static Set<Unit> derivedUnit(
            String baseUnit1,
            int exponent1,
            String baseUnit2,
            int exponent2,
            String baseUnit3,
            int exponent3,
            String baseUnit4,
            int exponent4,
            String baseUnit5,
            int exponent5) {
        return derivedUnitFromFactors(
                unit(baseUnit1),
                exponent1,
                unit(baseUnit2),
                exponent2,
                unit(baseUnit3),
                exponent3,
                unit(baseUnit4),
                exponent4,
                unit(baseUnit5),
                exponent5);
    }

    public static Set<Unit> derivedUnit(
            Unit baseUnit1,
            int exponent1,
            Unit baseUnit2,
            int exponent2,
            Unit baseUnit3,
            int exponent3,
            Unit baseUnit4,
            int exponent4,
            Unit baseUnit5,
            int exponent5,
            Unit baseUnit6,
            int exponent6) {
        return derivedUnitFromFactors(
                baseUnit1, exponent1, baseUnit2, exponent2, baseUnit3, exponent3, baseUnit4,
                exponent4, baseUnit5, exponent5, baseUnit6, exponent6);
    }

    public static Set<Unit> derivedUnit(
            IRI baseUnit1,
            int exponent1,
            IRI baseUnit2,
            int exponent2,
            IRI baseUnit3,
            int exponent3,
            IRI baseUnit4,
            int exponent4,
            IRI baseUnit5,
            int exponent5,
            IRI baseUnit6,
            int exponent6) {
        return derivedUnitFromFactors(
                unit(baseUnit1),
                exponent1,
                unit(baseUnit2),
                exponent2,
                unit(baseUnit3),
                exponent3,
                unit(baseUnit4),
                exponent4,
                unit(baseUnit5),
                exponent5,
                unit(baseUnit6),
                exponent6);
    }

    public static Set<Unit> derivedUnit(
            String baseUnit1,
            int exponent1,
            String baseUnit2,
            int exponent2,
            String baseUnit3,
            int exponent3,
            String baseUnit4,
            int exponent4,
            String baseUnit5,
            int exponent5,
            String baseUnit6,
            int exponent6) {
        return derivedUnitFromFactors(
                unit(baseUnit1),
                exponent1,
                unit(baseUnit2),
                exponent2,
                unit(baseUnit3),
                exponent3,
                unit(baseUnit4),
                exponent4,
                unit(baseUnit5),
                exponent5,
                unit(baseUnit6),
                exponent6);
    }

    public static QuantityKind quantityKindFromLocalname(String localname) {
        return quantityKind(quantityKindIriFromLocalname(localname));
    }

    public static QuantityKind quantityKind(String iriString) {
        return quantityKind(Values.iri(iriString));
    }

    public static QuantityKind quantityKind(IRI iri) {
        QuantityKind quantityKind = quantityKinds.get(iri);
        if (quantityKind == null) {
            throw new NotFoundException("QuantityKind not found: " + iri);
        }
        return quantityKind;
    }

    public static Set<QuantityKind> quantityKinds(Unit unit) {
        return unit.getQuantityKindIris().stream()
                .map(Qudt::quantityKind)
                .collect(Collectors.toSet());
    }

    public static Set<QuantityKind> quantityKindsBroad(Unit unit) {
        Set<QuantityKind> current = quantityKinds(unit);
        Set<QuantityKind> result = new HashSet<>();
        result.addAll(current);
        while (!current.isEmpty()) {
            current =
                    current.stream()
                            .flatMap(qk -> qk.getBroaderQuantityKinds().stream())
                            .map(Qudt::quantityKind)
                            .collect(Collectors.toSet());
            result.addAll(current);
        }
        return result;
    }

    public static IRI quantityKindIriFromLocalname(String localname) {
        return Values.iri(quantityKindBaseIri + localname);
    }

    public static IRI prefixIriFromLocalname(String localname) {
        return Values.iri(prefixBaseIri + localname);
    }

    public static Prefix prefixFromLocalname(String localname) {
        return prefix(prefixIriFromLocalname(localname));
    }

    public static Prefix prefix(String iriString) {
        return prefix(Values.iri(iriString));
    }

    public static Prefix prefix(IRI iri) {
        Prefix prefix = prefixes.get(iri);
        if (prefix == null) {
            throw new NotFoundException("Prefix not found: " + iri);
        }
        return prefix;
    }

    public static QuantityValue quantityValue(double value, String unitIriString) {
        return new QuantityValue(value, unit(unitIriString));
    }

    public static QuantityValue quantityValue(double value, IRI unit) {
        return new QuantityValue(value, unit(unit));
    }

    public static QuantityValue quantityValue(double value, Unit unit) {
        return new QuantityValue(value, unit);
    }

    public static QuantityValue convert(QuantityValue from, Unit toUnit) {
        return convert(from.getValue(), from.getUnit(), toUnit);
    }

    public static QuantityValue convert(QuantityValue from, String toUnitIriString) {
        return convert(from, unit(toUnitIriString));
    }

    public static QuantityValue convert(QuantityValue from, IRI toUnitIri)
            throws InconvertibleQuantitiesException {
        Unit toUnit = unit(toUnitIri);
        return convert(from.getValue(), from.getUnit(), toUnit);
    }

    public static QuantityValue convert(
            double fromValue, String fromUnitString, String toUnitString) {
        return convert(fromValue, unit(fromUnitString), unit(toUnitString));
    }

    public static QuantityValue convert(double fromValue, IRI fromUnit, IRI toUnit) {
        return convert(fromValue, unit(fromUnit), unit(toUnit));
    }

    public static QuantityValue convert(double fromValue, Unit fromUnit, Unit toUnit) {
        if (fromUnit.equals(toUnit)) {
            return new QuantityValue(fromValue, toUnit);
        }
        Set<IRI> fromDimensionVectors =
                fromUnit.getQuantityKindIris().stream()
                        .map(Qudt::quantityKind)
                        .map(QuantityKind::getDimensionVector)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(toSet());
        Set<IRI> toDimensionVectors =
                toUnit.getQuantityKindIris().stream()
                        .map(Qudt::quantityKind)
                        .map(QuantityKind::getDimensionVector)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(toSet());
        boolean isConvertible =
                fromDimensionVectors.stream().anyMatch(v -> toDimensionVectors.contains(v));
        if (!isConvertible) {
            throw new InconvertibleQuantitiesException(
                    String.format(
                            "Cannot convert from %s to %s",
                            fromUnit.getLabels(), toUnit.getLabels()));
        }
        double fromOffset = fromUnit.getConversionOffset().orElse(0.0);
        double fromMultiplier = fromUnit.getConversionMultiplier().orElse(1.0);
        double toOffset = toUnit.getConversionOffset().orElse(0.0);
        double toMultiplier = toUnit.getConversionMultiplier().orElse(1.0);
        double converted = (fromValue + fromOffset) / fromMultiplier / toMultiplier - toOffset;
        return new QuantityValue(converted, toUnit);
    }

    static {
        Model m = loadQudtModel();
        qudtRepository = new SailRepository(new MemoryStore());
        try (RepositoryConnection con = qudtRepository.getConnection()) {
            con.add(m);
            con.commit();
        }
        queryLoadUnit = loadQuery("/qudt/query/unit.rq");
        queryLoadQuantityKind = loadQuery("/qudt/query/quantitykind.rq");
        queryLoadPrefix = loadQuery("/qudt/query/prefix.rq");
        queryGetScaledUnit = loadQuery("/qudt/query/scaled-unit.rq");
        queryGetScaledUnitByLabel = loadQuery("/qudt/query/scaled-unit-by-label.rq");
        queryGetUnitByLabel = loadQuery("/qudt/query/unit-by-label.rq");
        queryLoadFactorUnits = loadQuery("/qudt/query/factor-units.rq");
        prefixes = loadPrefixes();
        units = loadUnits();
        quantityKinds = loadQuantityKinds();
        loadFactorUnits();
        connectObjects();
    }

    private static void connectObjects() {
        for (Unit unit : units.values()) {
            unit.getPrefixIri().ifPresent(iri -> unit.setPrefix(prefixes.get(iri)));
            unit.getScalingOfIri().ifPresent(iri -> unit.setScalingOf(units.get(iri)));
            unit.getQuantityKindIris().stream()
                    .forEach(iri -> unit.addQuantityKind(quantityKinds.get(iri)));
        }
    }

    static Unit loadUnit(String localname) {
        return loadUnit(Values.iri(unitBaseIri + localname));
    }

    static Unit loadUnit(IRI unitIri) {
        Objects.requireNonNull(unitIri);
        try (RepositoryConnection con = qudtRepository.getConnection()) {
            TupleQuery query = con.prepareTupleQuery(queryLoadUnit);
            query.setBinding("unit", unitIri);
            try (TupleQueryResult result = query.evaluate()) {
                Iterator<BindingSet> solutions = result.iterator();
                Unit unit = null;
                while (solutions.hasNext()) {
                    BindingSet bs = solutions.next();
                    if (unit == null) {
                        unit = makeUnit(bs);
                    }
                    unit.addQuantityKind((IRI) bs.getBinding("quantityKind").getValue());
                }
                if (unit == null) {
                    throw new NotFoundException("No unit found: " + unitIri);
                }
                return unit;
            }
        }
    }

    static Map<IRI, Unit> loadUnits() {
        try (RepositoryConnection con = qudtRepository.getConnection()) {
            TupleQuery query = con.prepareTupleQuery(queryLoadUnit);
            return queryUnits(query);
        }
    }

    private static Map<IRI, Unit> queryUnits(TupleQuery query) {
        try (TupleQueryResult result = query.evaluate()) {
            Iterator<BindingSet> solutions = result.iterator();
            Unit unit = null;
            Map<IRI, Unit> results = new HashMap<>();
            while (solutions.hasNext()) {
                BindingSet bs = solutions.next();
                IRI currentUnitIri = (IRI) bs.getBinding("unit").getValue();
                if (unit != null && !currentUnitIri.equals(unit.getIri())) {
                    results.put(unit.getIri(), unit);
                    unit = null;
                }
                if (unit == null) {
                    unit = makeUnit(bs);
                }
                if (bs.hasBinding("quantityKind")) {
                    unit.addQuantityKind((IRI) bs.getBinding("quantityKind").getValue());
                }
                if (bs.hasBinding("label")) {
                    Value val = bs.getValue("label");
                    Literal lit = (Literal) val;
                    unit.addLabel(new LangString(lit.getLabel(), lit.getLanguage().orElse(null)));
                }
            }
            if (unit != null) {
                results.put(unit.getIri(), unit);
            }
            if (results.isEmpty()) {
                throw new NotFoundException(
                        "No units found for unit query with bindings "
                                + bindingsToString(query.getBindings()));
            }
            return results;
        }
    }

    private static String bindingsToString(BindingSet bindingSet) {
        StringBuilder sb = new StringBuilder();
        return StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(
                                bindingSet.iterator(), Spliterator.IMMUTABLE),
                        false)
                .map(b -> b.getName() + "->" + b.getValue())
                .collect(Collectors.joining(", ", "[ ", " ]"));
    }

    static Map<IRI, QuantityKind> loadQuantityKinds() {
        try (RepositoryConnection con = qudtRepository.getConnection()) {
            TupleQuery query = con.prepareTupleQuery(queryLoadQuantityKind);
            try (TupleQueryResult result = query.evaluate()) {
                Iterator<BindingSet> solutions = result.iterator();
                QuantityKind quantityKind = null;
                Map<IRI, QuantityKind> results = new HashMap<>();
                while (solutions.hasNext()) {
                    BindingSet bs = solutions.next();
                    IRI currentQuantityKindIri = (IRI) bs.getBinding("quantityKind").getValue();
                    if (quantityKind != null
                            && !currentQuantityKindIri.equals(quantityKind.getIri())) {
                        results.put(quantityKind.getIri(), quantityKind);
                        quantityKind = null;
                    }
                    if (quantityKind == null) {
                        quantityKind = makeQuantityKind(bs);
                    }
                    if (bs.hasBinding("label")) {
                        Value val = bs.getValue("label");
                        Literal lit = (Literal) val;
                        quantityKind.addLabel(
                                new LangString(lit.getLabel(), lit.getLanguage().orElse(null)));
                    }
                    if (bs.hasBinding("broaderQuantityKind")) {
                        IRI val = (IRI) bs.getValue("broaderQuantityKind");
                        quantityKind.addBroaderQuantityKind(val);
                    }
                    if (bs.hasBinding("applicableUnit")) {
                        quantityKind.addApplicableUnit(
                                (IRI) bs.getBinding("applicableUnit").getValue());
                    }
                }
                if (quantityKind != null) {
                    results.put(quantityKind.getIri(), quantityKind);
                }
                return results;
            }
        }
    }

    static Map<IRI, Prefix> loadPrefixes() {
        try (RepositoryConnection con = qudtRepository.getConnection()) {
            TupleQuery query = con.prepareTupleQuery(queryLoadPrefix);
            try (TupleQueryResult result = query.evaluate()) {
                Iterator<BindingSet> solutions = result.iterator();
                Prefix prefix = null;
                Map<IRI, Prefix> results = new HashMap<>();
                while (solutions.hasNext()) {
                    BindingSet bs = solutions.next();
                    IRI currentQuantityKindIri = (IRI) bs.getBinding("prefix").getValue();
                    if (prefix != null && !currentQuantityKindIri.equals(prefix.getIri())) {
                        results.put(prefix.getIri(), prefix);
                        prefix = null;
                    }
                    if (prefix == null) {
                        prefix = makePrefix(bs);
                    }
                    if (bs.hasBinding("label")) {
                        Value val = bs.getValue("label");
                        Literal lit = (Literal) val;
                        prefix.addLabel(
                                new LangString(lit.getLabel(), lit.getLanguage().orElse(null)));
                    }
                }
                if (prefix != null) {
                    results.put(prefix.getIri(), prefix);
                }
                return results;
            }
        }
    }

    static void loadFactorUnits() {
        try (RepositoryConnection con = qudtRepository.getConnection()) {
            TupleQuery query = con.prepareTupleQuery(queryLoadFactorUnits);
            try (TupleQueryResult result = query.evaluate()) {
                Iterator<BindingSet> solutions = result.iterator();
                while (solutions.hasNext()) {
                    BindingSet bs = solutions.next();
                    IRI currentDerivedUnitIri = (IRI) bs.getBinding("derivedUnit").getValue();
                    Unit derivedUnit = units.get(currentDerivedUnitIri);
                    if (derivedUnit == null) {
                        throw new IllegalArgumentException(
                                "found a factor of unknown unit " + currentDerivedUnitIri);
                    }
                    Optional<FactorUnit> factorUnit = makeFactorUnit(bs);
                    if (factorUnit.isPresent()) {
                        derivedUnit.addFactorUnit(factorUnit.get());
                    }
                }
            }
        }
    }

    private static String loadQuery(String queryFile) {
        try (InputStream in = Qudt.class.getResourceAsStream(queryFile)) {
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new QudtInitializationException("Error loading qudt query ", e);
        }
    }

    private static Model loadQudtModel() {
        Model model = new LinkedHashModel();
        RDFParser parser = Rio.createParser(RDFFormat.TURTLE);
        parser.setRDFHandler(new StatementCollector(model));
        loadTtlFile("/qudt/qudt-quantitykind.ttl", parser);
        loadTtlFile("/qudt/qudt-unit.ttl", parser);
        loadTtlFile("/qudt/qudt-prefix.ttl", parser);
        return model;
    }

    private static void loadTtlFile(String ttlFile, RDFParser parser) {
        try (InputStream in = Qudt.class.getResourceAsStream(ttlFile)) {
            parser.parse(in);
        } catch (IOException e) {
            throw new QudtInitializationException("Error loading qudt data from " + ttlFile, e);
        }
    }

    private static Unit makeUnit(BindingSet bs) {
        return new Unit(
                (IRI) bs.getBinding("unit").getValue(),
                bs.hasBinding("prefix") ? (IRI) bs.getBinding("prefix").getValue() : null,
                bs.hasBinding("scalingOf") ? (IRI) bs.getValue("scalingOf") : null,
                bs.hasBinding("dimensionVector") ? (IRI) bs.getValue("dimensionVector") : null,
                bs.hasBinding("conversionMultiplier")
                        ? ((Literal) bs.getBinding("conversionMultiplier").getValue()).doubleValue()
                        : null,
                bs.hasBinding("conversionOffset")
                        ? ((Literal) bs.getBinding("conversionOffset").getValue()).doubleValue()
                        : null,
                bs.hasBinding("symbol") ? bs.getBinding("symbol").getValue().stringValue() : null);
    }

    private static QuantityKind makeQuantityKind(BindingSet bs) {
        return new QuantityKind(
                (IRI) bs.getBinding("quantityKind").getValue(),
                bs.hasBinding("dimensionVector")
                        ? (IRI) bs.getBinding("dimensionVector").getValue()
                        : null,
                bs.hasBinding("symbol") ? bs.getBinding("symbol").getValue().stringValue() : null);
    }

    private static Prefix makePrefix(BindingSet bs) {
        return new Prefix(
                (IRI) bs.getBinding("prefix").getValue(),
                ((Literal) bs.getBinding("prefixMultiplier").getValue()).doubleValue(),
                bs.getBinding("symbol").getValue().stringValue(),
                bs.hasBinding("ucumCode")
                        ? bs.getBinding("ucumCode").getValue().stringValue()
                        : null);
    }

    private static Optional<FactorUnit> makeFactorUnit(BindingSet bs) {
        IRI unitIri = (IRI) bs.getValue("factorUnit");
        Unit unit = units.get(unitIri);
        if (unit == null) {
            logger.info("Found factor unit for inexistent unit {}", unitIri);
            return Optional.empty();
        }
        return Optional.of(new FactorUnit(unit, ((Literal) bs.getValue("exponent")).intValue()));
    }
}
