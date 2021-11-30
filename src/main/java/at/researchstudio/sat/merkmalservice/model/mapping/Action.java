package at.researchstudio.sat.merkmalservice.model.mapping;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "__typename")
@JsonSubTypes({
    @JsonSubTypes.Type(value = AddAction.class, name = "AddAction"),
    @JsonSubTypes.Type(value = DeleteAction.class, name = "DeleteAction")
})
public interface Action {}
