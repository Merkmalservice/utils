package at.researchstudio.sat.merkmalservice.model;

import java.util.ArrayList;

public class StringFeature extends Feature {
    private final FeatureType featureType = new FeatureType();

    public StringFeature(String name) {
        super(name);
    }

    public StringFeature(String name, ArrayList<FeatureGroup> featureGroups) {
        super(name, featureGroups);
    }

    private class FeatureType {
        private final String type = "STRING";
    }
}
