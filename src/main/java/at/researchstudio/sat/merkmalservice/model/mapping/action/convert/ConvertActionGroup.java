package at.researchstudio.sat.merkmalservice.model.mapping.action.convert;

import at.researchstudio.sat.merkmalservice.model.builder.BuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.ListBuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.SubBuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.mapping.MappingExecutionValue;
import at.researchstudio.sat.merkmalservice.model.mapping.action.ActionGroup;
import java.util.List;

public class ConvertActionGroup extends ActionGroup<ConvertAction> {
    private MappingExecutionValue addToPropertySet;

    public ConvertActionGroup() {}

    public ConvertActionGroup(String id, List<ConvertAction> actions) {
        super(id, actions);
    }

    public MappingExecutionValue getAddToPropertySet() {
        return addToPropertySet;
    }

    public static Builder<?> convertActionGroupBuilder() {
        return new Builder();
    }

    public static <PARENT extends BuilderScaffold<?, PARENT>>
            Builder<PARENT> convertActionGroupBuilder(PARENT parent) {
        return new Builder<>(parent);
    }

    public static <PARENT extends BuilderScaffold<?, PARENT>>
            ListBuilder<PARENT> convertActionGroupListBuilder(PARENT parent) {
        return new ListBuilder<>(parent);
    }

    public static class ListBuilder<PARENT extends BuilderScaffold<?, PARENT>>
            extends ListBuilderScaffold<ConvertActionGroup, Builder<PARENT>, PARENT> {
        ListBuilder(PARENT parent) {
            super(() -> ConvertActionGroup.convertActionGroupBuilder(parent));
        }
    }

    public static class Builder<PARENT extends BuilderScaffold<?, PARENT>>
            extends MyBuilderScaffold<Builder<PARENT>, PARENT> {
        public Builder(PARENT parent) {
            super(parent);
        }

        public Builder() {}
    }

    abstract static class MyBuilderScaffold<
                    THIS extends MyBuilderScaffold<THIS, PARENT>,
                    PARENT extends BuilderScaffold<?, PARENT>>
            extends SubBuilderScaffold<ConvertActionGroup, THIS, PARENT>
            implements ActionGroupBuilder<PARENT, ConvertActionGroup, ConvertAction> {

        private ConvertAction.ListBuilder<THIS> convertActionListBuilder =
                ConvertAction.listBuilder((THIS) this);
        private String id = null;
        private MappingExecutionValue addToPropertySet = null;

        public MyBuilderScaffold(PARENT parent) {
            super(parent);
        }

        public MyBuilderScaffold() {}

        public ConvertAction.Builder<THIS> action() {
            return this.convertActionListBuilder.newBuilder();
        }

        public THIS id(String id) {
            this.id = id;
            return (THIS) this;
        }

        public THIS propertySetName(String value) {
            this.addToPropertySet = new MappingExecutionValue(value);
            return (THIS) this;
        }

        public THIS propertySetId(String id) {
            this.addToPropertySet = new MappingExecutionValue(id, "PropertySet");
            return (THIS) this;
        }

        @Override
        public ConvertActionGroup build() {
            return new ConvertActionGroup(id, this.convertActionListBuilder.build());
        }
    }
}
