package at.researchstudio.sat.merkmalservice.model.mapping.action;

import at.researchstudio.sat.merkmalservice.model.builder.BuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.mapping.feature.Feature;

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

    protected abstract static class MyBuilderScaffold<
                    T extends FeatureAction,
                    THIS extends MyBuilderScaffold<T, THIS, PARENT>,
                    PARENT extends BuilderScaffold<?, PARENT>>
            extends BaseAction.MyBuilderScaffold<T, THIS, PARENT> {

        protected Feature.Builder<THIS> featureBuilder = null;

        protected MyBuilderScaffold(T product) {
            super(product);
        }

        public MyBuilderScaffold(PARENT parent, T product) {
            super(parent, product);
        }

        public THIS feature(Feature feature) {
            product.feature = feature;
            return (THIS) this;
        }

        public Feature.Builder<THIS> feature() {
            this.featureBuilder = Feature.builder((THIS) this);
            return this.featureBuilder;
        }

        @Override
        public T build() {
            super.build();
            if (this.featureBuilder != null) {
                this.product.feature = featureBuilder.build();
            }
            return product;
        }
    }
}
