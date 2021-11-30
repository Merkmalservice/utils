package at.researchstudio.sat.merkmalservice.model;

import java.util.ArrayList;

public class BooleanFeature extends Feature {
    private FeatureType featureType = new FeatureType();

    public BooleanFeature() {}

    public BooleanFeature(String name) {
        super(name);
    }

    public BooleanFeature(String name, ArrayList<FeatureGroup> featureGroups) {
        super(name, featureGroups);
    }

    private class FeatureType {
        private final String type = "BOOLEAN";
    }
}
