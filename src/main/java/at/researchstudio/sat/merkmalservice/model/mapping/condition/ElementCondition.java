package at.researchstudio.sat.merkmalservice.model.mapping.condition;

import at.researchstudio.sat.merkmalservice.model.builder.BuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.ListBuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.SubBuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.mapping.MappingExecutionValue;
import at.researchstudio.sat.merkmalservice.model.mapping.MappingPredicate;
import at.researchstudio.sat.merkmalservice.model.mapping.action.convert.ExtractionSource;
import java.util.Optional;

public class ElementCondition implements Condition {
    private String id;
    private ExtractionSource source;
    private MappingPredicate predicate;
    private MappingExecutionValue value;

    public ElementCondition() {}

    public ElementCondition(
            String id,
            ExtractionSource source,
            MappingPredicate predicate,
            MappingExecutionValue value) {
        this.id = id;
        this.source = source;
        this.predicate = predicate;
        this.value = value;
    }

    public ExtractionSource getSource() {
        return source;
    }

    public Optional<MappingExecutionValue> getValue() {
        return Optional.ofNullable(value);
    }

    public MappingPredicate getPredicate() {
        return predicate;
    }

    public String getId() {
        return id;
    }

    public static Builder<?> builder() {
        return new Builder<>();
    }

    public static <PARENT extends BuilderScaffold<?, PARENT>> Builder<PARENT> builder(
            PARENT parent) {
        return new Builder(parent);
    }

    public static <PARENT extends BuilderScaffold<?, PARENT>> ListBuilder<PARENT> listBuilder(
            PARENT parent) {
        return new ListBuilder<PARENT>(parent);
    }

    public static class ListBuilder<PARENT extends BuilderScaffold<?, PARENT>>
            extends ListBuilderScaffold<
                    ElementCondition, ElementCondition.Builder<PARENT>, PARENT> {
        private ListBuilder(PARENT parent) {
            super(() -> ElementCondition.builder(parent));
        }
    }

    public static class Builder<PARENT extends BuilderScaffold<?, PARENT>>
            extends MyBuilderScaffold<Builder<PARENT>, PARENT> {
        private Builder(PARENT parent) {
            super(parent);
        }

        private Builder() {}
    }

    abstract static class MyBuilderScaffold<
                    THIS extends MyBuilderScaffold<THIS, PARENT>,
                    PARENT extends BuilderScaffold<?, PARENT>>
            extends SubBuilderScaffold<ElementCondition, THIS, PARENT> {
        private ElementCondition product = new ElementCondition();

        MyBuilderScaffold(PARENT parent) {
            super(parent);
        }

        MyBuilderScaffold() {}

        public ElementCondition build() {
            return product;
        }

        public THIS source(ExtractionSource source) {
            product.source = source;
            return (THIS) this;
        }

        public THIS id(String id) {
            product.id = id;
            return (THIS) this;
        }

        public THIS predicate(MappingPredicate predicate) {
            product.predicate = predicate;
            return (THIS) this;
        }

        public THIS value(MappingExecutionValue value) {
            product.value = value;
            return (THIS) this;
        }

        public THIS valueEquals(Object value) {
            setValue(value);
            product.predicate = MappingPredicate.EQUALS;
            return (THIS) this;
        }

        public THIS valueNotEquals(Object value) {
            setValue(value);
            product.predicate = MappingPredicate.NOT;
            return (THIS) this;
        }

        public THIS valueMatches(String value) {
            setValue(value);
            product.predicate = MappingPredicate.MATCHES;
            return (THIS) this;
        }

        public THIS valueContains(String value) {
            setValue(value);
            product.predicate = MappingPredicate.CONTAINS;
            return (THIS) this;
        }

        public THIS valueNotContains(String value) {
            setValue(value);
            product.predicate = MappingPredicate.CONTAINS_NOT;
            return (THIS) this;
        }

        public THIS valuePresent() {
            product.predicate = MappingPredicate.PRESENT;
            return (THIS) this;
        }

        public THIS valueNotPresent() {
            product.predicate = MappingPredicate.NOT_PRESENT;
            return (THIS) this;
        }

        public THIS valueGreaterThan(Object value) {
            setValue(value);
            product.predicate = MappingPredicate.GREATER_THAN;
            return (THIS) this;
        }

        public THIS valueGreaterThanOrEqualTo(Object value) {
            setValue(value);
            product.predicate = MappingPredicate.GREATER_OR_EQUALS;
            return (THIS) this;
        }

        public THIS valueLessThan(Object value) {
            setValue(value);
            product.predicate = MappingPredicate.LESS_THAN;
            return (THIS) this;
        }

        public THIS valueLessThanOrEqualTo(Object value) {
            setValue(value);
            product.predicate = MappingPredicate.LESS_OR_EQUALS;
            return (THIS) this;
        }

        public THIS valueHas(String value) {
            setValue(value);
            product.predicate = MappingPredicate.HAS;
            return (THIS) this;
        }

        public THIS valueHasNot(String value) {
            setValue(value);
            product.predicate = MappingPredicate.HAS_NOT;
            return (THIS) this;
        }

        private void setValue(Object value) {
            product.value = MappingExecutionValue.of(value);
        }
    }
}
