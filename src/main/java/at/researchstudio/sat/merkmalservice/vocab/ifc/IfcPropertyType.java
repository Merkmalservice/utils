package at.researchstudio.sat.merkmalservice.vocab.ifc;

import java.util.Objects;

public enum IfcPropertyType {
    AREA_DENSITY_MEASURE(true, null, "IfcAreaDensityMeasure"), // TODO WHICH UNIT TYPE IS THIS?
    AREA_MEASURE(true, IfcUnitType.AREAUNIT, "IfcAreaMeasure", "IFCAREAMEASURE"),
    BOOL("IfcBoolean", "IFCBOOLEAN"),
    COUNT_MEASURE(
            true, null, "IfcCountMeasure", "IFCCOUNTMEASURE"), // TODO WHICH UNIT TYPE IS THIS?
    DIMENSION_COUNT("IfcDimensionCount", "IFCDIMENSIONCOUNT"),
    ELECTRIC_CURRENT_MEASURE(true, IfcUnitType.ELECTRICCURRENTUNIT, "IfcElectricCurrentMeasure"),
    ELECTRIC_VOLTAGE_MEASURE(true, IfcUnitType.ELECTRIVOLTAGEUNIT, "IfcElectricVoltageMeasure"),
    EXPRESS_BOOL("BOOLEAN"),
    EXPRESS_INTEGER("INTEGER"),
    EXPRESS_REAL("REAL"),
    FORCE_MEASURE(true, IfcUnitType.FORCEUNIT, "IfcForceMeasure"),
    IDENTIFIER("IfcIdentifier", "IFCIDENTIFIER"),
    ILLUMINANCE_MEASURE(true, IfcUnitType.ILLUMINANCEUNIT, "IfcIlluminanceMeasure"),
    INTEGER("IfcInteger", "IFCINTEGER"),
    LABEL("IfcLabel", "IFCLABEL"),
    LENGTH_MEASURE(true, IfcUnitType.LENGTHUNIT, "IfcLengthMeasure", "IFCLENGTHMEASURE"),
    LINEAR_FORCE_MEASURE(true, IfcUnitType.LINEARFORCEUNIT, "IfcLinearForceMeasure"),
    LOGICAL("IfcLogical", "IFCLOGICAL"),
    LUMINOUS_FLUX_MEASURE(true, IfcUnitType.LUMINOUSFLUXUNIT, "IfcLuminousFluxMeasure"),
    LUMINOUS_INTESITY_MEASURE(
            true, IfcUnitType.LUMINOUSINTENSITYUNIT, "IfcLuminousIntensityMeasure"),
    MASS_DENSITY_MEASURE(true, IfcUnitType.MASSDENSITYUNIT, "IfcMassDensityMeasure"),
    MASS_MEASURE(true, IfcUnitType.MASSUNIT, "IfcMassMeasure", "IFCMASSMEASURE"),
    MOMENT_OF_INERTIA_MEASURE(true, IfcUnitType.MOMENTOFINERTIAUNIT, "IfcMomentOfInertiaMeasure"),
    NORMALISED_RATIO_MEASURE(
            true,
            null,
            "IfcNormalisedRatioMeasure",
            "IFCNORMALISEDRATIOMEASURE"), // TODO WHICH UNIT TYPE IS THIS?
    PLANAR_FORCE_MEASURE(true, IfcUnitType.PLANARFORCEUNIT, "IfcPlanarForceMeasure"),
    PLANE_ANGLE_MEASURE(
            true, IfcUnitType.PLANEANGLEUNIT, "IfcPlaneAngleMeasure", "IFCPLANEANGLEMEASURE"),
    POSITIVE_INTEGER("IfcPositiveInteger", "IFCPOSITIVEINTEGER"),
    POSITIVE_LENGTH_MEASURE(
            true, IfcUnitType.LENGTHUNIT, "IfcPositiveLengthMeasure", "IFCPOSITIVELENGTHMEASURE"),
    POWER_MEASURE(true, IfcUnitType.POWERUNIT, "IfcPowerMeasure", "IFCPOWERMEASURE"),
    PRESSURE_MEASURE(true, IfcUnitType.PRESSUREUNIT, "IfcPressureMeasure", "IFCPRESSUREMEASURE"),
    RATIO_MEASURE(true, null, "IfcRatioMeasure"), // TODO WHICH UNIT TYPE IS THIS?
    REAL("IfcReal", "IFCREAL"),
    TEXT("IfcText", "IFCTEXT"),
    THERMAL_TRANSMITTANCE_MEASURE(
            true,
            IfcUnitType.THERMALTRANSMITTANCEUNIT,
            "IfcThermalTransmittanceMeasure",
            "IFCTHERMALTRANSMITTANCEMEASURE"),
    THERMODYNAMIC_TEMPERATURE_MEASURE(
            true, null, "IfcThermodynamicTemperatureMeasure"), // TODO WHICH UNIT TYPE IS THIS?
    TIMESTAMP("IfcTimeStamp", "IFCTIMESTAMP"), // REF("bla"), //TODO: HOW DO REF PROPS LOOK LIKE
    VALUELIST("IfcValue_List"),
    VOLUME_MEASURE(true, IfcUnitType.VOLUMEUNIT, "IfcVolumeMeasure", "IFCVOLUMEMEASURE"),
    VOLUMETRIC_FLOW_RATE_MEASURE(
            true, IfcUnitType.VOLUMETRICFLOWRATEUNIT, "IfcVolumetricFlowRateMeasure"),

    UNKNOWN();

    // declaring private variable for getting values
    private final String[] typeUris;
    private final IfcUnitType unitType;
    private final boolean measureType;

    IfcPropertyType(boolean measureType, IfcUnitType unitType, String... typeUris) {
        this.unitType = unitType;
        this.measureType = measureType;
        this.typeUris = typeUris;
    }

    IfcPropertyType(String... typeUris) {
        this(false, null, typeUris);
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
                    if (splitPropertyType.length == 2
                            && enumUri.equalsIgnoreCase(splitPropertyType[1])) {
                        return type;
                    }
                }
                if (enumUri.equalsIgnoreCase(propertyType)) {
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
        return this.unitType;
    }
}
