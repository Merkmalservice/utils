package at.researchstudio.sat.merkmalservice.model.mapping.condition;

import at.researchstudio.sat.merkmalservice.model.builder.BuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.ListBuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.TerminalBuilderScaffold;
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

    public static <PARENT extends BuilderScaffold<PARENT, ?>> Builder<PARENT> builder() {
        return new Builder();
    }

    public static <PARENT extends BuilderScaffold<PARENT, ?>> Builder<PARENT> builder(
            PARENT parent) {
        return new Builder(parent);
    }

    public static class Builder<PARENT extends BuilderScaffold<PARENT, ?>>
            extends TerminalBuilderScaffold<SingleCondition, Builder<PARENT>, PARENT> {
        private SingleCondition product;
        private Feature.Builder<Builder<PARENT>> featureBuilder = null;

        public Builder(PARENT parent) {
            super(parent);
            this.product = new SingleCondition();
        }

        public Builder() {
            this(null);
        }

        public SingleCondition build() {
            return product;
        }

        public Builder feature(Feature feature) {
            product.feature = feature;
            return this;
        }

        public Feature.Builder<Builder<PARENT>> feature() {
            this.featureBuilder = Feature.builder(this);
            return this.featureBuilder;
        }

        public Builder id(String id) {
            product.id = id;
            return this;
        }

        public Builder value(Object value) {
            product.value = MappingExecutionValue.of(value);
            return this;
        }

        public Builder predicate(MappingPredicate predicate) {
            product.predicate = predicate;
            return this;
        }

        public PARENT endSingleCondition() {
            return end();
        }
    }

    public static class ListBuilder<PARENT extends BuilderScaffold<PARENT, ?>>
            extends ListBuilderScaffold<SingleCondition, SingleCondition.Builder<PARENT>, PARENT> {
        public ListBuilder(PARENT parent) {
            super(() -> SingleCondition.builder(parent));
        }
    }
}
