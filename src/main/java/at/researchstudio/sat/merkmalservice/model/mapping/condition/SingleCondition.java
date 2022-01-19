package at.researchstudio.sat.merkmalservice.model.mapping.condition;

import at.researchstudio.sat.merkmalservice.model.builder.BuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.ListBuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.SubBuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.mapping.MappingExecutionValue;
import at.researchstudio.sat.merkmalservice.model.mapping.MappingPredicate;
import at.researchstudio.sat.merkmalservice.model.mapping.feature.Feature;
import java.util.Optional;

public class SingleCondition implements Condition {
    private Feature feature;
    private MappingPredicate predicate;
    private MappingExecutionValue value;
    private String id;

    public SingleCondition() {}

    public SingleCondition(
            String id, Feature feature, MappingPredicate predicate, MappingExecutionValue value) {
        this.feature = feature;
        this.predicate = predicate;
        this.value = value;
        this.id = id;
    }

    public Feature getFeature() {
        return feature;
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
            extends ListBuilderScaffold<SingleCondition, SingleCondition.Builder<PARENT>, PARENT> {
        private ListBuilder(PARENT parent) {
            super(() -> SingleCondition.builder(parent));
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
            extends SubBuilderScaffold<SingleCondition, THIS, PARENT> {
        private SingleCondition product = new SingleCondition();
        ;
        private Feature.Builder<THIS> featureBuilder = null;

        MyBuilderScaffold(PARENT parent) {
            super(parent);
        }

        MyBuilderScaffold() {}

        public SingleCondition build() {
            if (this.featureBuilder != null) {
                this.product.feature = featureBuilder.build();
            }
            return product;
        }

        public THIS feature(Feature feature) {
            product.feature = feature;
            return (THIS) this;
        }

        public Feature.Builder<THIS> feature() {
            this.featureBuilder = Feature.builder((THIS) this);
            return this.featureBuilder;
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
