package at.researchstudio.sat.merkmalservice.model.mapping.action;

import at.researchstudio.sat.merkmalservice.model.IBuilder;
import at.researchstudio.sat.merkmalservice.model.builder.BuilderScaffold;
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

    public static Builder builder() {
        return new Builder();
    }


    public static interface ActionGroupBuilder<PARENT>{
        PARENT end();
    }

    abstract static class MyBuilderScaffold<
                    THIS extends MyBuilderScaffold<THIS, PARENT>,
                    PARENT extends BuilderScaffold<?, PARENT>> implements Finisher {

        ActionGroupBuilder<THIS> builder = null;

        public ConvertActionGroup.Builder<Finisher> convertActionGroup() {
            this.actionGroupBuilder<Finisher> = ConvertActionGroup.convertActionGroupBuilder((Finisher) this);
            return (ConvertActionGroup.Builder<Finisher>) this.actionGroupBuilder;
        }

        public DeleteActionGroup.Builder deleteActionGroup() {
            this.builder = new DeleteActionGroup.Builder();
            return (DeleteActionGroup.Builder) this.builder;
        }

        public AddActionGroup.Builder addActionGroup() {
            this.builder = new AddActionGroup.Builder();
            return (AddActionGroup.Builder) this.builder;
        }

        @Override
        public ActionGroup<? extends Action> build() {
            return builder.build();
        }
    }

    public static class BuilderBase<
                    G extends ActionGroup<A>, A extends Action, B extends BuilderBase<?, ?, B>>
            implements IBuilder<G> {
        protected G product;

        protected BuilderBase(G product) {
            this.product = product;
        }

        public BuilderBase<?, ?, B> action(A action) {
            if (product.actions == null) {
                product.actions = new ArrayList<>();
            }
            product.actions.add(action);
            return this;
        }

        @Override
        public G build() {
            return product;
        }
    }
}
