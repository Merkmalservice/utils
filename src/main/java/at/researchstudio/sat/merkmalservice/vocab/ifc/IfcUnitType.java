package at.researchstudio.sat.merkmalservice.vocab.ifc;

import java.util.Objects;

public enum IfcUnitType {
    LENGTHUNIT("LENGTHUNIT"),
    AREAUNIT("AREAUNIT"),
    VOLUMEUNIT("VOLUMEUNIT"),
    MASSUNIT("MASSUNIT"),
    TIMEUNIT("TIMEUNIT"),
    FREQUENCYUNIT("FREQUENCYUNIT"),
    PLANEANGLEUNIT("PLANEANGLEUNIT"),
    THERMODYNAMICTEMPERATUREUNIT("THERMODYNAMICTEMPERATUREUNIT"),
    ELECTRICCURRENTUNIT("ELECTRICCURRENTUNIT"),
    ELECTRIVOLTAGEUNIT("ELECTRICVOLTAGEUNIT"),
    POWERUNIT("POWERUNIT"),
    ENERGYUNIT("ENERGYUNIT"),
    FORCEUNIT("FORCEUNIT"),
    ILLUMINANCEUNIT("ILLUMINANCEUNIT"),
    LUMINOUSFLUXUNIT("LUMINOUSFLUXUNIT"),
    LUMINOUSINTENSITYUNIT("LUMINOUSINTENSITYUNIT"),
    PRESSUREUNIT("PRESSUREUNIT"),
    SOLIDANGLEUNIT("SOLIDANGLEUNIT"),
    VOLUMETRICFLOWRATEUNIT("VOLUMETRICFLOWRATEUNIT"),
    LINEARFORCEUNIT("LINEARFORCEUNIT"),
    MASSDENSITYUNIT("MASSDENSITYUNIT"),
    MOMENTOFINERTIAUNIT("MOMENTOFINERTIAUNIT"),
    LINEARVELOCITYUNIT("LINEARVELOCITYUNIT"),
    PLANARFORCEUNIT("PLANARFORCEUNIT"),
    THERMALTRANSMITTANCEUNIT("THERMALTRANSMITTANCEUNIT"),
    USERDEFINED("USERDEFINED"),
    UNKNOWN();

    // declaring private variable for getting values
    private final String[] typeUris;

    IfcUnitType(String... typeUris) {
        this.typeUris = typeUris;
    }

    /**
     * retrieves corresponding enum value for the given unitType
     *
     * @param unitType non null
     * @return corresponding Enum Value
     * @throws IllegalArgumentException if no enum value is found
     * @throws NullPointerException if unitType was null
     */
    public static IfcUnitType fromString(String unitType)
            throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(unitType, "No enum IfcUnitType for nullvalue");

        for (IfcUnitType type : IfcUnitType.values()) {
            for (String enumUri : type.typeUris) {
                if (unitType.contains("#")) {
                    String[] splitUnitType = unitType.split("#");
                    if (splitUnitType.length == 2 && enumUri.equals(splitUnitType[1])) {
                        return type;
                    }
                }
                if (enumUri.equals(unitType)) {
                    return type;
                }
            }
        }
        throw new IllegalArgumentException("No enum constant for value: '" + unitType + "'");
    }
}
