package at.researchstudio.sat.merkmalservice.model;

import java.util.List;

public class StringFeature extends Feature {
    private FeatureType featureType = new FeatureType();

    public StringFeature() {}

    public StringFeature(String name) {
        super(name);
    }

    public StringFeature(String name, List<FeatureGroup> featureGroups) {
        super(name, featureGroups);
    }

    private class FeatureType {
        private final String type = "STRING";
    }
}
