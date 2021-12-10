package at.researchstudio.sat.merkmalservice.model.mapping.action;

import at.researchstudio.sat.merkmalservice.model.builder.BuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.ListBuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.SubBuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.mapping.action.add.AddActionGroup;
import at.researchstudio.sat.merkmalservice.model.mapping.action.convert.ConvertActionGroup;
import at.researchstudio.sat.merkmalservice.model.mapping.action.delete.DeleteActionGroup;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.ArrayList;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "__typename")
@JsonSubTypes({
    @JsonSubTypes.Type(value = AddActionGroup.class, name = "AddActionGroup"),
    @JsonSubTypes.Type(value = DeleteActionGroup.class, name = "DeleteActionGroup")
})
public abstract class ActionGroup<T extends Action> {
    protected List<T> actions;

    public ActionGroup() {}

    public ActionGroup(List<T> actions) {
        this.actions = new ArrayList<>(actions);
    }

    public List<T> getActions() {
        return actions;
    }

    public static Builder<?> builder() {
        return new Builder<>();
    }

    public static <PARENT extends BuilderScaffold<?, PARENT>> Builder<PARENT> builder(
            PARENT parent) {
        return new Builder<PARENT>(parent);
    }

    public static interface ActionGroupBuilder<PARENT, G extends ActionGroup<A>, A extends Action> {
        PARENT end();

        G build();
    }

    public static <PARENT extends BuilderScaffold<?, PARENT>> ListBuilder<PARENT> listBuilder(
            PARENT parent) {
        return new ListBuilder<>(parent);
    }

    public static class ListBuilder<PARENT extends BuilderScaffold<?, PARENT>>
            extends ListBuilderScaffold<ActionGroup, Builder<PARENT>, PARENT> {
        ListBuilder(PARENT parent) {
            super(() -> ActionGroup.builder(parent));
        }
    }

    public static class Builder<PARENT extends BuilderScaffold<?, PARENT>>
            extends MyBuilderScaffold<Builder<PARENT>, PARENT> {
        Builder(PARENT parent) {
            super(parent);
        }

        Builder() {}
    }

    abstract static class MyBuilderScaffold<
                    THIS extends MyBuilderScaffold<THIS, PARENT>,
                    PARENT extends BuilderScaffold<?, PARENT>>
            extends SubBuilderScaffold<ActionGroup, THIS, PARENT> {

        public MyBuilderScaffold(PARENT parent) {
            super(parent);
        }

        public MyBuilderScaffold() {}

        ActionGroupBuilder<THIS, ?, ?> actionGroupBuilder = null;

        public ConvertActionGroup.Builder<THIS> convertActionGroup() {
            this.actionGroupBuilder = ConvertActionGroup.convertActionGroupBuilder((THIS) this);
            return (ConvertActionGroup.Builder) this.actionGroupBuilder;
        }

        public DeleteActionGroup.Builder<THIS> deleteActionGroup() {
            this.actionGroupBuilder = DeleteActionGroup.deleteActionGroupBuilder((THIS) this);
            return (DeleteActionGroup.Builder) this.actionGroupBuilder;
        }

        public AddActionGroup.Builder<THIS> addActionGroup() {
            this.actionGroupBuilder = AddActionGroup.addActionGroupBuilder((THIS) this);
            return (AddActionGroup.Builder) this.actionGroupBuilder;
        }

        @Override
        public ActionGroup<? extends Action> build() {
            return this.actionGroupBuilder.build();
        }
    }
}
