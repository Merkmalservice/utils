package at.researchstudio.sat.merkmalservice.vocab.ifc;

import java.util.Objects;

public enum IfcUnitType {
    ABSORBEDDOSEUNIT("ABSORBEDDOSEUNIT"),
    AMOUNTOFSUBSTANCEUNIT("AMOUNTOFSUBSTANCEUNIT"),
    AREAUNIT("AREAUNIT"),
    DOSEEQUIVALENTUNIT("DOSEEQUIVALENTUNIT"),
    ELECTRICCAPACITANCEUNIT("ELECTRICCAPACITANCEUNIT"),
    ELECTRICCHARGEUNIT("ELECTRICCHARGEUNIT"),
    ELECTRICCONDUCTANCEUNIT("ELECTRICCONDUCTANCEUNIT"),
    ELECTRICCURRENTUNIT("ELECTRICCURRENTUNIT"),
    ELECTRICRESISTANCEUNIT("ELECTRICRESISTANCEUNIT"),
    ELECTRICVOLTAGEUNIT("ELECTRICVOLTAGEUNIT"),
    ELECTRIVOLTAGEUNIT("ELECTRICVOLTAGEUNIT"),
    ENERGYUNIT("ENERGYUNIT"),
    FORCEUNIT("FORCEUNIT"),
    FREQUENCYUNIT("FREQUENCYUNIT"),
    ILLUMINANCEUNIT("ILLUMINANCEUNIT"),
    INDUCTANCEUNIT("INDUCTANCEUNIT"),
    LENGTHUNIT("LENGTHUNIT"),
    LINEARFORCEUNIT("LINEARFORCEUNIT"),
    LINEARVELOCITYUNIT("LINEARVELOCITYUNIT"),
    LUMINOUSFLUXUNIT("LUMINOUSFLUXUNIT"),
    LUMINOUSINTENSITYUNIT("LUMINOUSINTENSITYUNIT"),
    MAGNETICFLUXDENSITYUNIT("MAGNETICFLUXDENSITYUNIT"),
    MAGNETICFLUXUNIT("MAGNETICFLUXUNIT"),
    MASSDENSITYUNIT("MASSDENSITYUNIT"),
    MASSUNIT("MASSUNIT"),
    MOMENTOFINERTIAUNIT("MOMENTOFINERTIAUNIT"),
    PLANARFORCEUNIT("PLANARFORCEUNIT"),
    PLANEANGLEUNIT("PLANEANGLEUNIT"),
    POWERUNIT("POWERUNIT"),
    PRESSUREUNIT("PRESSUREUNIT"),
    RADIOACTIVITYUNIT("RADIOACTIVITYUNIT"),
    SOLIDANGLEUNIT("SOLIDANGLEUNIT"),
    THERMALTRANSMITTANCEUNIT("THERMALTRANSMITTANCEUNIT"),
    THERMODYNAMICTEMPERATUREUNIT("THERMODYNAMICTEMPERATUREUNIT"),
    TIMEUNIT("TIMEUNIT"),
    VOLUMETRICFLOWRATEUNIT("VOLUMETRICFLOWRATEUNIT"),
    VOLUMEUNIT("VOLUMEUNIT"),
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
