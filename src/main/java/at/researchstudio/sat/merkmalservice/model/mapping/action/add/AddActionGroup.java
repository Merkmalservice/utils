package at.researchstudio.sat.merkmalservice.model.mapping.action.add;

import at.researchstudio.sat.merkmalservice.model.mapping.MappingExecutionValue;
import at.researchstudio.sat.merkmalservice.model.mapping.action.ActionGroup;
import java.util.List;
import java.util.function.Consumer;

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

    public static class Builder extends BuilderBase<AddActionGroup, AddAction, Builder> {
        public Builder() {
            super(new AddActionGroup());
        }

        public Builder value(MappingExecutionValue value) {
            product.value = value;
            return this;
        }

        public Builder action(Consumer<AddAction.Builder> addActionConfigurer) {
            AddAction.Builder builder = AddAction.builder();
            addActionConfigurer.accept(builder);
            action(builder.build());
            return this;
        }
    }
}
