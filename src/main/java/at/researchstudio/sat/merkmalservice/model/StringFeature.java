package at.researchstudio.sat.merkmalservice.model;

public class StringFeature extends Feature {
    private final FeatureType featureType = new FeatureType();

    public StringFeature(String name) {
        super(name);
    }

    private class FeatureType {
        private final String type = "STRING";
    }
}
