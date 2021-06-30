package at.researchstudio.sat.merkmalservice.vocab.qudt;

import at.researchstudio.sat.merkmalservice.vocab.ifc.IfcUnitMeasure;
import at.researchstudio.sat.merkmalservice.vocab.ifc.IfcUnitMeasurePrefix;

public abstract class QudtUnit {
    public static final String METRE = "http://qudt.org/vocab/unit/M";
    public static final String CENTIMETRE = "http://qudt.org/vocab/unit/CentiM";
    public static final String DECIMETRE = "http://qudt.org/vocab/unit/DeciM";

    public static final String SQUARE_METRE = "http://qudt.org/vocab/unit/M2";
    public static final String CUBIC_METRE = "http://qudt.org/vocab/unit/M3";
    public static final String GRAM = "http://qudt.org/vocab/unit/GM";
    public static final String SECOND = "http://qudt.org/vocab/unit/SEC";
    public static final String HERTZ = "http://qudt.org/vocab/unit/HZ";
    public static final String DEGREE_CELSIUS = "http://qudt.org/vocab/unit/DEG_C";
    public static final String AMPERE = "http://qudt.org/vocab/unit/A";
    public static final String VOLT = "http://qudt.org/vocab/unit/V";
    public static final String WATT = "http://qudt.org/vocab/unit/W";
    public static final String NEWTON = "http://qudt.org/vocab/unit/N";
    public static final String LUX = "http://qudt.org/vocab/unit/LUX";
    public static final String LUMEN = "http://qudt.org/vocab/unit/LM";
    public static final String CANDELA = "http://qudt.org/vocab/unit/CD";
    public static final String PASCAL = "http://qudt.org/vocab/unit/PA";
    public static final String UNITLESS = "http://qudt.org/vocab/unit/UNITLESS";

    /**
     * Extracts the appropriate QudtUnit String for the given prefix and measure
     * @param prefix IfcUnitMeasurePrefix
     * @param measure IfcUnitMeasure
     * @throws IllegalArgumentException if there is no matching QudtUnit String for the given prefix and measure
     * @return Matching QudtUnit String
     */
    public static String extractUnitFromPrefixAndMeasure(IfcUnitMeasurePrefix prefix, IfcUnitMeasure measure) throws IllegalArgumentException {
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
            case SQUARE_METRE:
                if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                    return SQUARE_METRE;
                }
            case CUBIC_METRE:
                if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                    return CUBIC_METRE;
                }
            case GRAM:
                if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                    return GRAM;
                }
            case SECOND:
                if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                    return SECOND;
                }
            case HERTZ:
                if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                    return HERTZ;
                }
            case DEGREE_CELSIUS:
                if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                    return DEGREE_CELSIUS;
                }
            case AMPERE:
                if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                    return AMPERE;
                }
            case VOLT:
                if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                    return VOLT;
                }
            case WATT:
                if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                    return WATT;
                }
            case NEWTON:
                if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                    return NEWTON;
                }
            case LUX:
                if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                    return LUX;
                }
            case LUMEN:
                if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                    return LUMEN;
                }
            case CANDELA:
                if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                    return CANDELA;
                }
            case PASCAL:
                if (IfcUnitMeasurePrefix.NONE.equals(prefix)) {
                    return PASCAL;
                }
        }

        throw new IllegalArgumentException(
                "No QudtUnit for prefix<" + prefix + "> and measure<"+ measure +">");
    }
}
