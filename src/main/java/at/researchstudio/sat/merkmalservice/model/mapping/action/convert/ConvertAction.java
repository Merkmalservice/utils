package at.researchstudio.sat.merkmalservice.model.mapping.action.convert;

import at.researchstudio.sat.merkmalservice.model.mapping.action.BaseAction;
import at.researchstudio.sat.merkmalservice.model.mapping.feature.Feature;
import java.util.function.Consumer;

public class ConvertAction extends BaseAction {

    private Feature inputFeature;

    private Feature outputFeature;

    public ConvertAction() {}

    public ConvertAction(String id, Feature inputFeature, Feature outputFeature) {
        super(id);
        this.inputFeature = inputFeature;
        this.outputFeature = outputFeature;
    }

    public Feature getInputFeature() {
        return inputFeature;
    }

    public Feature getOutputFeature() {
        return outputFeature;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends BaseAction.Builder<ConvertAction> {

        public Builder() {
            super(new ConvertAction());
        }

        public Builder inputFeature(Feature inputFeature) {
            product.inputFeature = inputFeature;
            return this;
        }

        public Builder outputFeature(Feature outputFeature) {
            product.outputFeature = outputFeature;
            return this;
        }

        public Builder inputFeature(Consumer<Feature.Builder> featureConfigurer) {
            Feature.Builder builder = Feature.builder();
            featureConfigurer.accept(builder);
            return inputFeature(builder.build());
        }

        public Builder outputFeature(Consumer<Feature.Builder> featureConfigurer) {
            Feature.Builder builder = Feature.builder();
            featureConfigurer.accept(builder);
            return outputFeature(builder.build());
        }
    }
}
