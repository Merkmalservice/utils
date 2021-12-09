package at.researchstudio.sat.merkmalservice.model.mapping;

import at.researchstudio.sat.merkmalservice.model.Project;
import at.researchstudio.sat.merkmalservice.model.Standard;
import at.researchstudio.sat.merkmalservice.model.builder.BuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.ListBuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.mapping.action.Action;
import at.researchstudio.sat.merkmalservice.model.mapping.action.ActionGroup;
import at.researchstudio.sat.merkmalservice.model.mapping.condition.Condition;
import at.researchstudio.sat.merkmalservice.model.mapping.condition.ConditionGroup;
import at.researchstudio.sat.merkmalservice.model.mapping.condition.Connective;
import at.researchstudio.sat.merkmalservice.model.mapping.condition.SingleCondition;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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

    public static Builder builder() {
        return new Builder();
    }

    public static <PARENT extends BuilderScaffold<PARENT, ?>> Builder<PARENT> builder(
            PARENT parent) {
        return new Builder(parent);
    }

    public abstract static class MyBuilderScaffold<
                    THIS extends BuilderScaffold<THIS, PARENT>,
                    PARENT extends BuilderScaffold<PARENT, ?>>
            extends at.researchstudio.sat.merkmalservice.model.builder.BuilderScaffold<
                    THIS, PARENT> {
        public MyBuilderScaffold(PARENT parent) {
            super(parent);
        }

        public MyBuilderScaffold() {}
    }
    ;

    public static class Builder<PARENT extends BuilderScaffold<PARENT, ?>>
            extends MyBuilderScaffold<Builder<PARENT>, PARENT> {
        private Mapping product = new Mapping();
        private Project.Builder projectBuilder;
        private SingleCondition.Builder<Builder<PARENT>> singleConditionBuilder;
        private ConditionGroup.Builder<Builder<PARENT>> conditionGroupBuilder;

        public Builder(PARENT parent) {
            super(parent);
        }

        public Builder() {}

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
            return product;
        }

        public Builder id(String id) {
            product.id = id;
            return this;
        }

        public Builder name(String name) {
            product.name = name;
            return this;
        }

        public Builder project(Project project) {
            product.project = project;
            return this;
        }

        public Project.Builder project() {
            this.projectBuilder = Project.builder(this);
            return this.projectBuilder;
        }

        public Builder featureSet(Standard featureSet) {
            if (product.featureSets == null) {
                product.featureSets = new ArrayList<>();
            }
            product.featureSets.add(featureSet);
            return this;
        }

        public Builder featureSet(Consumer<Standard.Builder> featureSetConfigurer) {
            Standard.Builder builder = Standard.builder(this);
            featureSetConfigurer.accept(builder);
            return featureSet(builder.build());
        }

        public Builder actionGroup(ActionGroup actionGroup) {
            if (product.actionGroups == null) {
                product.actionGroups = new ArrayList<>();
            }
            product.actionGroups.add(actionGroup);
            return this;
        }

        public Builder actionGroup(Consumer<ActionGroup.Builder> actionGroupConfigurer) {
            ActionGroup.Builder builder = ActionGroup.builder();
            actionGroupConfigurer.accept(builder);
            return actionGroup(builder.build());
        }

        public ConditionGroup.Builder<Builder<PARENT>> ifAllOf() {
            this.conditionGroupBuilder = ConditionGroup.builder(this);
            this.conditionGroupBuilder.connective(Connective.AND);
            return this.conditionGroupBuilder;
        }

        public ConditionGroup.Builder<Builder<PARENT>> ifOneOf() {
            this.conditionGroupBuilder = ConditionGroup.builder(this);
            this.conditionGroupBuilder.connective(Connective.OR);
            return this.conditionGroupBuilder;
        }

        public SingleCondition.Builder<Builder<PARENT>> ifIs() {
            this.singleConditionBuilder = SingleCondition.builder(this);
            return this.singleConditionBuilder;
        }
    }

    public static class ListBuilder<PARENT extends BuilderScaffold<PARENT, ?>>
            extends ListBuilderScaffold<Mapping, Builder<PARENT>, PARENT> {
        public ListBuilder(PARENT parent) {
            super(() -> Mapping.builder(parent));
        }
    }
}
