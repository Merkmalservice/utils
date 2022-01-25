package at.researchstudio.sat.merkmalservice.model;

import java.util.List;

public class JsonModel {
    private List<Feature> features;
    private List<FeatureGroup> featureGroups;
    private List<PropertySet> propertySets;

    public JsonModel(
            List<Feature> features,
            List<FeatureGroup> featureGroups,
            List<PropertySet> propertySets) {
        this.features = features;
        this.featureGroups = featureGroups;
        this.propertySets = propertySets;
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

    public List<PropertySet> getPropertySets() {
        return propertySets;
    }

    public void setPropertySets(List<PropertySet> propertySets) {
        this.propertySets = propertySets;
    }
}
