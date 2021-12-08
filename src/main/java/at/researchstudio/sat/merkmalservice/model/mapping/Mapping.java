package at.researchstudio.sat.merkmalservice.model.mapping;

import at.researchstudio.sat.merkmalservice.model.IBuilder;
import at.researchstudio.sat.merkmalservice.model.Project;
import at.researchstudio.sat.merkmalservice.model.Standard;
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

    public static class Builder implements IBuilder<Mapping> {
        private Mapping product = new Mapping();

        public Builder() {}

        public Mapping build() {
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

        public Builder project(Consumer<Project.Builder> projectConfigurer) {
            Project.Builder pb = Project.builder();
            projectConfigurer.accept(pb);
            product.project = pb.build();
            return this;
        }

        public Builder featureSet(Standard featureSet) {
            if (product.featureSets == null) {
                product.featureSets = new ArrayList<>();
            }
            product.featureSets.add(featureSet);
            return this;
        }

        public Builder featureSet(Consumer<Standard.Builder> featureSetConfigurer) {
            Standard.Builder builder = Standard.builder();
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

        public Builder condition(Condition condition) {
            product.condition = condition;
            return this;
        }

        public Builder condition(Consumer<Condition.Builder> conditionConfigurer) {
            Condition.Builder builder = Condition.builder();
            conditionConfigurer.accept(builder);
            return condition(builder.build());
        }

        public Builder and(Consumer<ConditionGroup.Builder> groupConditionConfigurer) {
            ConditionGroup.Builder builder = new ConditionGroup.Builder();
            groupConditionConfigurer.accept(builder.connective(Connective.AND));
            return condition(builder.build());
        }

        public Builder or(Consumer<ConditionGroup.Builder> groupConditionConfigurer) {
            ConditionGroup.Builder builder = new ConditionGroup.Builder();
            groupConditionConfigurer.accept(builder.connective(Connective.OR));
            return condition(builder.build());
        }

        public Builder is(Consumer<SingleCondition.Builder> singleConditionConfigurer) {
            SingleCondition.Builder builder = new SingleCondition.Builder();
            singleConditionConfigurer.accept(builder);
            return condition(builder.build());
        }
    }
}
