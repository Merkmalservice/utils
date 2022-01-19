package at.researchstudio.sat.merkmalservice.model.qudt;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.eclipse.rdf4j.model.IRI;

public class Unit {
    private final IRI iri;
    private final IRI prefixIri;
    private Prefix prefix;
    private final Double conversionMultiplier;
    private final Double conversionOffset;
    private final Set<IRI> quantityKindIris;
    private Set<QuantityKind> quantityKinds;
    private final String symbol;
    private final Set<LangString> labels;
    private final IRI scalingOfIri;
    private Unit scalingOf;
    private final IRI dimensionVectorIri;
    private List<FactorUnit> factorUnits = null;

    public Unit(
            IRI iri,
            IRI prefixIri,
            IRI scalingOfIri,
            IRI dimensionVectorIri,
            Double conversionMultiplier,
            Double conversionOffset,
            Set<IRI> quantityKindIris,
            String symbol,
            Set<LangString> labels) {
        this.iri = iri;
        this.prefixIri = prefixIri;
        this.scalingOfIri = scalingOfIri;
        this.dimensionVectorIri = dimensionVectorIri;
        this.conversionMultiplier = conversionMultiplier;
        this.conversionOffset = conversionOffset;
        this.quantityKindIris = new HashSet<>(quantityKindIris);
        this.symbol = symbol;
        this.labels = labels;
    }

    public Unit(
            IRI iri,
            IRI prefixIri,
            IRI scalingOfIri,
            IRI dimensionVectorIri,
            Double conversionMultiplier,
            Double conversionOffset,
            String symbol) {
        this.iri = iri;
        this.prefixIri = prefixIri;
        this.scalingOfIri = scalingOfIri;
        this.dimensionVectorIri = dimensionVectorIri;
        this.conversionMultiplier = conversionMultiplier;
        this.conversionOffset = conversionOffset;
        this.quantityKindIris = new HashSet<>();
        this.symbol = symbol;
        this.labels = new HashSet<>();
    }

    public boolean matches(Collection<Map.Entry<IRI, Integer>> factorUnitSpec) {
        Object[] arr = new Object[factorUnitSpec.size() * 2];
        return matches(
                factorUnitSpec.stream()
                        .flatMap(e -> Stream.of(e.getKey(), e.getValue()))
                        .collect(Collectors.toList())
                        .toArray(arr));
    }

    /**
     * Accepts up to 7 pairs of <Unit, Integer> which are interpreted as factor units and respective
     * exponents.
     *
     * @param factorUnitSpec array of up to 7 <Unit, Integer> pairs
     * @return true if the specified unit/exponent combination identifies this unit.
     *     (overspecification is counted as a match)
     */
    public boolean matches(Object... factorUnitSpec) {
        if (factorUnitSpec.length % 2 != 0) {
            throw new IllegalArgumentException("An even number of arguments is required");
        }
        if (factorUnitSpec.length > 14) {
            throw new IllegalArgumentException(
                    "No more than 10 arguments (7 factor units) supported");
        }
        boolean abortSearch = false;
        List<FactorUnitSelector> selectors = new ArrayList<>();
        for (int i = 0; i < factorUnitSpec.length && !abortSearch; i += 2) {
            Unit requestedUnit;
            requestedUnit = ((Unit) factorUnitSpec[i]);
            Integer requestedExponent = (Integer) factorUnitSpec[i + 1];
            selectors.add(new FactorUnitSelector(requestedUnit, requestedExponent));
        }
        Set<FactorUnitSelection> selections = Set.of(new FactorUnitSelection(selectors));
        selections = match(selections, 1, new ArrayDeque<>(), new ScaleFactor());
        if (selections == null || selections.isEmpty()) return false;
        return selections.stream()
                .filter(FactorUnitSelection::isCompleteMatch)
                .anyMatch(sel -> this.isMatched(sel, new ArrayDeque<>()));
    }

    boolean isMatched(FactorUnitSelection selection, Deque<Unit> checkedPath) {
        checkedPath.push(this);
        boolean match = false;
        if (hasFactorUnits()) {
            match = getFactorUnits().stream().allMatch(fu -> fu.isMatched(selection, checkedPath));
        }
        if (!match && isScaled()) {
            match = getScalingOf().map(u -> u.isMatched(selection, checkedPath)).orElse(false);
        }
        checkedPath.pop();
        return match;
    }

    Set<FactorUnitSelection> match(
            Set<FactorUnitSelection> selections,
            int cumulativeExponent,
            Deque<Unit> matchedPath,
            ScaleFactor scaleFactor) {
        Set<FactorUnitSelection> results = new HashSet<>();
        matchedPath.push(this);
        if (this.getScalingOf().isPresent() && getPrefix().isPresent()) {
            results.addAll(
                    this.getScalingOf()
                            .get()
                            .match(
                                    selections,
                                    cumulativeExponent,
                                    matchedPath,
                                    scaleFactor.multiplyBy(
                                            this.getPrefix().get().getMultiplier())));
        }
        if (hasFactorUnits()) {
            for (FactorUnit factorUnit : factorUnits) {
                selections =
                        factorUnit.match(selections, cumulativeExponent, matchedPath, scaleFactor);
            }
        }
        results.addAll(selections);
        matchedPath.pop();
        return results;
    }

    public boolean hasFactorUnits() {
        return this.factorUnits != null && !this.factorUnits.isEmpty();
    }

    public boolean isScaled() {
        return this.scalingOfIri != null;
    }

    public List<FactorUnit> getLeafFactorUnitsWithCumulativeExponents() {
        return this.factorUnits == null
                ? Collections.emptyList()
                : factorUnits.stream()
                        .flatMap(f -> f.getLeafFactorUnitsWithCumulativeExponents().stream())
                        .collect(Collectors.toList());
    }

    public IRI getIri() {
        return iri;
    }

    public Optional<IRI> getPrefixIri() {
        return Optional.ofNullable(prefixIri);
    }

    public Optional<IRI> getScalingOfIri() {
        return Optional.ofNullable(scalingOfIri);
    }

    public Optional<IRI> getDimensionVectorIri() {
        return Optional.ofNullable(dimensionVectorIri);
    }

    public Optional<Double> getConversionMultiplier() {
        return Optional.ofNullable(conversionMultiplier);
    }

    public Optional<Double> getConversionOffset() {
        return Optional.ofNullable(conversionOffset);
    }

    public Set<IRI> getQuantityKindIris() {
        return Collections.unmodifiableSet(quantityKindIris);
    }

    public Optional<String> getSymbol() {
        return Optional.ofNullable(symbol);
    }

    public void addLabel(LangString langString) {
        this.labels.add(langString);
    }

    public Set<LangString> getLabels() {
        return labels;
    }

    public Optional<LangString> getLabelForLanguageTag(String languageTag) {
        if (languageTag == null) {
            return labels.stream().filter(s -> s.getLanguageTag().isEmpty()).findFirst();
        } else {
            return labels.stream()
                    .filter(s -> languageTag.equals(s.getLanguageTag().orElse(null)))
                    .findFirst();
        }
    }

    public boolean hasLabel(String label) {
        return labels.stream().anyMatch(s -> s.getString().equals(label));
    }

    public void addQuantityKind(IRI quantityKind) {
        this.quantityKindIris.add(quantityKind);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return Objects.equals(iri, unit.iri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iri);
    }

    @Override
    public String toString() {
        return "unit:" + iri.toString().replaceAll(".+/([^/]+)", "$1");
    }

    void setPrefix(Prefix prefix) {
        this.prefix = prefix;
    }

    void setScalingOf(Unit scalingOf) {
        this.scalingOf = scalingOf;
    }

    public Optional<Prefix> getPrefix() {
        return Optional.ofNullable(prefix);
    }

    public Optional<Unit> getScalingOf() {
        return Optional.ofNullable(scalingOf);
    }

    public Set<QuantityKind> getQuantityKinds() {
        return Collections.unmodifiableSet(quantityKinds);
    }

    public List<FactorUnit> getFactorUnits() {
        return factorUnits == null
                ? Collections.emptyList()
                : Collections.unmodifiableList(factorUnits);
    }

    void addQuantityKind(QuantityKind quantityKind) {
        if (this.quantityKinds == null) {
            this.quantityKinds = new HashSet<>();
        }
        this.quantityKinds.add(quantityKind);
    }

    void addFactorUnit(FactorUnit factorUnit) {
        if (this.factorUnits == null) {
            this.factorUnits = new ArrayList<>();
        }
        this.factorUnits.add(factorUnit);
    }
}
