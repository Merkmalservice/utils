package at.researchstudio.sat.merkmalservice.model.mapping;

import at.researchstudio.sat.merkmalservice.model.mapping.feature.Feature;

public abstract class FeatureAction extends BaseAction {
    private Feature feature;

    public FeatureAction() {}

    public FeatureAction(String id, Feature feature) {
        super(id);
        this.feature = feature;
    }

    public Feature getFeature() {
        return feature;
    }
}
