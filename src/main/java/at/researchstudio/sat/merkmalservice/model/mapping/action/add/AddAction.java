package at.researchstudio.sat.merkmalservice.model.mapping.action.add;

import at.researchstudio.sat.merkmalservice.model.mapping.MappingExecutionValue;
import at.researchstudio.sat.merkmalservice.model.mapping.action.FeatureAction;
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends FeatureAction.Builder<AddAction> {
        public Builder() {
            super(new AddAction());
        }

        public Builder value(MappingExecutionValue value) {
            product.value = value;
            return this;
        }

        public Builder value(Double value) {
            product.value = new MappingExecutionValue(value);
            return this;
        }

        public Builder value(Integer value) {
            product.value = new MappingExecutionValue(value);
            return this;
        }

        public Builder value(Boolean value) {
            product.value = new MappingExecutionValue(value);
            return this;
        }

        public Builder value(String value) {
            product.value = new MappingExecutionValue(value);
            return this;
        }

        public Builder id(String id) {
            product.id = id;
            return this;
        }
    }
}
