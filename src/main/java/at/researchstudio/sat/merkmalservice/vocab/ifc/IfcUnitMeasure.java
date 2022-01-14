package at.researchstudio.sat.merkmalservice.vocab.ifc;

import java.util.Objects;

// represents IfcSIUnitName, see
// https://standards.buildingsmart.org/IFC/DEV/IFC4_3/RC1/HTML/link/ifcsiunitname.htm
public enum IfcUnitMeasure {
    AMPERE("AMPERE"),
    BECQUEREL("BECQUEREL"),
    CANDELA("CANDELA"),
    COULOMB("COULOMB"),
    CUBIC_METRE("CUBIC_METRE"),
    DEGREE_CELSIUS("DEGREE_CELSIUS"),
    FARAD("FARAD"),
    GRAM("GRAM"),
    GRAY("GRAY"),
    HENRY("HENRY"),
    HERTZ("HERTZ"),
    JOULE("JOULE"),
    KELVIN("KELVIN"),
    LUMEN("LUMEN"),
    LUX("LUX"),
    METRE("METRE"),
    MOLE("MOLE"),
    NEWTON("NEWTON"),
    OHM("OHM"),
    PASCAL("PASCAL"),
    RADIAN("RADIAN"),
    SECOND("SECOND"),
    SIEMENS("SIEMENS"),
    SIEVERT("SIEVERT"),
    SQUARE_METRE("SQUARE_METRE"),
    STERADIAN("STERADIAN"),
    TESLA("TESLA"),
    VOLT("VOLT"),
    WATT("WATT"),
    WEBER("WEBER"),
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
