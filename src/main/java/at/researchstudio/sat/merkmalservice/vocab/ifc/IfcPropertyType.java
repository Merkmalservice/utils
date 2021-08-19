package at.researchstudio.sat.merkmalservice.vocab.ifc;

import java.util.Objects;

public enum IfcPropertyType {
    AREA_DENSITY_MEASURE(true, "IfcAreaDensityMeasure"),
    AREA_MEASURE(true, "IfcAreaMeasure", "IFCAREAMEASURE"),
    BOOL("IfcBoolean", "IFCBOOLEAN"),
    COUNT_MEASURE(true, "IfcCountMeasure", "IFCCOUNTMEASURE"),
    DIMENSION_COUNT("IfcDimensionCount", "IFCDIMENSIONCOUNT"),
    ELECTRIC_CURRENT_MEASURE(true, "IfcElectricCurrentMeasure"),
    EXPRESS_BOOL("BOOLEAN"),
    EXPRESS_INTEGER("INTEGER"),
    EXPRESS_REAL("REAL"),
    FORCE_MEASURE(true, "IfcForceMeasure"),
    IDENTIFIER("IfcIdentifier", "IFCIDENTIFIER"),
    ILLUMINANCE_MEASURE(true, "IfcIlluminanceMeasure"),
    INTEGER("IfcInteger", "IFCINTEGER"),
    LABEL("IfcLabel", "IFCLABEL"),
    LENGTH_MEASURE(true, "IfcLengthMeasure", "IFCLENGTHMEASURE"),
    LINEAR_FORCE_MEASURE(true, "IfcLinearForceMeasure"),
    LOGICAL("IfcLogical", "IFCLOGICAL"),
    LUMINOUS_FLUX_MEASURE(true, "IfcLuminousFluxMeasure"),
    LUMINOUS_INTESITY_MEASURE(true, "IfcLuminousIntensityMeasure"),
    MASS_DENSITY_MEASURE(true, "IfcMassDensityMeasure"),
    MASS_MEASURE(true, "IfcMassMeasure", "IFCMASSMEASURE"),
    MOMENT_OF_INERTIA_MEASURE(true, "IfcMomentOfInertiaMeasure"),
    NORMALISED_RATIO_MEASURE(true, "IfcNormalisedRatioMeasure", "IFCNORMALISEDRATIOMEASURE"),
    PLANAR_FORCE_MEASURE(true, "IfcPlanarForceMeasure"),
    PLANE_ANGLE_MEASURE(true, "IfcPlaneAngleMeasure", "IFCPLANEANGLEMEASURE"),
    POSITIVE_INTEGER("IfcPositiveInteger", "IFCPOSITIVEINTEGER"),
    POSITIVE_LENGTH_MEASURE(true, "IfcPositiveLengthMeasure", "IFCPOSITIVELENGTHMEASURE"),
    POWER_MEASURE(true, "IfcPowerMeasure", "IFCPOWERMEASURE"),
    PRESSURE_MEASURE(true, "IfcPressureMeasure", "IFCPRESSUREMEASURE"),
    RATIO_MEASURE(true, "IfcRatioMeasure"),
    REAL("IfcReal", "IFCREAL"),
    TEXT("IfcText", "IFCTEXT"),
    THERMAL_TRANSMITTANCE_MEASURE(
            true, "IfcThermalTransmittanceMeasure", "IFCTHERMALTRANSMITTANCEMEASURE"),
    THERMODYNAMIC_TEMPERATURE_MEASURE(true, "IfcThermodynamicTemperatureMeasure"),
    TIMESTAMP("IfcTimeStamp", "IFCTIMESTAMP"), // REF("bla"), //TODO: HOW DO REF PROPS LOOK LIKE
    VALUELIST("IfcValue_List"),
    VOLUME_MEASURE(true, "IfcVolumeMeasure", "IFCVOLUMEMEASURE"),
    VOLUMETRIC_FLOW_RATE_MEASURE(true, "IfcVolumetricFlowRateMeasure"),

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
