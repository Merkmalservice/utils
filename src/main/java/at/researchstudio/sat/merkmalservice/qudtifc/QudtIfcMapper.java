package at.researchstudio.sat.merkmalservice.qudtifc;

import at.researchstudio.sat.merkmalservice.model.ifc.IfcDerivedUnit;
import at.researchstudio.sat.merkmalservice.model.ifc.IfcDerivedUnitElement;
import at.researchstudio.sat.merkmalservice.model.ifc.IfcSIUnit;
import at.researchstudio.sat.merkmalservice.model.ifc.IfcUnit;
import at.researchstudio.sat.merkmalservice.model.qudt.*;
import at.researchstudio.sat.merkmalservice.vocab.ifc.IfcPropertyType;
import at.researchstudio.sat.merkmalservice.vocab.ifc.IfcUnitMeasure;
import at.researchstudio.sat.merkmalservice.vocab.ifc.IfcUnitMeasurePrefix;
import at.researchstudio.sat.merkmalservice.vocab.ifc.IfcUnitType;
import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.eclipse.rdf4j.model.IRI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.stream.Collectors.toSet;

public class QudtIfcMapper {
    private static final Logger logger =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final Map<IfcUnitType, Set<IRI>> ifcUnitTypeToDimensionVectors = new HashMap<>();
    private static final Map<IRI, Set<IfcUnitType>> dimensionVectorToIfcUnitTypes = new HashMap<>();
    private static final Map<QuantityKind, Set<IfcUnitType>> quantityKindToIfcUnitTypes =
            new HashMap<>();
    private static final Map<IfcUnitType, Set<QuantityKind>> ifcUnitTypeToQuantityKinds =
            new HashMap<>();
    private static final Map<IfcUnitMeasure, Unit> ifcUnitMeasureToUnit = new HashMap<>();
    private static final Map<Unit, IfcUnitMeasure> unitToIfcUnitMeasure = new HashMap<>();
    private static final Map<IfcUnitMeasurePrefix, Prefix> ifcUnitMeasurePrefixToPrefix =
            new HashMap<>();
    private static final Map<Prefix, IfcUnitMeasurePrefix> prefixToIfcUnitMeasurePrefix =
            new HashMap<>();

    static {
        try {
            // entries for IfcUnitEnum
            associate(IfcUnitType.ABSORBEDDOSEUNIT, Qudt.Units.GRAY);
            associate(IfcUnitType.AMOUNTOFSUBSTANCEUNIT, Qudt.Units.MOL);
            associate(IfcUnitType.AREAUNIT, Qudt.Units.M2);
            associate(IfcUnitType.DOSEEQUIVALENTUNIT, Qudt.Units.SV);
            associate(IfcUnitType.ELECTRICCAPACITANCEUNIT, Qudt.Units.F);
            associate(IfcUnitType.ELECTRICCHARGEUNIT, Qudt.Units.C);
            associate(IfcUnitType.ELECTRICCONDUCTANCEUNIT, Qudt.Units.S);
            associate(IfcUnitType.ELECTRICCURRENTUNIT, Qudt.Units.A);
            associate(IfcUnitType.ELECTRICRESISTANCEUNIT, Qudt.Units.OHM);
            associate(IfcUnitType.ELECTRICVOLTAGEUNIT, Qudt.Units.V);
            associate(IfcUnitType.ENERGYUNIT, Qudt.Units.J);
            associate(IfcUnitType.FORCEUNIT, Qudt.Units.N);
            associate(IfcUnitType.FREQUENCYUNIT, Qudt.Units.HZ);
            associate(IfcUnitType.ILLUMINANCEUNIT, Qudt.Units.LUX);
            associate(IfcUnitType.INDUCTANCEUNIT, Qudt.Units.H);
            associate(IfcUnitType.LENGTHUNIT, Qudt.Units.M);
            associate(IfcUnitType.LUMINOUSFLUXUNIT, Qudt.Units.LM);
            associate(IfcUnitType.LUMINOUSINTENSITYUNIT, Qudt.Units.CD);
            associate(IfcUnitType.MAGNETICFLUXDENSITYUNIT, Qudt.Units.T);
            associate(IfcUnitType.MAGNETICFLUXUNIT, Qudt.Units.WB);
            associate(IfcUnitType.MASSUNIT, Qudt.Units.KiloGM);
            associate(IfcUnitType.PLANEANGLEUNIT, Qudt.Units.RAD);
            associate(IfcUnitType.POWERUNIT, Qudt.Units.W);
            associate(IfcUnitType.PRESSUREUNIT, Qudt.Units.PA);
            associate(IfcUnitType.RADIOACTIVITYUNIT, Qudt.Units.BQ);
            associate(IfcUnitType.SOLIDANGLEUNIT, Qudt.Units.SR);
            associate(IfcUnitType.THERMODYNAMICTEMPERATUREUNIT, Qudt.Units.K);
            associate(IfcUnitType.TIMEUNIT, Qudt.Units.SEC);
            associate(IfcUnitType.VOLUMEUNIT, Qudt.Units.M3);
            // entries for IfcDerivedUnitEnu;
            associate(IfcUnitType.ACCELERATIONUNIT, Qudt.Units.M__PER__SEC2);
            associate(IfcUnitType.ANGULARVELOCITYUNIT, Qudt.Units.RAD__PER__SEC);
            associate(IfcUnitType.AREADENSITYUNIT, Qudt.Units.KiloGM__PER__M2);
            associate(IfcUnitType.CURVATUREUNIT, Qudt.Units.RAD__PER__M);
            associate(IfcUnitType.DYNAMICVISCOSITYUNIT, Qudt.Units.PA__SEC);
            associate(IfcUnitType.HEATFLUXDENSITYUNIT, Qudt.Units.W__PER__M2);
            associate(IfcUnitType.HEATINGVALUEUNIT, Qudt.Units.J__PER__KiloGM);
            associate(IfcUnitType.INTEGERCOUNTRATEUNIT, Qudt.Units.PER__SEC);
            associate(IfcUnitType.IONCONCENTRATIONUNIT, Qudt.Units.GM__PER__L);
            associate(IfcUnitType.ISOTHERMALMOISTURECAPACITYUNIT, Qudt.Units.M3__PER__KiloGM);
            associate(IfcUnitType.KINEMATICVISCOSITYUNIT, Qudt.Units.M2__PER__SEC);
            associate(IfcUnitType.LINEARFORCEUNIT, Qudt.Units.N__PER__M);
            associate(IfcUnitType.LINEARMOMENTUNIT, Qudt.Units.N__M__PER__M);
            associate(IfcUnitType.LINEARVELOCITYUNIT, Qudt.Units.M__PER__SEC);
            associate(IfcUnitType.MASSDENSITYUNIT, Qudt.Units.KiloGM__PER__M3);
            associate(IfcUnitType.MASSFLOWRATEUNIT, Qudt.Units.KiloGM__PER__SEC);
            associate(IfcUnitType.MASSPERLENGTHUNIT, Qudt.Units.KiloGM__PER__M);
            associate(IfcUnitType.MODULUSOFELASTICITYUNIT, Qudt.Units.N__PER__M2);
            associate(IfcUnitType.MODULUSOFLINEARSUBGRADEREACTIONUNIT, Qudt.Units.N__PER__M2);
            associate(IfcUnitType.MODULUSOFSUBGRADEREACTIONUNIT, Qudt.Units.N__PER__M3);
            associate(IfcUnitType.MOISTUREDIFFUSIVITYUNIT, Qudt.Units.M3__PER__SEC);
            associate(IfcUnitType.MOLECULARWEIGHTUNIT, Qudt.Units.GM__PER__MOL);
            associate(IfcUnitType.MOMENTOFINERTIAUNIT, Qudt.Units.M4);
            associate(IfcUnitType.PHUNIT, Qudt.Units.PH);
            associate(IfcUnitType.PLANARFORCEUNIT, Qudt.Units.N__PER__M2);
            associate(IfcUnitType.ROTATIONALFREQUENCYUNIT, Qudt.Units.HZ);
            associate(IfcUnitType.ROTATIONALMASSUNIT, Qudt.Units.KiloGM__PER__M2);
            associate(IfcUnitType.ROTATIONALSTIFFNESSUNIT, Qudt.Units.N__M__PER__RAD);
            associate(IfcUnitType.SECTIONMODULUSUNIT, Qudt.Units.M3);
            associate(IfcUnitType.SHEARMODULUSUNIT, Qudt.Units.N__PER__M2);
            associate(IfcUnitType.SOUNDPOWERLEVELUNIT, Qudt.Units.W);
            associate(IfcUnitType.SPECIFICHEATCAPACITYUNIT, Qudt.Units.J__PER__KiloGM__K);
            associate(IfcUnitType.TEMPERATUREGRADIENTUNIT, Qudt.Units.K__PER__M);
            associate(IfcUnitType.TEMPERATURERATEOFCHANGEUNIT, Qudt.Units.K__PER__SEC);
            associate(IfcUnitType.THERMALADMITTANCEUNIT, Qudt.Units.W__PER__M2__K);
            associate(IfcUnitType.THERMALCONDUCTANCEUNIT, Qudt.Units.W__PER__K); // not sure
            associate(IfcUnitType.THERMALEXPANSIONCOEFFICIENTUNIT, Qudt.Units.PER__K);
            associate(IfcUnitType.THERMALRESISTANCEUNIT, Qudt.Units.M2__K__PER__W);
            associate(IfcUnitType.THERMALTRANSMITTANCEUNIT, Qudt.Units.W__PER__M2__K);
            associate(IfcUnitType.TORQUEUNIT, Qudt.Units.N__M);
            associate(IfcUnitType.VOLUMETRICFLOWRATEUNIT, Qudt.Units.M2__PER__SEC);
            associate(IfcUnitType.WARPINGMOMENTUNIT, Qudt.Units.N__PER__M2);
            // not sure what to use here:
            // associate(IfcUnitType.WARPINGCONSTANTUNIT, Qudt.Units.M_6); //unit does not exist
            // associate(IfcUnitType.VAPORPERMEABILITYUNIT, Qudt.Units.KG__PER__SEC__M__PA); //unit
            // does
            // not exist
            // getEntry(, IfcUnitType.COMPOUNDPLANEANGLEUNIT), // not sure if that is even a thing
            // in
            // qudt
            // getEntry(Qudt.Units., IfcUnitType.THERMALCONDUCTANCEUNIT), //not sure which unit to
            // take
            // getEntry(Qudt.Units.N__PER__M, IfcUnitType.LINEARSTIFFNESSUNIT),
            // getEntry(Qudt.Units.CD__PER__LM, IfcUnitType.LUMINOUSINTENSITYDISTRIBUTIONUNIT),
            // //unit
            // does not exist
            // getEntry(Qudt.Units.N__M__PER__M__RAD,
            // IfcUnitType.MODULUSOFROTATIONALSUBGRADEREACTIONUNIT), //unit does not exist
            // getEntry(Qudt.Units.M, IfcUnitType.SECTIONAREAINTEGRALUNIT), //unit does not exist
            // getEntry(Qudt.Units.W__PER__W, IfcUnitType.SOUNDPOWERUNIT), //unit does not exist
            // getEntry(Qudt.Units.PA__PER__PA, IfcUnitType.SOUNDPRESSURELEVELUNIT),//unit does not
            // exist
            associateUnitName(IfcUnitMeasure.AMPERE, Qudt.Units.A);
            associateUnitName(IfcUnitMeasure.BECQUEREL, Qudt.Units.BQ);
            associateUnitName(IfcUnitMeasure.CANDELA, Qudt.Units.CD);
            associateUnitName(IfcUnitMeasure.COULOMB, Qudt.Units.C);
            associateUnitName(IfcUnitMeasure.CUBIC_METRE, Qudt.Units.M3);
            associateUnitName(IfcUnitMeasure.DEGREE_CELSIUS, Qudt.Units.DEG_C);
            associateUnitName(IfcUnitMeasure.FARAD, Qudt.Units.F);
            associateUnitName(IfcUnitMeasure.GRAM, Qudt.Units.GM);
            associateUnitName(IfcUnitMeasure.GRAY, Qudt.Units.GRAY);
            associateUnitName(IfcUnitMeasure.HENRY, Qudt.Units.H);
            associateUnitName(IfcUnitMeasure.HERTZ, Qudt.Units.HZ);
            associateUnitName(IfcUnitMeasure.JOULE, Qudt.Units.J);
            associateUnitName(IfcUnitMeasure.KELVIN, Qudt.Units.K);
            associateUnitName(IfcUnitMeasure.LUMEN, Qudt.Units.LM);
            associateUnitName(IfcUnitMeasure.LUX, Qudt.Units.LUX);
            associateUnitName(IfcUnitMeasure.METRE, Qudt.Units.M);
            associateUnitName(IfcUnitMeasure.MOLE, Qudt.Units.MOL);
            associateUnitName(IfcUnitMeasure.NEWTON, Qudt.Units.N);
            associateUnitName(IfcUnitMeasure.OHM, Qudt.Units.OHM);
            associateUnitName(IfcUnitMeasure.PASCAL, Qudt.Units.PA);
            associateUnitName(IfcUnitMeasure.RADIAN, Qudt.Units.RAD);
            associateUnitName(IfcUnitMeasure.SECOND, Qudt.Units.SEC);
            associateUnitName(IfcUnitMeasure.SIEMENS, Qudt.Units.S);
            associateUnitName(IfcUnitMeasure.SIEVERT, Qudt.Units.SV);
            associateUnitName(IfcUnitMeasure.SQUARE_METRE, Qudt.Units.M2);
            associateUnitName(IfcUnitMeasure.STERADIAN, Qudt.Units.SR);
            associateUnitName(IfcUnitMeasure.TESLA, Qudt.Units.T);
            associateUnitName(IfcUnitMeasure.VOLT, Qudt.Units.V);
            associateUnitName(IfcUnitMeasure.WATT, Qudt.Units.W);
            associateUnitName(IfcUnitMeasure.WEBER, Qudt.Units.WB);
            // prefixes
            associatePrefix(IfcUnitMeasurePrefix.EXA, Qudt.Prefixes.Exa);
            associatePrefix(IfcUnitMeasurePrefix.PETA, Qudt.Prefixes.Peta);
            associatePrefix(IfcUnitMeasurePrefix.TERA, Qudt.Prefixes.Tera);
            associatePrefix(IfcUnitMeasurePrefix.GIGA, Qudt.Prefixes.Giga);
            associatePrefix(IfcUnitMeasurePrefix.MEGA, Qudt.Prefixes.Mega);
            associatePrefix(IfcUnitMeasurePrefix.KILO, Qudt.Prefixes.Kilo);
            associatePrefix(IfcUnitMeasurePrefix.HECTO, Qudt.Prefixes.Hecto);
            associatePrefix(IfcUnitMeasurePrefix.DECA, Qudt.Prefixes.Deca);
            associatePrefix(IfcUnitMeasurePrefix.DECI, Qudt.Prefixes.Deci);
            associatePrefix(IfcUnitMeasurePrefix.CENTI, Qudt.Prefixes.Centi);
            associatePrefix(IfcUnitMeasurePrefix.MILLI, Qudt.Prefixes.Milli);
            associatePrefix(IfcUnitMeasurePrefix.MICRO, Qudt.Prefixes.Micro);
            associatePrefix(IfcUnitMeasurePrefix.NANO, Qudt.Prefixes.Nano);
            associatePrefix(IfcUnitMeasurePrefix.PICO, Qudt.Prefixes.Pico);
            associatePrefix(IfcUnitMeasurePrefix.FEMTO, Qudt.Prefixes.Femto);
            associatePrefix(IfcUnitMeasurePrefix.ATTO, Qudt.Prefixes.Atto);
        } catch (Exception e) {
            logger.error("Error initializing {} ", QudtIfcMapper.class.getName(), e);
        }
    }

    private static void associateUnitName(IfcUnitMeasure ifcUnitMeasure, Unit unit) {
        ifcUnitMeasureToUnit.put(ifcUnitMeasure, unit);
        unitToIfcUnitMeasure.put(unit, ifcUnitMeasure);
    }

    private static void associatePrefix(IfcUnitMeasurePrefix ifcUnitMeasurePrefix, Prefix prefix) {
        ifcUnitMeasurePrefixToPrefix.put(ifcUnitMeasurePrefix, prefix);
        prefixToIfcUnitMeasurePrefix.put(prefix, ifcUnitMeasurePrefix);
    }

    private static void associate(IfcUnitType ifcUnitType, Unit unit) {
        Qudt.quantityKindsBroad(unit)
                .forEach(
                        qk -> {
                            Set<IfcUnitType> types = quantityKindToIfcUnitTypes.get(qk);
                            if (types == null) {
                                types = new HashSet<>();
                            }
                            types.add(ifcUnitType);
                            quantityKindToIfcUnitTypes.put(qk, types);

                            Set<QuantityKind> quantityKinds =
                                    ifcUnitTypeToQuantityKinds.get(ifcUnitType);
                            if (quantityKinds == null) {
                                quantityKinds = new HashSet<>();
                            }
                            quantityKinds.add(qk);
                            ifcUnitTypeToQuantityKinds.put(ifcUnitType, quantityKinds);
                        });

        if (unit.getDimensionVectorIri().isPresent()) {
            IRI dimVector = unit.getDimensionVectorIri().get();
            Set<IRI> dimVectors = ifcUnitTypeToDimensionVectors.get(ifcUnitType);
            if (dimVectors == null) {
                dimVectors = new HashSet<>();
            }
            dimVectors.add(dimVector);
            ifcUnitTypeToDimensionVectors.put(ifcUnitType, dimVectors);

            Set<IfcUnitType> types = dimensionVectorToIfcUnitTypes.get(dimVector);
            if (types == null) {
                types = new HashSet<>();
            }
            types.add(ifcUnitType);
            dimensionVectorToIfcUnitTypes.put(dimVector, types);
        }
    }

    public static IfcUnit mapQudtUnitToIfcUnit(String qudtUnitIri) {
        Unit unit = Qudt.unit(qudtUnitIri);
        return mapQudtUnitToIfcUnit(unit);
    }

    public static IfcUnit mapQudtUnitToIfcUnit(Unit unit) {
        List<FactorUnit> factorUnits = Qudt.factorUnits(unit);
        if (factorUnits.isEmpty()) {
            return makeIfcSIUnit(unit, Qudt.unscale(unit));
        }
        List<FactorUnit> unscaledUnitFactors = Qudt.unscaleFactorUnits(factorUnits);
        if (factorUnits.size() > 1 || factorUnits.stream().findFirst().get().getExponent() != 1) {
            Map<IfcSIUnit, Integer> derivedUnitElements = new HashMap<>();
            for (int i = 0; i < factorUnits.size(); i++) {
                FactorUnit unitFactor = factorUnits.get(i);
                IfcSIUnit ifcSIUnit =
                        makeIfcSIUnit(unitFactor.getUnit(), unscaledUnitFactors.get(i).getUnit());
                derivedUnitElements.put(ifcSIUnit, unitFactor.getExponent());
            }
            IfcUnitType type = getIfcUnitType(unit);
            return new IfcDerivedUnit(null, type, derivedUnitElements, false);
        } else {
            return makeIfcSIUnit(
                    factorUnits.stream().findFirst().get().getUnit(),
                    unscaledUnitFactors.stream().findFirst().get().getUnit());
        }
    }

    private static IfcSIUnit makeIfcSIUnit(Unit unit, Unit unscaledUnit) {
        if (unit.getDimensionVectorIri().isPresent()) {
            try {
                IfcUnitType type = getIfcUnitType(unit);
                IfcUnitMeasure measure = getIfcUnitMeasure(unit);
                IfcUnitMeasurePrefix prefix = getIfcUnitMeasurePrefix(unit);
                return new IfcSIUnit(null, type, measure, prefix, false);
            } catch (UnsupportedOperationException e) {
            }
            IfcUnitType type = getIfcUnitType(unscaledUnit);
            IfcUnitMeasure measure = getIfcUnitMeasure(unscaledUnit);
            IfcUnitMeasurePrefix prefix = getIfcUnitMeasurePrefix(unit);
            return new IfcSIUnit(null, type, measure, prefix, false);
        } else {
            throw new UnsupportedOperationException("TODO: handle unit " + unit);
        }
    }

    private static IfcUnitMeasure getIfcUnitMeasure(Unit unit) {
        IfcUnitMeasure measure = unitToIfcUnitMeasure.get(unit);
        if (measure == null) {
            throw new UnsupportedOperationException(
                    "TODO: handle unit " + unit + ": no IfcUnitMeasure mapped");
        }
        return measure;
    }

    private static IfcUnitMeasurePrefix getIfcUnitMeasurePrefix(Unit unit) {
        if (unit.getPrefixIri().isPresent()) {
            IfcUnitMeasurePrefix prefix =
                    prefixToIfcUnitMeasurePrefix.get(Qudt.prefix(unit.getPrefixIri().get()));
            if (prefix == null) {
                throw new UnsupportedOperationException(
                        "TODO: handle unit " + unit + ": no IfcUnitMeasurePrefix mapped");
            }
            return prefix;
        }
        return IfcUnitMeasurePrefix.NONE;
    }

    public static IfcUnitType getIfcUnitType(Unit unit) {
        List<IfcUnitType> types = getIfcUnitTypesForQuantityKinds(unit);
        if (types == null || types.isEmpty()) {
            types = getIfcUnitTypesForBroadQuantityKinds(unit);
        }
        if (types == null || types.isEmpty()) {
            throw new UnsupportedOperationException(
                    "TODO: handle unit " + unit + ": no IfcUnitTypes mapped");
        }
        IfcUnitType type =
                types.stream()
                        .collect(Collectors.groupingBy(Function.identity(), toSet()))
                        .entrySet()
                        .stream()
                        .map(e -> Map.entry(e.getKey(), e.getValue().size()))
                        .reduce(
                                null,
                                (left, right) -> {
                                    if (left == null) {
                                        return right;
                                    } else if (right == null) {
                                        return left;
                                    } else {
                                        if (left.getValue() < right.getValue()) {
                                            return right;
                                        } else {
                                            return left;
                                        }
                                    }
                                })
                        .getKey();
        return type;
    }

    public static IfcPropertyType getIfcPropertyType(Unit unit) {
        return getIfcMeasure(unit);
    }

    public static IfcPropertyType getIfcMeasure(Unit unit) {
        return getIfcMeasure(getIfcUnitType(unit));
    }

    public static IfcPropertyType getIfcMeasure(IfcUnitType unitType) {
        return IfcPropertyType.forIfcUnitType(unitType);
    }

    private static List<IfcUnitType> getIfcUnitTypesForBroadQuantityKinds(Unit unit) {
        List<IfcUnitType> types =
                Qudt.quantityKindsBroad(unit).stream()
                        .filter(t -> t.getBroaderQuantityKinds().isEmpty())
                        .map(t -> quantityKindToIfcUnitTypes.get(t))
                        .filter(Objects::nonNull)
                        .flatMap(t -> t.stream())
                        .collect(Collectors.toList());
        return types;
    }

    private static List<IfcUnitType> getIfcUnitTypesForQuantityKinds(Unit unit) {
        List<IfcUnitType> types =
                unit.getQuantityKinds().stream()
                        .map(t -> quantityKindToIfcUnitTypes.get(t))
                        .filter(Objects::nonNull)
                        .flatMap(t -> t.stream())
                        .collect(Collectors.toList());
        return types;
    }

    /**
     * Extracts the appropriate QudtUnit String for the given prefix and measure
     *
     * @param ifcUnit IfcUnit
     * @return Matching QudtUnit String
     * @throws IllegalArgumentException if there is no matching QudtUnit String for the given prefix
     *     and measure
     * @throws NullPointerException if the measure is null
     */
    public static Set<Unit> mapIfcUnitToQudtUnit(IfcUnit ifcUnit)
            throws IllegalArgumentException, NullPointerException {
        if (ifcUnit instanceof IfcSIUnit) {
            return Set.of(
                    extractUnitFromPrefixAndMeasure(
                            ((IfcSIUnit) ifcUnit).getPrefix(), ((IfcSIUnit) ifcUnit).getMeasure()));
        } else if (ifcUnit instanceof IfcDerivedUnit) {
            IfcDerivedUnit derivedUnit = (IfcDerivedUnit) ifcUnit;
            List<IfcDerivedUnitElement> derivedUnitElements = derivedUnit.getDerivedUnitElements();
            List<Map.Entry<Unit, Integer>> convertedFactorUnits =
                    derivedUnitElements.stream()
                            .map(
                                    e ->
                                            Map.entry(
                                                    mapIfcUnitToQudtUnit(e.getIfcUnit()).stream()
                                                            .findFirst()
                                                            .get(),
                                                    e.getExponent()))
                            .collect(Collectors.toList());
            return Qudt.derivedUnit(convertedFactorUnits);
        }
        throw new IllegalArgumentException("No QudtUnit for IfcUnit<" + ifcUnit + ">");
    }

    /**
     * Extracts the appropriate QudtUnit String for the given prefix and measure
     *
     * @param prefix IfcUnitMeasurePrefix if null then the Prefix will be set to NONE
     * @param measure IfcUnitMeasure
     * @return Matching QudtUnit String
     * @throws IllegalArgumentException if there is no matching QudtUnit String for the given prefix
     *     and measure
     * @throws NullPointerException if the measure is null
     */
    private static Unit extractUnitFromPrefixAndMeasure(
            IfcUnitMeasurePrefix prefix, IfcUnitMeasure measure)
            throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(measure, "No QudtUnit for null value in measure");
        prefix = Objects.requireNonNullElse(prefix, IfcUnitMeasurePrefix.NONE);
        if (prefix.equals(IfcUnitMeasurePrefix.NONE)) {
            return Qudt.unitFromLabel(measure.name());
        }
        return Qudt.scaledUnit(prefix.name(), measure.name().replaceAll("_", " "));
    }

    private static final HashMap<Object, Object> QUANTITY_KIND_TO_IFC_UNIT_TYPE;

    static {
        QUANTITY_KIND_TO_IFC_UNIT_TYPE = new HashMap<>();
        QUANTITY_KIND_TO_IFC_UNIT_TYPE.put(
                "http://qudt.org/vocab/quantitykind/Length", IfcUnitType.LENGTHUNIT);
        QUANTITY_KIND_TO_IFC_UNIT_TYPE.put(
                "http://qudt.org/vocab/quantitykind/Area", IfcUnitType.AREAUNIT);
        QUANTITY_KIND_TO_IFC_UNIT_TYPE.put(
                "http://qudt.org/vocab/quantitykind/Volume", IfcUnitType.VOLUMEUNIT);
        QUANTITY_KIND_TO_IFC_UNIT_TYPE.put(
                "http://qudt.org/vocab/quantitykind/Mass", IfcUnitType.MASSUNIT);
        QUANTITY_KIND_TO_IFC_UNIT_TYPE.put(
                "http://qudt.org/vocab/quantitykind/Time", IfcUnitType.TIMEUNIT);
        QUANTITY_KIND_TO_IFC_UNIT_TYPE.put(
                "http://qudt.org/vocab/quantitykind/Frequency", IfcUnitType.FREQUENCYUNIT);
        QUANTITY_KIND_TO_IFC_UNIT_TYPE.put(
                "http://qudt.org/vocab/quantitykind/Angle", IfcUnitType.PLANEANGLEUNIT);
        QUANTITY_KIND_TO_IFC_UNIT_TYPE.put(
                "http://qudt.org/vocab/quantitykind/Temperature",
                IfcUnitType.THERMODYNAMICTEMPERATUREUNIT);
        QUANTITY_KIND_TO_IFC_UNIT_TYPE.put(
                "http://qudt.org/vocab/quantitykind/AbsorbedDose", IfcUnitType.ABSORBEDDOSEUNIT);
        QUANTITY_KIND_TO_IFC_UNIT_TYPE.put(
                "http://qudt.org/vocab/quantitykind/AmountOfSubstancePerUnitVolume",
                IfcUnitType.AMOUNTOFSUBSTANCEUNIT);
        QUANTITY_KIND_TO_IFC_UNIT_TYPE.put(
                "http://qudt.org/vocab/quantitykind/AmountOfSubstancePerUnitMass",
                IfcUnitType.AMOUNTOFSUBSTANCEUNIT);
        QUANTITY_KIND_TO_IFC_UNIT_TYPE.put(
                "http://qudt.org/vocab/quantitykind/AmountOfSubstancePerUnitVolume",
                IfcUnitType.AMOUNTOFSUBSTANCEUNIT);
        QUANTITY_KIND_TO_IFC_UNIT_TYPE.put(
                "http://qudt.org/vocab/quantitykind/DoseEquivalent",
                IfcUnitType.DOSEEQUIVALENTUNIT);
        // TODO: add other unit types
    }

    /*
    private static String extractUnitFromPrefixAndMeasure(
                    IfcUnitMeasurePrefix prefix, IfcUnitMeasure measure)
                    throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(measure, "No QudtUnit for null value in measure");
        prefix = Objects.requireNonNullElse(prefix, IfcUnitMeasurePrefix.NONE);
        switch (measure) {
            case METRE:
                switch (prefix) {
                    case DECI:
                        return QudtUnit.DECIMETRE;
                    case CENTI:
                        return CENTIMETRE;
                    case NONE:
                        return METRE;
                }
                break;
            case SQUARE_METRE:
                if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                    return SQUARE_METRE;
                }
                break;
            case CUBIC_METRE:
                if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                    return CUBIC_METRE;
                }
                break;
            case GRAM:
                switch (prefix) {
                    case KILO:
                        return KILOGRAM;
                    case NONE:
                        return GRAM;
                }
                break;
            case SECOND:
                if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                    return SECOND;
                }
                break;
            case HERTZ:
                if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                    return HERTZ;
                }
                break;
            case DEGREE_CELSIUS:
                if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                    return DEGREE_CELSIUS;
                }
                break;
            case AMPERE:
                if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                    return AMPERE;
                }
                break;
            case VOLT:
                if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                    return VOLT;
                }
                break;
            case WATT:
                switch (prefix) {
                    case KILO:
                        return KILOWATT;
                    case MEGA:
                        return MEGAWATT;
                    case NONE:
                        return WATT;
                }
                break;
            case NEWTON:
                switch (prefix) {
                    case KILO:
                        return KILONEWTON;
                    case MEGA:
                        return MEGANEWTON;
                    case NONE:
                        return NEWTON;
                }
                break;
            case LUX:
                if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                    return LUX;
                }
                break;
            case LUMEN:
                if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                    return LUMEN;
                }
                break;
            case CANDELA:
                if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                    return CANDELA;
                }
                break;
            case PASCAL:
                if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                    return PASCAL;
                }
                break;
            case RADIAN:
                if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                    return RADIAN;
                }
                break;
            case JOULE:
                if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                    return JOULE;
                }
            case KELVIN:
                if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                    return KELVIN;
                }
            case STERADIAN:
                if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                    return STERADIAN;
                }
        }
        throw new IllegalArgumentException(
                        "No QudtUnit for prefix<" + prefix + "> and measure<" + measure + ">");
    }*/
    private static final IfcSIUnit IFCMETER =
            new IfcSIUnit(
                    1,
                    IfcUnitType.LENGTHUNIT,
                    IfcUnitMeasure.METRE,
                    IfcUnitMeasurePrefix.NONE,
                    false);
    private static final IfcSIUnit IFCSECOND =
            new IfcSIUnit(
                    2,
                    IfcUnitType.TIMEUNIT,
                    IfcUnitMeasure.SECOND,
                    IfcUnitMeasurePrefix.NONE,
                    false);
    private static final IfcSIUnit IFCKILOGRAM =
            new IfcSIUnit(
                    3, IfcUnitType.MASSUNIT, IfcUnitMeasure.GRAM, IfcUnitMeasurePrefix.KILO, false);
    private static final IfcSIUnit IFCKELVIN =
            new IfcSIUnit(
                    1,
                    IfcUnitType.THERMODYNAMICTEMPERATUREUNIT,
                    IfcUnitMeasure.KELVIN,
                    IfcUnitMeasurePrefix.NONE,
                    false);
    /*
    private static Map<String, IfcUnit> QUDT_UNIT_TO_IFC_UNIT =
                    Map.ofEntries(
                                    Map.entry(
                                                    MILLIMETRE,
                                                    new IfcSIUnit(
                                                                    toUuid(MILLIMETRE),
                                                                    IfcUnitType.LENGTHUNIT,
                                                                    IfcUnitMeasure.METRE,
                                                                    IfcUnitMeasurePrefix.MILLI,
                                                                    false)),
                                    Map.entry(
                                                    CENTIMETRE,
                                                    new IfcSIUnit(
                                                                    toUuid(CENTIMETRE),
                                                                    IfcUnitType.LENGTHUNIT,
                                                                    IfcUnitMeasure.METRE,
                                                                    IfcUnitMeasurePrefix.CENTI,
                                                                    false)),
                                    Map.entry(
                                                    DECIMETRE,
                                                    new IfcSIUnit(
                                                                    toUuid(DECIMETRE),
                                                                    IfcUnitType.LENGTHUNIT,
                                                                    IfcUnitMeasure.METRE,
                                                                    IfcUnitMeasurePrefix.DECI,
                                                                    false)),
                                    Map.entry(METRE, IFCMETER),
                                    Map.entry(
                                                    SQUARE_METRE,
                                                    new IfcSIUnit(
                                                                    toUuid(SQUARE_METRE),
                                                                    IfcUnitType.AREAUNIT,
                                                                    IfcUnitMeasure.SQUARE_METRE,
                                                                    IfcUnitMeasurePrefix.NONE,
                                                                    false)));*/
    /*
    switch (measure) {
        case METRE:
            switch (prefix) {
                case DECI:
                    return DECIMETRE;
                case CENTI:
                    return CENTIMETRE;
                case NONE:
                    return METRE;
            }
            break;
        case SQUARE_METRE:
            if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                return SQUARE_METRE;
            }
            break;
        case CUBIC_METRE:
            if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                return CUBIC_METRE;
            }
            break;
        case GRAM:
            switch (prefix) {
                case KILO:
                    return KILOGRAM;
                case NONE:
                    return GRAM;
            }
            break;
        case SECOND:
            if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                return SECOND;
            }
            break;
        case HERTZ:
            if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                return HERTZ;
            }
            break;
        case DEGREE_CELSIUS:
            if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                return DEGREE_CELSIUS;
            }
            break;
        case AMPERE:
            if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                return AMPERE;
            }
            break;
        case VOLT:
            if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                return VOLT;
            }
            break;
        case WATT:
            switch (prefix) {
                case KILO:
                    return KILOWATT;
                case MEGA:
                    return MEGAWATT;
                case NONE:
                    return WATT;
            }
            break;
        case NEWTON:
            switch (prefix) {
                case KILO:
                    return KILONEWTON;
                case MEGA:
                    return MEGANEWTON;
                case NONE:
                    return NEWTON;
            }
            break;
        case LUX:
            if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                return LUX;
            }
            break;
        case LUMEN:
            if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                return LUMEN;
            }
            break;
        case CANDELA:
            if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                return CANDELA;
            }
            break;
        case PASCAL:
            if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                return PASCAL;
            }
            break;
        case RADIAN:
            if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                return RADIAN;
            }
            break;
        case JOULE:
            if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                return JOULE;
            }
        case KELVIN:
            if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                return KELVIN;
            }
        case STERADIAN:
            if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                return STERADIAN;
            }
            */
}
