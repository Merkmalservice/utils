package at.researchstudio.sat.merkmalservice.model;

import java.util.List;

public class JsonModel {
    private List<Feature> features;
    private List<FeatureGroup> featureGroups;

    public JsonModel(List<Feature> features, List<FeatureGroup> featureGroups) {
        this.features = features;
        this.featureGroups = featureGroups;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public List<FeatureGroup> getFeatureGroups() {
        return featureGroups;
    }

    public void setFeatureGroups(List<FeatureGroup> featureGroups) {
        this.featureGroups = featureGroups;
    }
}
