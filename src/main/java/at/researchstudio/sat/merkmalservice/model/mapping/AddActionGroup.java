package at.researchstudio.sat.merkmalservice.model.mapping;

import java.util.List;

public class AddActionGroup extends ActionGroup<AddAction> {
    private MappingExecutionValue value;

    public AddActionGroup() {}

    public AddActionGroup(List<AddAction> actions, MappingExecutionValue value) {
        super(actions);
        this.value = value;
    }

    public MappingExecutionValue getValue() {
        return value;
    }
}
