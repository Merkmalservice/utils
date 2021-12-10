package at.researchstudio.sat.merkmalservice.model.mapping.condition;

import at.researchstudio.sat.merkmalservice.model.builder.BuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.ListBuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.SubBuilderScaffold;
import java.util.ArrayList;
import java.util.List;

public class ConditionGroup implements Condition {
    private List<Condition> conditions;
    private Connective connective;
    private String id;

    public ConditionGroup() {}

    public ConditionGroup(String id, List<Condition> conditions, Connective connective) {
        this.conditions = conditions;
        this.connective = connective;
        this.id = id;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public Connective getConnective() {
        return connective;
    }

    public String getId() {
        return id;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static <PARENT extends BuilderScaffold<?, PARENT>> Builder<PARENT> builder(
            PARENT parent) {
        return new Builder(parent);
    }

    public static <PARENT extends BuilderScaffold<?, PARENT>> ListBuilder<PARENT> listBuilder(
            PARENT parent) {
        return new ListBuilder<>(parent);
    }

    public static class Builder<PARENT extends BuilderScaffold<?, PARENT>>
            extends MyBuilderScaffold<Builder<PARENT>, PARENT> {
        Builder() {}

        Builder(PARENT parent) {
            super(parent);
        }
    }

    public static class ListBuilder<PARENT extends BuilderScaffold<?, PARENT>>
            extends ListBuilderScaffold<ConditionGroup, Builder<PARENT>, PARENT> {
        private ListBuilder(PARENT parent) {
            super(() -> ConditionGroup.builder(parent));
        }
    }

    abstract static class MyBuilderScaffold<
                    THIS extends MyBuilderScaffold<THIS, PARENT>,
                    PARENT extends BuilderScaffold<?, PARENT>>
            extends SubBuilderScaffold<ConditionGroup, THIS, PARENT> {
        private ConditionGroup product;
        private SingleCondition.ListBuilder<THIS> singleConditionListBuilder =
                SingleCondition.listBuilder((THIS) this);
        private ConditionGroup.ListBuilder<THIS> conditionGroupListBuilder =
                ConditionGroup.listBuilder((THIS) this);

        private MyBuilderScaffold() {
            super();
            product = new ConditionGroup();
        }

        private MyBuilderScaffold(PARENT parent) {
            super(parent);
            product = new ConditionGroup();
        }

        public ConditionGroup build() {
            prepareConditionGroupList();
            product.conditions.addAll(singleConditionListBuilder.build());
            product.conditions.addAll(conditionGroupListBuilder.build());
            return product;
        }

        public PARENT endConditionGroup() {
            return end();
        }

        public THIS condition(Condition condition) {
            prepareConditionGroupList();
            product.conditions.add(condition);
            return (THIS) this;
        }

        private void prepareConditionGroupList() {
            if (product.conditions == null) {
                product.conditions = new ArrayList<>();
            }
        }

        public ConditionGroup.Builder<THIS> allMatch() {
            ConditionGroup.Builder<THIS> builder = this.conditionGroupListBuilder.newBuilder();
            builder.connective(Connective.AND);
            return builder;
        }

        public ConditionGroup.Builder<THIS> anyMatch() {
            ConditionGroup.Builder<THIS> builder = this.conditionGroupListBuilder.newBuilder();
            builder.connective(Connective.OR);
            return builder;
        }

        public SingleCondition.Builder<THIS> matches() {
            return singleConditionListBuilder.newBuilder();
        }

        public THIS connective(Connective connective) {
            product.connective = connective;
            return (THIS) this;
        }
    }
}
