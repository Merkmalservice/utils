package at.researchstudio.sat.merkmalservice.model;

import java.util.ArrayList;

public abstract class Feature {
    private final String name;
    private ArrayList<FeatureGroup> featureGroups;

    public Feature(String name) {
        this.name = name;
    }

    public Feature(String name, ArrayList<FeatureGroup> featureGroups) {
        this.name = name;
        this.featureGroups = featureGroups;
    }

    public String getName() {
        return name;
    }

    public ArrayList<FeatureGroup> getFeatureGroups() {
        return featureGroups;
    }

    public void setFeatureGroups(ArrayList<FeatureGroup> featureGroups) {
        this.featureGroups = featureGroups;
    }
}
