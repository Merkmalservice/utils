package at.researchstudio.sat.merkmalservice.model;

import java.util.ArrayList;

public class ReferenceFeature extends Feature {
    private final FeatureType featureType = new FeatureType();

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
