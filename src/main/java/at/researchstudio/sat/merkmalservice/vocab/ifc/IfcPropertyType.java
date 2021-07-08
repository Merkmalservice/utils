package at.researchstudio.sat.merkmalservice.vocab.ifc;

import java.util.Objects;

public enum IfcPropertyType {
    TEXT("IfcText", "IFCTEXT"),
    LABEL("IfcLabel", "IFCLABEL"),
    LOGICAL("IfcLogical", "IFCLOGICAL"),
    IDENTIFIER("IfcIdentifier", "IFCIDENTIFIER"),
    BOOL("IfcBoolean", "IFCBOOLEAN"),
    EXPRESS_REAL("REAL"),
    EXPRESS_BOOL("BOOLEAN"),
    EXPRESS_INTEGER("INTEGER"),
    INTEGER("IfcInteger", "IFCINTEGER"),
    POSITIVE_INTEGER("IfcPositiveInteger", "IFCPOSITIVEINTEGER"),
    DIMENSION_COUNT("IfcDimensionCount", "IFCDIMENSIONCOUNT"),
    LENGTH_MEASURE(true, "IfcLengthMeasure", "IFCLENGTHMEASURE"),
    COUNT_MEASURE(true, "IfcCountMeasure", "IFCCOUNTMEASURE"),
    POSITIVE_LENGTH_MEASURE(true, "IfcPositiveLengthMeasure", "IFCPOSITIVELENGTHMEASURE"),
    NORMALISED_RATIO_MEASURE(true, "IfcNormalisedRatioMeasure", "IFCNORMALISEDRATIOMEASURE"),
    PLANE_ANGLE_MEASURE(true, "IfcPlaneAngleMeasure", "IFCPLANEANGLEMEASURE"),
    AREA_MEASURE(true, "IfcAreaMeasure", "IFCAREAMEASURE"),
    AREA_DENSITY_MEASURE(true, "IfcAreaDensityMeasure"),
    VOLUME_MEASURE(true, "IfcVolumeMeasure", "IFCVOLUMEMEASURE"),
    PRESSURE_MEASURE(true, "IfcPressureMeasure", "IFCPRESSUREMEASURE"),
    POWER_MEASURE(true, "IfcPowerMeasure", "IFCPOWERMEASURE"),
    MASS_MEASURE(true, "IfcMassMeasure", "IFCMASSMEASURE"),
    VOLUMETRIC_FLOW_RATE_MEASURE(true, "IfcVolumetricFlowRateMeasure"),
    RATIO_MEASURE(true, "IfcRatioMeasure"),
    THERMODYNAMIC_TEMPERATURE_MEASURE(true, "IfcThermodynamicTemperatureMeasure"),
    VALUELIST("IfcValue_List"),
    THERMAL_TRANSMITTANCE_MEASURE(
            true, "IfcThermalTransmittanceMeasure", "IFCTHERMALTRANSMITTANCEMEASURE"),
    TIMESTAMP("IfcTimeStamp", "IFCTIMESTAMP"), // REF("bla"), //TODO: HOW DO REF PROPS LOOK LIKE
    REAL("IfcReal", "IFCREAL"),
    UNKNOWN();

    // declaring private variable for getting values
    private final String[] typeUris;
    private final boolean measureType;

    IfcPropertyType(boolean measureType, String... typeUris) {
        this.measureType = measureType;
        this.typeUris = typeUris;
    }

    IfcPropertyType(String... typeUris) {
        this(false, typeUris);
    }

    /**
     * retrieves corresponding enum value for the given propertyType
     *
     * @param propertyType non null
     * @return corresponding Enum Value
     * @throws IllegalArgumentException if no enum value is found
     * @throws NullPointerException if propertyType was null
     */
    public static IfcPropertyType fromString(String propertyType)
            throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(propertyType, "No enum IfcPropertyType for nullvalue");

        for (IfcPropertyType type : IfcPropertyType.values()) {
            for (String enumUri : type.typeUris) {
                if (propertyType.contains("#")) {
                    String[] splitPropertyType = propertyType.split("#");
                    if (splitPropertyType.length == 2 && enumUri.equals(splitPropertyType[1])) {
                        return type;
                    }
                }
                if (enumUri.equals(propertyType)) {
                    return type;
                }
            }
        }
        throw new IllegalArgumentException(
                "No IfcPropertyType enum constant for value: '" + propertyType + "'");
    }

    /** @return true if the PropertyType is considered to be a measurement type */
    public boolean isMeasureType() {
        return measureType;
    }

    /** @return return the correct IfcUnitType, null if there is no corresponding unit */
    public IfcUnitType getUnitType() {
        switch (this) {
            case LENGTH_MEASURE:
            case POSITIVE_LENGTH_MEASURE:
                return IfcUnitType.LENGTHUNIT;
            case MASS_MEASURE:
                return IfcUnitType.MASSUNIT;
            case POWER_MEASURE:
                return IfcUnitType.POWERUNIT;
            case PRESSURE_MEASURE:
                return IfcUnitType.PRESSUREUNIT;
            case AREA_MEASURE:
                return IfcUnitType.AREAUNIT;
            case VOLUME_MEASURE:
                return IfcUnitType.VOLUMEUNIT;
            case THERMAL_TRANSMITTANCE_MEASURE:
                return IfcUnitType.THERMODYNAMICTEMPERATUREUNIT;
            case PLANE_ANGLE_MEASURE:
                return IfcUnitType.PLANEANGLEUNIT;
            case NORMALISED_RATIO_MEASURE:
            default:
                return null;
        }
    }
}