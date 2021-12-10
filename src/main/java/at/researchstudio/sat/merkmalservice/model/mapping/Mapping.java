package at.researchstudio.sat.merkmalservice.model.mapping;

import at.researchstudio.sat.merkmalservice.model.Project;
import at.researchstudio.sat.merkmalservice.model.Standard;
import at.researchstudio.sat.merkmalservice.model.builder.BuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.ListBuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.SubBuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.mapping.action.Action;
import at.researchstudio.sat.merkmalservice.model.mapping.action.ActionGroup;
import at.researchstudio.sat.merkmalservice.model.mapping.action.add.AddActionGroup;
import at.researchstudio.sat.merkmalservice.model.mapping.action.convert.ConvertActionGroup;
import at.researchstudio.sat.merkmalservice.model.mapping.action.delete.DeleteActionGroup;
import at.researchstudio.sat.merkmalservice.model.mapping.condition.Condition;
import at.researchstudio.sat.merkmalservice.model.mapping.condition.ConditionGroup;
import at.researchstudio.sat.merkmalservice.model.mapping.condition.Connective;
import at.researchstudio.sat.merkmalservice.model.mapping.condition.SingleCondition;
import java.util.ArrayList;
import java.util.List;

public class Mapping {
    private String id;
    private String name;
    private Project project;
    private List<Standard> featureSets;
    private Condition condition;
    private List<ActionGroup<? extends Action>> actionGroups;

    public Mapping() {}

    public Mapping(
            String id,
            String name,
            Project project,
            List<Standard> featureSets,
            Condition condition,
            List<ActionGroup<? extends Action>> actionGroups) {
        this.id = id;
        this.name = name;
        this.project = project;
        this.featureSets = featureSets;
        this.condition = condition;
        this.actionGroups = actionGroups;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Project getProject() {
        return project;
    }

    public List<Standard> getFeatureSets() {
        return featureSets;
    }

    public Condition getCondition() {
        return condition;
    }

    public List<ActionGroup<? extends Action>> getActionGroups() {
        return actionGroups;
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

    public static class Builder<PARENT extends BuilderScaffold<?, PARENT>>
            extends MyBuilderScaffold<Builder<PARENT>, PARENT> {
        public Builder(PARENT parent) {
            super(parent);
        }

        public Builder() {}
    }

    public static class ListBuilder<PARENT extends BuilderScaffold<?, PARENT>>
            extends ListBuilderScaffold<Mapping, Builder<PARENT>, PARENT> {
        ListBuilder(PARENT parent) {
            super(() -> Mapping.builder(parent));
        }
    }

    abstract static class MyBuilderScaffold<
                    THIS extends MyBuilderScaffold<THIS, PARENT>,
                    PARENT extends BuilderScaffold<?, PARENT>>
            extends SubBuilderScaffold<Mapping, THIS, PARENT> {
        private Mapping product = new Mapping();
        private Project.Builder<THIS> projectBuilder;
        private SingleCondition.Builder<THIS> singleConditionBuilder;
        private ConditionGroup.Builder<THIS> conditionGroupBuilder;
        private Standard.Builder<THIS> standardBuilder = null;
        private AddActionGroup.ListBuilder<THIS> addActionGroupListBuilder =
                AddActionGroup.addActionGroupListBuilder((THIS) this);
        private DeleteActionGroup.ListBuilder<THIS> deleteActionGroupListBuilder =
                DeleteActionGroup.deleteActionGroupListBuilder((THIS) this);
        private ConvertActionGroup.ListBuilder<THIS> convertActionGroupListBuilder =
                ConvertActionGroup.convertActionGroupListBuilder((THIS) this);

        public MyBuilderScaffold(PARENT parent) {
            super(parent);
        }

        public MyBuilderScaffold() {}

        public PARENT endMapping() {
            return end();
        }

        public Mapping build() {
            if (projectBuilder != null) {
                product.project = projectBuilder.build();
            }
            if (singleConditionBuilder != null) {
                product.condition = singleConditionBuilder.build();
            }
            initializeActionGroupsList();
            product.actionGroups.addAll(convertActionGroupListBuilder.build());
            product.actionGroups.addAll(deleteActionGroupListBuilder.build());
            product.actionGroups.addAll(addActionGroupListBuilder.build());
            return product;
        }

        public THIS id(String id) {
            product.id = id;
            return (THIS) this;
        }

        public THIS name(String name) {
            product.name = name;
            return (THIS) this;
        }

        public THIS project(Project project) {
            product.project = project;
            return (THIS) this;
        }

        public Project.Builder<THIS> project() {
            this.projectBuilder = Project.builder((THIS) this);
            return this.projectBuilder;
        }

        public THIS featureSet(Standard featureSet) {
            if (product.featureSets == null) {
                product.featureSets = new ArrayList<>();
            }
            product.featureSets.add(featureSet);
            return (THIS) this;
        }

        public Standard.Builder<THIS> featureSet() {
            this.standardBuilder = Standard.builder((THIS) this);
            return this.standardBuilder;
        }

        public THIS actionGroup(ActionGroup actionGroup) {
            initializeActionGroupsList();
            product.actionGroups.add(actionGroup);
            return (THIS) this;
        }

        public DeleteActionGroup.Builder<THIS> deleteActionGroup() {
            return this.deleteActionGroupListBuilder.newBuilder();
        }

        public AddActionGroup.Builder<THIS> addActionGroup() {
            return this.addActionGroupListBuilder.newBuilder();
        }

        public ConvertActionGroup.Builder<THIS> convertActionGroup() {
            return this.convertActionGroupListBuilder.newBuilder();
        }

        private void initializeActionGroupsList() {
            if (product.actionGroups == null) {
                product.actionGroups = new ArrayList<>();
            }
        }

        public ConditionGroup.Builder<THIS> allMatch() {
            this.conditionGroupBuilder = ConditionGroup.builder((THIS) this);
            this.conditionGroupBuilder.connective(Connective.AND);
            return this.conditionGroupBuilder;
        }

        public ConditionGroup.Builder<THIS> anyMatch() {
            this.conditionGroupBuilder = ConditionGroup.builder((THIS) this);
            this.conditionGroupBuilder.connective(Connective.OR);
            return this.conditionGroupBuilder;
        }

        public SingleCondition.Builder<THIS> matches() {
            this.singleConditionBuilder = SingleCondition.builder((THIS) this);
            return this.singleConditionBuilder;
        }
    }
}
