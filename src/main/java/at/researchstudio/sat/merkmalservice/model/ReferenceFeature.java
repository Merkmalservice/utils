package at.researchstudio.sat.merkmalservice.model;

public class ReferenceFeature extends Feature {
    private final FeatureType featureType = new FeatureType();

    public ReferenceFeature(String name) {
        super(name);
    }


    private class FeatureType {
        private final String type = "REFERENCE";
    }
}
