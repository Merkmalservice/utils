package at.researchstudio.sat.merkmalservice.model.mapping.action.delete;

import at.researchstudio.sat.merkmalservice.model.mapping.action.FeatureAction;
import at.researchstudio.sat.merkmalservice.model.mapping.feature.Feature;

public class DeleteAction extends FeatureAction {

    public DeleteAction() {}

    public DeleteAction(String id, Feature feature) {
        super(id, feature);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends FeatureAction.Builder<DeleteAction> {
        public Builder() {
            super(new DeleteAction());
        }
    }
}
