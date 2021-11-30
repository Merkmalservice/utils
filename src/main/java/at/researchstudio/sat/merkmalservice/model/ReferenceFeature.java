package at.researchstudio.sat.merkmalservice.model;

import java.util.ArrayList;

public class ReferenceFeature extends Feature {
    private FeatureType featureType = new FeatureType();

    public ReferenceFeature() {}

    public ReferenceFeature(String name) {
        super(name);
    }

    public ReferenceFeature(String name, ArrayList<FeatureGroup> featureGroups) {
        super(name, featureGroups);
    }

    private class FeatureType {
        private final String type = "REFERENCE";
    }
}
