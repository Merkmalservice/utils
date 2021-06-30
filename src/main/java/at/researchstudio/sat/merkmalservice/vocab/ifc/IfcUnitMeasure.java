package at.researchstudio.sat.merkmalservice.vocab.ifc;

import java.util.Objects;

public enum IfcUnitMeasure {
    METRE("METRE"),
    SQUARE_METRE("SQUARE_METRE"),
    CUBIC_METRE("CUBIC_METRE"),
    GRAM("GRAM"),
    SECOND("SECOND"),
    HERTZ("HERTZ"),
    DEGREE_CELSIUS("DEGREE_CELSIUS"),
    AMPERE("AMPERE"),
    VOLT("VOLT"),
    WATT("WATT"),
    NEWTON("NEWTON"),
    LUX("LUX"),
    LUMEN("LUMEN"),
    KELVIN("KELVIN"),
    RADIAN("RADIAN"),
    CANDELA("CANDELA"),
    PASCAL("PASCAL"),
    JOULE("JOULE"),
    STERADIAN("STERADIAN"),
    UNKNOWN();

    // declaring private variable for getting values
    private final String[] measureUris;

    IfcUnitMeasure(String... measureUris) {
        this.measureUris = measureUris;
    }

    /**
     * retrieves corresponding enum value for the given unitMeasure
     *
     * @param unitMeasure non null
     * @return corresponding Enum Value
     * @throws IllegalArgumentException if no enum value is found
     * @throws NullPointerException if unitMeasure was null
     */
    public static IfcUnitMeasure fromString(String unitMeasure)
            throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(unitMeasure, "No enum IfcUnitMeasure for nullvalue");

        for (IfcUnitMeasure type : IfcUnitMeasure.values()) {
            for (String enumUri : type.measureUris) {
                if (unitMeasure.contains("#")) {
                    String[] splitUnitMeasure = unitMeasure.split("#");
                    if (splitUnitMeasure.length == 2 && enumUri.equals(splitUnitMeasure[1])) {
                        return type;
                    }
                }
                if (enumUri.equals(unitMeasure)) {
                    return type;
                }
            }
        }
        throw new IllegalArgumentException(
                "No enum IfcUnitMeasure constant for value: '" + unitMeasure + "'");
    }
}
