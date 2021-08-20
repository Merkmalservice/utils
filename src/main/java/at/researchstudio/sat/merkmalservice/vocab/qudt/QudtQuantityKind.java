package at.researchstudio.sat.merkmalservice.vocab.qudt;

import java.util.Objects;

public abstract class QudtQuantityKind {
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
}
