package at.researchstudio.sat.merkmalservice.model.mapping.action.delete;

import at.researchstudio.sat.merkmalservice.model.builder.BuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.ListBuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.SubBuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.mapping.action.ActionGroup;
import java.util.List;

public class DeleteActionGroup extends ActionGroup<DeleteAction> {
    public DeleteActionGroup() {}

    public DeleteActionGroup(List<DeleteAction> actions) {
        super(actions);
    }

    public static Builder<?> deleteActionGroupBuilder() {
        return new Builder<>();
    }

    public static <PARENT extends BuilderScaffold<?, PARENT>>
            Builder<PARENT> deleteActionGroupBuilder(PARENT parent) {
        return new Builder<>(parent);
    }

    public static <PARENT extends BuilderScaffold<?, PARENT>>
            ListBuilder<PARENT> deleteActionGroupListBuilder(PARENT parent) {
        return new ListBuilder<>(parent);
    }

    public static class ListBuilder<PARENT extends BuilderScaffold<?, PARENT>>
            extends ListBuilderScaffold<DeleteActionGroup, Builder<PARENT>, PARENT> {
        ListBuilder(PARENT parent) {
            super(() -> DeleteActionGroup.deleteActionGroupBuilder(parent));
        }
    }

    public static class Builder<PARENT extends BuilderScaffold<?, PARENT>>
            extends MyBuilderScaffold<Builder<PARENT>, PARENT> {
        public Builder(PARENT parent) {
            super(parent);
        }

        public Builder() {}
    }

    abstract static class MyBuilderScaffold<
                    THIS extends MyBuilderScaffold<THIS, PARENT>,
                    PARENT extends BuilderScaffold<?, PARENT>>
            extends SubBuilderScaffold<DeleteActionGroup, THIS, PARENT>
            implements ActionGroupBuilder<PARENT, DeleteActionGroup, DeleteAction> {
        private DeleteAction.ListBuilder<THIS> deleteActionListBuilder =
                DeleteAction.listBuilder((THIS) this);

        public MyBuilderScaffold(PARENT parent) {
            super(parent);
        }

        public MyBuilderScaffold() {}

        public DeleteAction.Builder<THIS> deleteAction() {
            return deleteActionListBuilder.newBuilder();
        }

        @Override
        public DeleteActionGroup build() {
            return new DeleteActionGroup(deleteActionListBuilder.build());
        }
    }
}
