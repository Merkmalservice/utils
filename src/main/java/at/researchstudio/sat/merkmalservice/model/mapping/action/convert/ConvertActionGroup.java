package at.researchstudio.sat.merkmalservice.model.mapping.action.convert;

import at.researchstudio.sat.merkmalservice.model.mapping.action.ActionGroup;
import java.util.List;
import java.util.function.Consumer;

public class ConvertActionGroup extends ActionGroup<ConvertAction> {

    public ConvertActionGroup() {}

    public ConvertActionGroup(List<ConvertAction> actions) {
        super(actions);
    }

    public static class Builder extends BuilderBase<ConvertActionGroup, ConvertAction, Builder> {
        public Builder() {
            super(new ConvertActionGroup());
        }

        public Builder action(Consumer<ConvertAction.Builder> addActionConfigurer) {
            ConvertAction.Builder builder = ConvertAction.builder();
            addActionConfigurer.accept(builder);
            action(builder.build());
            return this;
        }
    }
}
