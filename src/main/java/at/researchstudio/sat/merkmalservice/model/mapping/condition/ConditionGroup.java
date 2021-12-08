package at.researchstudio.sat.merkmalservice.model.mapping.condition;

import at.researchstudio.sat.merkmalservice.model.IBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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

    public static class Builder implements IBuilder<ConditionGroup> {
        private ConditionGroup product;

        public Builder() {
            product = new ConditionGroup();
        }

        public ConditionGroup build() {
            return product;
        }

        public Builder condition(Condition condition) {
            if (product.conditions == null) {
                product.conditions = new ArrayList<>();
            }
            product.conditions.add(condition);
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

        public Builder connective(Connective connective) {
            product.connective = connective;
            return this;
        }

        public Builder conjunction() {
            return connective(Connective.AND);
        }

        public Builder disjunction() {
            return connective(Connective.OR);
        }
    }
}
