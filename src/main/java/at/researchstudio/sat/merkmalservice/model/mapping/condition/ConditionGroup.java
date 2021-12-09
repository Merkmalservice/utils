package at.researchstudio.sat.merkmalservice.model.mapping.condition;

import at.researchstudio.sat.merkmalservice.model.builder.BuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.ListBuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.TerminalBuilderScaffold;
import java.util.ArrayList;
import java.util.List;

public class ConditionGroup implements Condition {
    private List<Condition> conditions;
    private Connective connective;
    private String id;

    public ConditionGroup() {}

    public ConditionGroup(String id, List<Condition> conditions, Connective connective) {
        this.conditions = conditions;
        this.connective = connective;
        this.id = id;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public Connective getConnective() {
        return connective;
    }

    public String getId() {
        return id;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static <PARENT extends BuilderScaffold<PARENT, ?>> Builder<PARENT> builder(
            PARENT parent) {
        return new Builder(parent);
    }

    public static class Builder<PARENT extends BuilderScaffold<PARENT, ?>>
            extends TerminalBuilderScaffold<ConditionGroup, Builder<PARENT>, PARENT> {
        private ConditionGroup product;
        private SingleCondition.ListBuilder<Builder<PARENT>> singleConditionListBuilder =
                new SingleCondition.ListBuilder(this);
        private ConditionGroup.ListBuilder<Builder<PARENT>> conditionGroupListBuilder =
                new ConditionGroup.ListBuilder<>(this);

        public Builder() {
            super();
            product = new ConditionGroup();
        }

        public Builder(PARENT parent) {
            super(parent);
            product = new ConditionGroup();
        }

        public ConditionGroup build() {
            prepareConditionGroupList();
            product.conditions.addAll(singleConditionListBuilder.build());
            product.conditions.addAll(conditionGroupListBuilder.build());
            return product;
        }

        public PARENT endConditionGroup() {
            return end();
        }

        public Builder condition(Condition condition) {
            prepareConditionGroupList();
            product.conditions.add(condition);
            return this;
        }

        private void prepareConditionGroupList() {
            if (product.conditions == null) {
                product.conditions = new ArrayList<>();
            }
        }

        public ConditionGroup.Builder<Builder<PARENT>> and() {
            ConditionGroup.Builder<Builder<PARENT>> builder =
                    this.conditionGroupListBuilder.newBuilder();
            builder.connective(Connective.AND);
            return builder;
        }

        public ConditionGroup.Builder<Builder<PARENT>> or() {
            ConditionGroup.Builder<Builder<PARENT>> builder =
                    this.conditionGroupListBuilder.newBuilder();
            builder.connective(Connective.OR);
            return builder;
        }

        public SingleCondition.Builder<Builder<PARENT>> is() {
            return singleConditionListBuilder.newBuilder();
        }

        public Builder connective(Connective connective) {
            product.connective = connective;
            return this;
        }
    }

    public static class ListBuilder<PARENT extends BuilderScaffold<PARENT, ?>>
            extends ListBuilderScaffold<ConditionGroup, Builder<PARENT>, PARENT> {
        public ListBuilder(PARENT parent) {
            super(() -> ConditionGroup.builder(parent));
        }
    }
}
