package at.researchstudio.sat.merkmalservice.model.mapping;

import java.util.List;

public class DeleteActionGroup extends ActionGroup<DeleteAction> {
    public DeleteActionGroup() {}

    public DeleteActionGroup(List<DeleteAction> actions) {
        super(actions);
    }
}
