package at.researchstudio.sat.merkmalservice.model.mapping.action.delete;

import at.researchstudio.sat.merkmalservice.model.builder.BuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.ListBuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.mapping.action.FeatureAction;
import at.researchstudio.sat.merkmalservice.model.mapping.feature.Feature;

public class DeleteAction extends FeatureAction {

    public DeleteAction() {}

    public DeleteAction(String id, Feature feature) {
        super(id, feature);
    }

    public static Builder<?> builder() {
        return new Builder<>();
    }

    public static <PARENT extends BuilderScaffold<?, PARENT>> Builder<PARENT> builder(
            PARENT parent) {
        return new Builder<PARENT>(parent);
    }

    public static <PARENT extends BuilderScaffold<?, PARENT>> ListBuilder<PARENT> listBuilder(
            PARENT parent) {
        return new ListBuilder<>(parent);
    }

    public static class ListBuilder<PARENT extends BuilderScaffold<?, PARENT>>
            extends ListBuilderScaffold<DeleteAction, Builder<PARENT>, PARENT> {
        ListBuilder(PARENT parent) {
            super(() -> DeleteAction.builder(parent));
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
            extends FeatureAction.MyBuilderScaffold<DeleteAction, THIS, PARENT> {
        public MyBuilderScaffold() {
            super(new DeleteAction());
        }

        public MyBuilderScaffold(PARENT parent) {
            super(parent, new DeleteAction());
        }
    }
}
