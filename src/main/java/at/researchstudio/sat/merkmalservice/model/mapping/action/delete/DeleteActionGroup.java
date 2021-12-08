package at.researchstudio.sat.merkmalservice.model.mapping.action.delete;

import at.researchstudio.sat.merkmalservice.model.mapping.action.ActionGroup;
import java.util.List;
import java.util.function.Consumer;

public class DeleteActionGroup extends ActionGroup<DeleteAction> {
    public DeleteActionGroup() {}

    public DeleteActionGroup(List<DeleteAction> actions) {
        super(actions);
    }

    public static class Builder extends BuilderBase<DeleteActionGroup, DeleteAction, Builder> {
        public Builder() {
            super(new DeleteActionGroup());
        }

        public Builder action(Consumer<DeleteAction.Builder> deleteActionConfigurer) {
            DeleteAction.Builder builder = DeleteAction.builder();
            deleteActionConfigurer.accept(builder);
            action(builder.build());
            return this;
        }
    }
}
