package at.researchstudio.sat.merkmalservice.vocab.qudt;

import at.researchstudio.sat.merkmalservice.model.ifc.IfcDerivedUnit;
import at.researchstudio.sat.merkmalservice.model.ifc.IfcSIUnit;
import at.researchstudio.sat.merkmalservice.model.ifc.IfcUnit;
import at.researchstudio.sat.merkmalservice.vocab.ifc.IfcUnitMeasure;
import at.researchstudio.sat.merkmalservice.vocab.ifc.IfcUnitMeasurePrefix;
import at.researchstudio.sat.merkmalservice.vocab.ifc.IfcUnitType;
import java.lang.invoke.MethodHandles;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class QudtUnit {
    private static final Logger logger =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static final String METRE = "http://qudt.org/vocab/unit/M";
    public static final String CENTIMETRE = "http://qudt.org/vocab/unit/CentiM";
    public static final String MILLIMETRE = "http://qudt.org/vocab/unit/MilliM";
    public static final String KILOMETRE = "http://qudt.org/vocab/unit/KiloM";
    public static final String DECIMETRE = "http://qudt.org/vocab/unit/DeciM";
    public static final String SQUARE_METRE = "http://qudt.org/vocab/unit/M2";
    public static final String CUBIC_METRE = "http://qudt.org/vocab/unit/M3";
    public static final String QUARTIC_METRE = "http://qudt.org/vocab/unit/M4";
    public static final String GRAM = "http://qudt.org/vocab/unit/GM";
    public static final String KILOGRAM = "http://qudt.org/vocab/unit/KiloGM";
    public static final String SECOND = "http://qudt.org/vocab/unit/SEC";
    public static final String HERTZ = "http://qudt.org/vocab/unit/HZ";
    public static final String DEGREE_CELSIUS = "http://qudt.org/vocab/unit/DEG_C";
    public static final String AMPERE = "http://qudt.org/vocab/unit/A";
    public static final String VOLT = "http://qudt.org/vocab/unit/V";
    public static final String WATT = "http://qudt.org/vocab/unit/W";
    public static final String KILOWATT = "http://qudt.org/vocab/unit/KiloW";
    public static final String MEGAWATT = "http://qudt.org/vocab/unit/MegaW";
    public static final String NEWTON = "http://qudt.org/vocab/unit/N";
    public static final String KILONEWTON = "http://qudt.org/vocab/unit/KiloN";
    public static final String MEGANEWTON = "http://qudt.org/vocab/unit/MegaN";
    public static final String LUX = "http://qudt.org/vocab/unit/LUX";
    public static final String LUMEN = "http://qudt.org/vocab/unit/LM";
    public static final String CANDELA = "http://qudt.org/vocab/unit/CD";
    public static final String PASCAL = "http://qudt.org/vocab/unit/PA";
    public static final String RADIAN = "http://qudt.org/vocab/unit/RAD";
    public static final String STERADIAN = "http://qudt.org/vocab/unit/SR";
    public static final String KELVIN = "http://qudt.org/vocab/unit/K";
    public static final String JOULE = "http://qudt.org/vocab/unit/J";
    public static final String WATTPERSQUARKEMETERPERKELVIN =
            "http://qudt.org/vocab/unit/W-PER-M2-K";
    public static final String NEWTONPERSQUAREMILLIMETER =
            "http://qudt.org/vocab/unit/N-PER-MilliM2";
    public static final String KILOGRAMPERCUBICMETER = "http://qudt.org/vocab/unit/KiloGM-PER-M3";
    public static final String CUBICMETERPERSECOND = "http://qudt.org/vocab/unit/M3-PER-SEC";
    public static final String DEGREES = "http://qudt.org/vocab/unit/DEG";
    public static final String UNITLESS = "http://qudt.org/vocab/unit/UNITLESS";

    private static final IfcSIUnit IFCMETER =
            new IfcSIUnit(
                    "1",
                    IfcUnitType.LENGTHUNIT,
                    IfcUnitMeasure.METRE,
                    IfcUnitMeasurePrefix.NONE,
                    false);
    private static final IfcSIUnit IFCSECOND =
            new IfcSIUnit(
                    "2",
                    IfcUnitType.TIMEUNIT,
                    IfcUnitMeasure.SECOND,
                    IfcUnitMeasurePrefix.NONE,
                    false);
    private static final IfcSIUnit IFCKILOGRAM =
            new IfcSIUnit(
                    "3",
                    IfcUnitType.MASSUNIT,
                    IfcUnitMeasure.GRAM,
                    IfcUnitMeasurePrefix.KILO,
                    false);

    private static final IfcSIUnit IFCKELVIN =
            new IfcSIUnit(
                    "1",
                    IfcUnitType.THERMODYNAMICTEMPERATUREUNIT,
                    IfcUnitMeasure.KELVIN,
                    IfcUnitMeasurePrefix.NONE,
                    false);

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
                                    false)));
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

    private static String toUuid(String forName) {
        return UUID.nameUUIDFromBytes(forName.getBytes()).toString();
    }

    public static Optional<IfcUnit> mapQudtUnitToIfcUnit(String qudtUnit) {
        IfcUnit mapped = QUDT_UNIT_TO_IFC_UNIT.get(qudtUnit);
        return Optional.ofNullable(mapped);
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
    public static String mapIfcUnitToQudtUnit(IfcUnit ifcUnit)
            throws IllegalArgumentException, NullPointerException {
        if (ifcUnit instanceof IfcSIUnit) {
            return extractUnitFromPrefixAndMeasure(
                    ((IfcSIUnit) ifcUnit).getPrefix(), ((IfcSIUnit) ifcUnit).getMeasure());
        } else if (ifcUnit instanceof IfcDerivedUnit) {
            IfcDerivedUnit derivedUnit = (IfcDerivedUnit) ifcUnit;
            Map<IfcSIUnit, Integer> derivedUnitElements = derivedUnit.getDerivedUnitElements();

            if (derivedUnitElements.size() == 1) {
                if (derivedUnitElements.getOrDefault(IFCMETER, 0) == 4) {
                    return QudtUnit.QUARTIC_METRE;
                }
            } else if (derivedUnitElements.size() == 2) {
                if (derivedUnitElements.getOrDefault(IFCKILOGRAM, 0) == 1
                        && derivedUnitElements.getOrDefault(IFCMETER, 0) == -3) {
                    return QudtUnit.KILOGRAMPERCUBICMETER;
                } else if (derivedUnitElements.getOrDefault(IFCSECOND, 0) == -1
                        && derivedUnitElements.getOrDefault(IFCMETER, 0) == 3) {
                    return QudtUnit.CUBICMETERPERSECOND;
                }
            } else if (derivedUnitElements.size() == 3) {
                if (derivedUnitElements.getOrDefault(IFCMETER, 0) == 1
                        && derivedUnitElements.getOrDefault(IFCSECOND, 0) == -2
                        && derivedUnitElements.getOrDefault(IFCKILOGRAM, 0) == 1) {

                    return QudtUnit.NEWTON;
                } else if (derivedUnitElements.getOrDefault(IFCKELVIN, 0) == -1
                        && derivedUnitElements.getOrDefault(IFCSECOND, 0) == -3
                        && derivedUnitElements.getOrDefault(IFCKILOGRAM, 0) == 1) {
                    // TODO: FIND UNIT
                }
            }
            throw new IllegalArgumentException(
                    "No QudtUnit for IfcDerivedUnit<" + derivedUnit + ">");
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
    private static String extractUnitFromPrefixAndMeasure(
            IfcUnitMeasurePrefix prefix, IfcUnitMeasure measure)
            throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(measure, "No QudtUnit for null value in measure");
        prefix = Objects.requireNonNullElse(prefix, IfcUnitMeasurePrefix.NONE);
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
        }
        throw new IllegalArgumentException(
                "No QudtUnit for prefix<" + prefix + "> and measure<" + measure + ">");
    }
}
