package at.researchstudio.sat.merkmalservice.model.mapping.action.convert;

import at.researchstudio.sat.merkmalservice.model.builder.BuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.ListBuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.mapping.action.BaseAction;
import at.researchstudio.sat.merkmalservice.model.mapping.feature.Feature;

public class ExtractAction extends TransferAction {
    private Feature outputFeature;
    private ExtractionSource source;

    public ExtractAction() {}

    public ExtractAction(String id, ExtractionSource source, Feature outputFeature) {
        super(id);
        this.outputFeature = outputFeature;
        this.source = source;
    }

    public ExtractionSource getSource() {
        return source;
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
            extends ListBuilderScaffold<ExtractAction, Builder<PARENT>, PARENT> {
        ListBuilder(PARENT parent) {
            super(() -> ExtractAction.builder(parent));
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
            extends BaseAction.MyBuilderScaffold<ExtractAction, THIS, PARENT> {
        private Feature.Builder<THIS> outputFeatureBuilder = null;

        public MyBuilderScaffold() {
            super(new ExtractAction());
        }

        public MyBuilderScaffold(PARENT parent) {
            super(parent, new ExtractAction());
        }

        public THIS source(ExtractionSource source) {
            product.source = source;
            return (THIS) this;
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
        public ExtractAction build() {
            super.build();
            if (this.outputFeatureBuilder != null) {
                this.product.outputFeature = outputFeatureBuilder.build();
            }
            return product;
        }
    }
}
