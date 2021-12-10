package at.researchstudio.sat.merkmalservice.model.mapping.action.convert;

import at.researchstudio.sat.merkmalservice.model.builder.BuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.ListBuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.mapping.action.BaseAction;
import at.researchstudio.sat.merkmalservice.model.mapping.feature.Feature;

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

    public static Builder<?> builder() {
        return new Builder<>();
    }

    public static <PARENT extends BuilderScaffold<?, PARENT>> Builder<PARENT> builder(
            PARENT parent) {
        return new Builder<>(parent);
    }

    public static <PARENT extends BuilderScaffold<?, PARENT>> ListBuilder<PARENT> listBuilder(
            PARENT parent) {
        return new ListBuilder<>(parent);
    }

    public static class ListBuilder<PARENT extends BuilderScaffold<?, PARENT>>
            extends ListBuilderScaffold<ConvertAction, Builder<PARENT>, PARENT> {
        ListBuilder(PARENT parent) {
            super(() -> ConvertAction.builder(parent));
        }
    }

    public static class Builder<PARENT extends BuilderScaffold<?, PARENT>>
            extends MyBuilderScaffold<Builder<PARENT>, PARENT> {
        Builder() {}

        Builder(PARENT parent) {
            super(parent);
        }
    }

    abstract static class MyBuilderScaffold<
                    THIS extends MyBuilderScaffold<THIS, PARENT>,
                    PARENT extends BuilderScaffold<?, PARENT>>
            extends BaseAction.MyBuilderScaffold<ConvertAction, THIS, PARENT> {
        private Feature.Builder<THIS> inputFeatureBuilder = null;
        private Feature.Builder<THIS> outputFeatureBuilder = null;

        public MyBuilderScaffold() {
            super(new ConvertAction());
        }

        public MyBuilderScaffold(PARENT parent) {
            super(parent, new ConvertAction());
        }

        public THIS inputFeature(Feature inputFeature) {
            product.inputFeature = inputFeature;
            return (THIS) this;
        }

        public Feature.Builder<THIS> inputFeature() {
            this.inputFeatureBuilder = Feature.builder((THIS) this);
            return this.inputFeatureBuilder;
        }

        public THIS outputFeature(Feature outputFeature) {
            product.outputFeature = outputFeature;
            return (THIS) this;
        }

        public Feature.Builder<THIS> outputFeature() {
            this.outputFeatureBuilder = Feature.builder((THIS) this);
            return this.outputFeatureBuilder;
        }

        @Override
        public ConvertAction build() {
            super.build();
            if (this.inputFeatureBuilder != null) {
                this.product.inputFeature = inputFeatureBuilder.build();
            }
            if (this.outputFeatureBuilder != null) {
                this.product.outputFeature = outputFeatureBuilder.build();
            }
            return product;
        }
    }
}
