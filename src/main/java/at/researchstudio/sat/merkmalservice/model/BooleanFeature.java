package at.researchstudio.sat.merkmalservice.model;

import java.util.ArrayList;

public class BooleanFeature extends Feature {
    private final FeatureType featureType;

    public BooleanFeature(String name) {
        super(name);
        this.featureType = new FeatureType();
    }

    public BooleanFeature(String name, ArrayList<FeatureGroup> featureGroups) {
        super(name, featureGroups);
        this.featureType = new FeatureType();
    }

    private class FeatureType {
        private final String type = "BOOLEAN";
    }
}
