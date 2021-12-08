package at.researchstudio.sat.merkmalservice.model.mapping.condition;

import at.researchstudio.sat.merkmalservice.model.mapping.MappingExecutionValue;
import at.researchstudio.sat.merkmalservice.model.mapping.MappingPredicate;
import at.researchstudio.sat.merkmalservice.model.mapping.feature.Feature;
import java.util.Optional;
import java.util.function.Consumer;

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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private SingleCondition product;

        public Builder() {
            this.product = new SingleCondition();
        }

        public SingleCondition build() {
            return product;
        }

        public Builder feature(Feature feature) {
            product.feature = feature;
            return this;
        }

        public Builder feature(Consumer<Feature.Builder> featureConfigurer) {
            Feature.Builder builder = Feature.builder();
            featureConfigurer.accept(builder);
            return feature(builder.build());
        }

        public Builder id(String id) {
            product.id = id;
            return this;
        }

        public Builder value(MappingExecutionValue value) {
            product.value = value;
            return this;
        }

        public Builder value(String value) {
            product.value = new MappingExecutionValue(value);
            return this;
        }

        public Builder value(Double value) {
            product.value = new MappingExecutionValue(value);
            return this;
        }

        public Builder value(Boolean value) {
            product.value = new MappingExecutionValue(value);
            return this;
        }

        public Builder predicate(MappingPredicate predicate) {
            product.predicate = predicate;
            return this;
        }
    }
}
