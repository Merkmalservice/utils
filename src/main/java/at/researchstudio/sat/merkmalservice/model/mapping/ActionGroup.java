package at.researchstudio.sat.merkmalservice.model.mapping;

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
    private List<T> actions;

    public ActionGroup() {}

    public ActionGroup(List<T> actions) {
        this.actions = new ArrayList<>(actions);
    }

    public List<T> getActions() {
        return actions;
    }
}
