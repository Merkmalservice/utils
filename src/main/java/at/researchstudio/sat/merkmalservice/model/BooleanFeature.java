package at.researchstudio.sat.merkmalservice.model;

public class BooleanFeature extends Feature {
    private final FeatureType featureType;

    public BooleanFeature(String name) {
        super(name);
        this.featureType = new FeatureType();
    }

    private class FeatureType {
        private final String type = "BOOLEAN";
    }
}
