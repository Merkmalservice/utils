package at.researchstudio.sat.merkmalservice.model.mapping.action;

import at.researchstudio.sat.merkmalservice.model.mapping.feature.Feature;
import java.util.function.Consumer;

public abstract class FeatureAction extends BaseAction {
    protected Feature feature;

    public FeatureAction() {}

    public FeatureAction(String id, Feature feature) {
        super(id);
        this.feature = feature;
    }

    public Feature getFeature() {
        return feature;
    }

    public static class Builder<T extends FeatureAction> extends BaseAction.Builder<T> {
        protected Builder(T product) {
            super(product);
        }

        public Builder<?> feature(Feature feature) {
            product.feature = feature;
            return this;
        }

        public Builder<?> feature(Consumer<Feature.Builder> featureConfigurer) {
            Feature.Builder builder = Feature.builder();
            featureConfigurer.accept(builder);
            return feature(builder.build());
        }
    }
}
