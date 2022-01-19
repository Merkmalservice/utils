package at.researchstudio.sat.merkmalservice.model.mapping.action.add;

import at.researchstudio.sat.merkmalservice.model.builder.BuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.ListBuilderScaffold;
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
            extends ListBuilderScaffold<AddAction, Builder<PARENT>, PARENT> {
        ListBuilder(PARENT parent) {
            super(() -> AddAction.builder(parent));
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
            extends FeatureAction.MyBuilderScaffold<AddAction, THIS, PARENT> {
        public MyBuilderScaffold() {
            super(new AddAction());
        }

        public MyBuilderScaffold(PARENT parent) {
            super(parent, new AddAction());
        }

        public THIS value(MappingExecutionValue value) {
            product.value = value;
            return (THIS) this;
        }

        public THIS value(Double value) {
            product.value = new MappingExecutionValue(value);
            return (THIS) this;
        }

        public THIS value(Integer value) {
            product.value = new MappingExecutionValue(value);
            return (THIS) this;
        }

        public THIS value(Boolean value) {
            product.value = new MappingExecutionValue(value);
            return (THIS) this;
        }

        public THIS value(String value) {
            product.value = new MappingExecutionValue(value);
            return (THIS) this;
        }

        public THIS idValue(String id, String graphqlType) {
            product.value = new MappingExecutionValue(id, graphqlType);
            return (THIS) this;
        }

        public THIS idValue(String id) {
            product.value = new MappingExecutionValue(id, "unknownGraphQlType");
            return (THIS) this;
        }

        public THIS id(String id) {
            product.id = id;
            return (THIS) this;
        }
    }
}
