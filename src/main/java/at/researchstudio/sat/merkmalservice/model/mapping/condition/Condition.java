package at.researchstudio.sat.merkmalservice.model.mapping.condition;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "__typename")
@JsonSubTypes({
    @JsonSubTypes.Type(value = SingleCondition.class, name = "SingleCondition"),
    @JsonSubTypes.Type(value = ConditionGroup.class, name = "ConditionGroup")
})
public interface Condition {}
