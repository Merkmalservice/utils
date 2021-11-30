package at.researchstudio.sat.merkmalservice.model.mapping;

import at.researchstudio.sat.merkmalservice.model.mapping.feature.Feature;

public class AddAction extends FeatureAction {
    private MappingExecutionValue value;

    public AddAction() {}

    public AddAction(String id, Feature feature, MappingExecutionValue value) {
        super(id, feature);
        this.value = value;
    }

    public MappingExecutionValue getValue() {
        return value;
    }
}
