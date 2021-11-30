package at.researchstudio.sat.merkmalservice.model.mapping;

import at.researchstudio.sat.merkmalservice.model.mapping.feature.Feature;

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

    public MappingExecutionValue getValue() {
        return value;
    }

    public MappingPredicate getPredicate() {
        return predicate;
    }

    public String getId() {
        return id;
    }
}
