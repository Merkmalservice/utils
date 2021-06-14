package at.researchstudio.sat.merkmalservice.model;

import java.util.ArrayList;

public abstract class Feature {
    private final String name;
    private String description;
    private ArrayList<FeatureGroup> featureGroups;

    public Feature(String name) {
        this.name = name;
    }

    public Feature(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Feature(String name, ArrayList<FeatureGroup> featureGroups) {
        this.name = name;
        this.featureGroups = featureGroups;
    }

    public Feature(String name, String description, ArrayList<FeatureGroup> featureGroups) {
        this.name = name;
        this.description = description;
        this.featureGroups = featureGroups;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<FeatureGroup> getFeatureGroups() {
        return featureGroups;
    }

    public void setFeatureGroups(ArrayList<FeatureGroup> featureGroups) {
        this.featureGroups = featureGroups;
    }
}
