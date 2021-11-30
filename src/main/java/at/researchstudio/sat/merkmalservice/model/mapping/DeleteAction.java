package at.researchstudio.sat.merkmalservice.model.mapping;

import at.researchstudio.sat.merkmalservice.model.mapping.feature.Feature;

public class DeleteAction extends FeatureAction {

    public DeleteAction() {}

    public DeleteAction(String id, Feature feature) {
        super(id, feature);
    }
}
