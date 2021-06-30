package at.researchstudio.sat.merkmalservice.vocab.ifc;

import java.util.Objects;

public enum IfcUnitMeasurePrefix {
    MEGA("MEGA"),
    KILO("KILO"),
    DECI("DECI"),
    CENTI("CENTI"),
    NONE();

    // declaring private variable for getting values
    private final String[] prefixUris;

    IfcUnitMeasurePrefix(String... prefixUris) {
        this.prefixUris = prefixUris;
    }

    /**
     * retrieves corresponding enum value for the given unitMeasurePrefix
     *
     * @param unitMeasurePrefix non null
     * @return corresponding Enum Value
     * @throws IllegalArgumentException if no enum value is found
     * @throws NullPointerException if unitMeasurePrefix was null
     */
    public static IfcUnitMeasurePrefix fromString(String unitMeasurePrefix)
            throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(unitMeasurePrefix, "No enum IfcUnitMeasurePrefix for nullvalue");

        for (IfcUnitMeasurePrefix type : IfcUnitMeasurePrefix.values()) {
            for (String enumUri : type.prefixUris) {
                if (unitMeasurePrefix.contains("#")) {
                    String[] splitUnitMeasure = unitMeasurePrefix.split("#");
                    if (splitUnitMeasure.length == 2 && enumUri.equals(splitUnitMeasure[1])) {
                        return type;
                    }
                }
                if (enumUri.equals(unitMeasurePrefix)) {
                    return type;
                }
            }
        }
        throw new IllegalArgumentException(
                "No enum IfcUnitMeasurePrefix constant for value: '" + unitMeasurePrefix + "'");
    }
}
