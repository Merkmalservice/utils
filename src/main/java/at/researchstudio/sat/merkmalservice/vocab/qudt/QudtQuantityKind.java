package at.researchstudio.sat.merkmalservice.vocab.qudt;

import at.researchstudio.sat.merkmalservice.vocab.ifc.IfcUnitType;
import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class QudtQuantityKind {
    private static final Logger logger =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final String AREA = "http://qudt.org/vocab/quantitykind/Area";
    public static final String ANGLE = "http://qudt.org/vocab/quantitykind/Angle";
    public static final String LUMINOUS_INTENSITY =
            "http://qudt.org/vocab/quantitykind/LuminousIntensity";
    public static final String ELECTRIC_CURRENT =
            "http://qudt.org/vocab/quantitykind/ElectricCurrent";
    public static final String MASS_DENSITY = "http://qudt.org/vocab/quantitykind/MassDensity";
    public static final String ILLUMINANCE = "http://qudt.org/vocab/quantitykind/Illuminance";
    public static final String FORCE = "http://qudt.org/vocab/quantitykind/Force";
    public static final String MOMENT_OF_INERTIA =
            "http://qudt.org/vocab/quantitykind/MomentOfInertia";
    public static final String MASS = "http://qudt.org/vocab/quantitykind/Mass";
    public static final String PRESSURE = "http://qudt.org/vocab/quantitykind/Pressure";
    public static final String LUMINOUS_FLUX = "http://qudt.org/vocab/quantitykind/LuminousFlux";
    public static final String VOLUME_FLOW_RATE =
            "http://qudt.org/vocab/quantitykind/VolumeFlowRate";
    public static final String POWER = "http://qudt.org/vocab/quantitykind/Power";
    public static final String THERMODYNAMIC_TEMPERATURE =
            "http://qudt.org/vocab/quantitykind/ThermodynamicTemperature";
    public static final String VOLUME = "http://qudt.org/vocab/quantitykind/Volume";
    public static final String LENGTH = "http://qudt.org/vocab/quantitykind/Length";
    public static final String WIDTH = "http://qudt.org/vocab/quantitykind/Width";
    public static final String HEIGHT = "http://qudt.org/vocab/quantitykind/Height";
    public static final String RADIUS = "http://qudt.org/vocab/quantitykind/Radius";
    public static final String DIAMETER = "http://qudt.org/vocab/quantitykind/Diameter";
    public static final String DEPTH = "http://qudt.org/vocab/quantitykind/Depth";
    public static final String THICKNESS = "http://qudt.org/vocab/quantitykind/Thickness";
    public static final String THERMALCONDUCTIVITY =
            "http://qudt.org/vocab/quantitykind/ThermalConductivity";
    public static final String FORCEPERAREA = "http://qudt.org/vocab/quantitykind/ForcePerArea";
    public static final String DENSITY = "http://qudt.org/vocab/quantitykind/Density";
    public static final String DIMENSIONLESS = "http://qudt.org/vocab/quantitykind/Dimensionless";

    /**
     * Extracts the appropriate QudtQuantityKind String for the given propertyName
     *
     * <p>Simple check for Keywords such as length/height etc.
     *
     * @param propertyName
     * @return Matching QudtQuantityKind String
     * @throws IllegalArgumentException if there is no matching QudtUnit String for the given prefix
     *     and measure
     * @throws NullPointerException if the propertyName is null
     */
    public static String extractQuantityKindFromPropertyName(String propertyName)
            throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(propertyName, "No QudtQuantityKind for nullvalue");
        // TODO: This is really simple and probably not going to be too useful,
        // but it will do the trick
        // for now
        String lowerCaseName = propertyName.toLowerCase();
        if (lowerCaseName.contains("höhe") || lowerCaseName.contains("height")) {
            return HEIGHT;
        }
        if (lowerCaseName.contains("winkel") || lowerCaseName.contains("angle")) {
            return ANGLE;
        }
        if (lowerCaseName.contains("volume")) {
            return VOLUME;
        }
        if (lowerCaseName.contains("länge") || lowerCaseName.contains("length")) {
            return LENGTH;
        }
        if (lowerCaseName.contains("breite") || lowerCaseName.contains("width")) {
            return WIDTH;
        }
        if (lowerCaseName.contains("durchmesser") || lowerCaseName.contains("diameter")) {
            return DIAMETER;
        }
        if (lowerCaseName.contains("radius")) {
            return RADIUS;
        }
        if (lowerCaseName.contains("fläche") || lowerCaseName.contains("area")) {
            return AREA;
        }
        if (lowerCaseName.contains("tiefe") || lowerCaseName.contains("depth")) {
            return DEPTH;
        }
        if (lowerCaseName.contains("dicke") || lowerCaseName.contains("stärke")) {
            return THICKNESS;
        }
        throw new IllegalArgumentException(
                "No QudtQuantityKind found for propertyName '" + propertyName + "'");
    }

    private static final Map<String, IfcUnitType> QUANTITY_KIND_TO_IFC_UNIT_TYPE;

    static {
        QUANTITY_KIND_TO_IFC_UNIT_TYPE = new HashMap<>();
        putWithSameValue(
                QUANTITY_KIND_TO_IFC_UNIT_TYPE,
                IfcUnitType.LENGTHUNIT,
                "http://qudt.org/vocab/quantitykind/Length",
                "http://qudt.org/vocab/quantitykind/FundamentalLatticeVector",
                "http://qudt.org/vocab/quantitykind/AngularReciprocalLatticeVector",
                "http://qudt.org/vocab/quantitykind/Wavelength",
                "http://qudt.org/vocab/quantitykind/Width",
                "http://qudt.org/vocab/quantitykind/BurgersVector",
                "http://qudt.org/vocab/quantitykind/Radius",
                "http://qudt.org/vocab/quantitykind/ParticlePositionVector",
                "http://qudt.org/vocab/quantitykind/Displacement",
                "http://qudt.org/vocab/quantitykind/CartesianCoordinates",
                "http://qudt.org/vocab/quantitykind/ElectronRadius",
                "http://qudt.org/vocab/quantitykind/LondonPenetrationDepth",
                "http://qudt.org/vocab/quantitykind/PathLength",
                "http://qudt.org/vocab/quantitykind/Half-ValueThickness",
                "http://qudt.org/vocab/quantitykind/EquilibriumPositionVectorOfIon",
                "http://qudt.org/vocab/quantitykind/MeanFreePath",
                "http://qudt.org/vocab/quantitykind/Distance",
                "http://qudt.org/vocab/quantitykind/ElectronMeanFreePath",
                "http://qudt.org/vocab/quantitykind/RadiusOfCurvature",
                "http://qudt.org/vocab/quantitykind/LatticeVector",
                "http://qudt.org/vocab/quantitykind/SolidStateDiffusionLength",
                "http://qudt.org/vocab/quantitykind/CoherenceLength",
                "http://qudt.org/vocab/quantitykind/RadialDistance",
                "http://qudt.org/vocab/quantitykind/Breadth",
                "http://qudt.org/vocab/quantitykind/PositionVector",
                "http://qudt.org/vocab/quantitykind/SoundParticleDisplacement",
                "http://qudt.org/vocab/quantitykind/SpatialSummationFunction",
                "http://qudt.org/vocab/quantitykind/Slowing-DownLength",
                "http://qudt.org/vocab/quantitykind/DiffusionLength",
                "http://qudt.org/vocab/quantitykind/NuclearRadius",
                "http://qudt.org/vocab/quantitykind/NozzleThroatDiameter",
                "http://qudt.org/vocab/quantitykind/DiffusionCoefficientForFluenceRate",
                "http://qudt.org/vocab/quantitykind/Thickness",
                "http://qudt.org/vocab/quantitykind/Height",
                "http://qudt.org/vocab/quantitykind/Diameter",
                "http://qudt.org/vocab/quantitykind/PhononMeanFreePath",
                "http://qudt.org/vocab/quantitykind/LatticePlaneSpacing",
                "http://qudt.org/vocab/quantitykind/Depth",
                "http://qudt.org/vocab/quantitykind/DisplacementVectorOfIon",
                "http://qudt.org/vocab/quantitykind/MeanLinearRange",
                "http://qudt.org/vocab/quantitykind/Altitude",
                "http://qudt.org/vocab/quantitykind/MigrationLength",
                "http://qudt.org/vocab/quantitykind/InitialNozzleThroatDiameter",
                "http://qudt.org/vocab/quantitykind/FundamentalReciprocalLatticeVector");
        putWithSameValue(
                QUANTITY_KIND_TO_IFC_UNIT_TYPE,
                IfcUnitType.AREAUNIT,
                "http://qudt.org/vocab/quantitykind/Area",
                "http://qudt.org/vocab/quantitykind/TotalCross-Section",
                "http://qudt.org/vocab/quantitykind/Slowing-DownArea",
                "http://qudt.org/vocab/quantitykind/CartesianArea",
                "http://qudt.org/vocab/quantitykind/Cross-Section",
                "http://qudt.org/vocab/quantitykind/AtomicAttenuationCoefficient",
                "http://qudt.org/vocab/quantitykind/DiffusionArea",
                "http://qudt.org/vocab/quantitykind/MigrationArea",
                "http://qudt.org/vocab/quantitykind/EquivalentAbsorptionArea");
        putWithSameValue(
                QUANTITY_KIND_TO_IFC_UNIT_TYPE,
                IfcUnitType.VOLUMEUNIT,
                "http://qudt.org/vocab/quantitykind/Volume",
                "http://qudt.org/vocab/quantitykind/DryVolume",
                "http://qudt.org/vocab/quantitykind/FirstMomentOfArea",
                "http://qudt.org/vocab/quantitykind/LiquidVolume");
        putWithSameValue(
                QUANTITY_KIND_TO_IFC_UNIT_TYPE,
                IfcUnitType.MASSUNIT,
                "http://qudt.org/vocab/quantitykind/Mass",
                "http://qudt.org/vocab/quantitykind/GROSS-LIFT-OFF-WEIGHT",
                "http://qudt.org/vocab/quantitykind/NOMINAL-ASCENT-PROPELLANT-MASS",
                "http://qudt.org/vocab/quantitykind/CONTROL-MASS",
                "http://qudt.org/vocab/quantitykind/FLIGHT-PERFORMANCE-RESERVE-PROPELLANT-MASS",
                "http://qudt.org/vocab/quantitykind/AtomicMass",
                "http://qudt.org/vocab/quantitykind/EffectiveMass",
                "http://qudt.org/vocab/quantitykind/CENTER-OF-MASS",
                "http://qudt.org/vocab/quantitykind/RestMass",
                "http://qudt.org/vocab/quantitykind/MASS-MARGIN",
                "http://qudt.org/vocab/quantitykind/DRY-MASS",
                "http://qudt.org/vocab/quantitykind/MASS-GROWTH-ALLOWANCE",
                "http://qudt.org/vocab/quantitykind/MassExcess",
                "http://qudt.org/vocab/quantitykind/CONTRACT-END-ITEM-SPECIFICATION-MASS",
                "http://qudt.org/vocab/quantitykind/MassDefect",
                "http://qudt.org/vocab/quantitykind/INERT-MASS",
                "http://qudt.org/vocab/quantitykind/RESERVE-MASS",
                "http://qudt.org/vocab/quantitykind/MASS-DELIVERED",
                "http://qudt.org/vocab/quantitykind/PREDICTED-MASS",
                "http://qudt.org/vocab/quantitykind/TARGET-BOGIE-MASS",
                "http://qudt.org/vocab/quantitykind/MolecularMass",
                "http://qudt.org/vocab/quantitykind/RelativeMassDefect");
        putWithSameValue(
                QUANTITY_KIND_TO_IFC_UNIT_TYPE,
                IfcUnitType.TIMEUNIT,
                "http://qudt.org/vocab/quantitykind/Time",
                "http://qudt.org/vocab/quantitykind/IgnitionIntervalTime",
                "http://qudt.org/vocab/quantitykind/SpecificImpulseByWeight");
        putWithSameValue(
                QUANTITY_KIND_TO_IFC_UNIT_TYPE,
                IfcUnitType.FREQUENCYUNIT,
                "http://qudt.org/vocab/quantitykind/Frequency",
                "http://qudt.org/vocab/quantitykind/SpecificActivity",
                "http://qudt.org/vocab/quantitykind/Activity",
                "http://qudt.org/vocab/quantitykind/Vorticity",
                "http://qudt.org/vocab/quantitykind/StochasticProcess",
                "http://qudt.org/vocab/quantitykind/AngularVelocity",
                "http://qudt.org/vocab/quantitykind/MorbidityRate",
                "http://qudt.org/vocab/quantitykind/MortalityRate",
                "http://qudt.org/vocab/quantitykind/AngularFrequency",
                "http://qudt.org/vocab/quantitykind/LarmorAngularFrequency",
                "http://qudt.org/vocab/quantitykind/CyclotronAngularFrequency");
        putWithSameValue(
                QUANTITY_KIND_TO_IFC_UNIT_TYPE,
                IfcUnitType.PLANEANGLEUNIT,
                "http://qudt.org/vocab/quantitykind/PlaneAngle",
                "http://qudt.org/vocab/quantitykind/Angle");
        putWithSameValue(
                QUANTITY_KIND_TO_IFC_UNIT_TYPE,
                IfcUnitType.THERMODYNAMICTEMPERATUREUNIT,
                "http://qudt.org/vocab/quantitykind/DewPointTemperature",
                "http://qudt.org/vocab/quantitykind/Temperature");

        QUANTITY_KIND_TO_IFC_UNIT_TYPE.put(
                "http://qudt.org/vocab/quantitykind/AbsorbedDose", IfcUnitType.ABSORBEDDOSEUNIT);
        putWithSameValue(
                QUANTITY_KIND_TO_IFC_UNIT_TYPE,
                IfcUnitType.AMOUNTOFSUBSTANCEUNIT,
                "http://qudt.org/vocab/quantitykind/AmountOfSubstancePerUnitVolume",
                "http://qudt.org/vocab/quantitykind/AmountOfSubstancePerUnitMass",
                "http://qudt.org/vocab/quantitykind/AmountOfSubstancePerUnitVolume");
        QUANTITY_KIND_TO_IFC_UNIT_TYPE.put(
                "http://qudt.org/vocab/quantitykind/DoseEquivalent",
                IfcUnitType.DOSEEQUIVALENTUNIT);

        // TODO: add other unit types
    }

    private static void putWithSameValue(
            Map<String, IfcUnitType> map, IfcUnitType value, String... keys) {
        Arrays.stream(keys)
                .forEach(
                        k -> {
                            if (map.containsKey(k)) {
                                logger.warn(
                                        "overwriting mapping {} -> {} with {} -> {}",
                                        new Object[] {k, map.get(k), k, value});
                            }
                            map.put(k, value);
                        });
    }

    public static IfcUnitType mapToIfcUnitType(String qudtQuantityKind) {
        return QUANTITY_KIND_TO_IFC_UNIT_TYPE.get(qudtQuantityKind);
    }
}
