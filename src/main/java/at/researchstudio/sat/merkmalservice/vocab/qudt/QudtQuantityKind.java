package at.researchstudio.sat.merkmalservice.vocab.qudt;

import java.util.Objects;

public abstract class QudtQuantityKind {
    public static final String AREA = "http://qudt.org/vocab/quantitykind/Area";
    public static final String ANGLE = "http://qudt.org/vocab/quantitykind/Angle";
    public static final String VOLUME = "http://qudt.org/vocab/quantitykind/Volume";
    public static final String LENGTH = "http://qudt.org/vocab/quantitykind/Length";
    public static final String WIDTH = "http://qudt.org/vocab/quantitykind/Width";
    public static final String HEIGHT = "http://qudt.org/vocab/quantitykind/Height";
    public static final String RADIUS = "http://qudt.org/vocab/quantitykind/Radius";
    public static final String DIAMETER = "http://qudt.org/vocab/quantitykind/Diameter";
    public static final String DEPTH = "http://qudt.org/vocab/quantitykind/Depth";
    public static final String THICKNESS = "http://qudt.org/vocab/quantitykind/Thickness";
    public static final String DIMENSIONLESS = "http://qudt.org/vocab/quantitykind/Dimensionless";

    /**
     * Extracts the appropriate QudtQuantityKind String for the given propertyName
     *
     * <p>Simple check for Keywords such as length/height etc.
     *
     * @param propertyName
     * @throws IllegalArgumentException if there is no matching QudtUnit String for the given prefix
     *     and measure
     * @throws NullPointerException if the propertyName is null
     * @return Matching QudtQuantityKind String
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
