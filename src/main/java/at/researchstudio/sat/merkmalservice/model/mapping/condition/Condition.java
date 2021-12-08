package at.researchstudio.sat.merkmalservice.model.mapping.condition;

import at.researchstudio.sat.merkmalservice.model.IBuilder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "__typename")
@JsonSubTypes({
    @JsonSubTypes.Type(value = SingleCondition.class, name = "SingleCondition"),
    @JsonSubTypes.Type(value = ConditionGroup.class, name = "ConditionGroup")
})
public interface Condition {

    static Builder builder() {
        return new Builder();
    }

    static class Builder implements IBuilder<Condition> {
        private IBuilder<? extends Condition> builder;

        public SingleCondition.Builder singleCondition() {
            this.builder = new Condition.Builder();
            return (SingleCondition.Builder) this.builder;
        }

        public ConditionGroup.Builder groupCondition() {
            this.builder = new ConditionGroup.Builder();
            return (ConditionGroup.Builder) this.builder;
        }

        @Override
        public Condition build() {
            return this.builder.build();
        }
    }
}
