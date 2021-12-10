package at.researchstudio.sat.merkmalservice.model.mapping.action.add;

import at.researchstudio.sat.merkmalservice.model.builder.BuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.ListBuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.SubBuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.mapping.MappingExecutionValue;
import at.researchstudio.sat.merkmalservice.model.mapping.action.ActionGroup;
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

    public static Builder<?> addActionGroupBuilder() {
        return new Builder<>();
    }

    public static <PARENT extends BuilderScaffold<?, PARENT>> Builder<PARENT> addActionGroupBuilder(
            PARENT parent) {
        return new Builder<>(parent);
    }

    public static <PARENT extends BuilderScaffold<?, PARENT>>
            ListBuilder<PARENT> addActionGroupListBuilder(PARENT parent) {
        return new ListBuilder<>(parent);
    }

    public static class ListBuilder<PARENT extends BuilderScaffold<?, PARENT>>
            extends ListBuilderScaffold<AddActionGroup, Builder<PARENT>, PARENT> {
        ListBuilder(PARENT parent) {
            super(() -> AddActionGroup.addActionGroupBuilder(parent));
        }
    }

    public static class Builder<PARENT extends BuilderScaffold<?, PARENT>>
            extends MyBuilderScaffold<Builder<PARENT>, PARENT> {
        Builder(PARENT parent) {
            super(parent);
        }

        Builder() {}
    }

    abstract static class MyBuilderScaffold<
                    THIS extends MyBuilderScaffold<THIS, PARENT>,
                    PARENT extends BuilderScaffold<?, PARENT>>
            extends SubBuilderScaffold<AddActionGroup, THIS, PARENT>
            implements ActionGroupBuilder<PARENT, AddActionGroup, AddAction> {
        private AddAction.ListBuilder<THIS> addActionListBuilder =
                AddAction.listBuilder((THIS) this);
        private MappingExecutionValue value = null;

        public MyBuilderScaffold(PARENT parent) {
            super(parent);
        }

        public MyBuilderScaffold() {}

        @Override
        public AddActionGroup build() {
            return new AddActionGroup(addActionListBuilder.build(), value);
        }

        public THIS value(Object value) {
            this.value = MappingExecutionValue.of(value);
            return (THIS) this;
        }

        public AddAction.Builder<THIS> addAction() {
            return addActionListBuilder.newBuilder();
        }
    }
}
