package at.researchstudio.sat.merkmalservice.model.mapping;

import java.util.Optional;

public class MappingExecutionValue {
    private String stringValue;
    private Integer integerValue;
    private Boolean booleanValue;
    private Double floatValue;
    private String idValue;
    private String graphQLType;

    public MappingExecutionValue() {}

    public MappingExecutionValue(String stringValue) {
        this.stringValue = stringValue;
        this.integerValue = null;
        this.booleanValue = null;
        this.floatValue = null;
        this.idValue = null;
        this.graphQLType = null;
    }

    public MappingExecutionValue(Integer integerValue) {
        this.stringValue = null;
        this.booleanValue = null;
        this.floatValue = null;
        this.idValue = null;
        this.graphQLType = null;
        this.integerValue = integerValue;
    }

    public MappingExecutionValue(Boolean booleanValue) {
        this.stringValue = null;
        this.integerValue = null;
        this.floatValue = null;
        this.idValue = null;
        this.graphQLType = null;
        this.booleanValue = booleanValue;
    }

    public MappingExecutionValue(Double floatValue) {
        this.stringValue = null;
        this.integerValue = null;
        this.booleanValue = null;
        this.idValue = null;
        this.graphQLType = null;
        this.floatValue = floatValue;
    }

    public MappingExecutionValue(String idValue, String graphQLType) {
        this.stringValue = null;
        this.integerValue = null;
        this.booleanValue = null;
        this.floatValue = null;
        this.idValue = idValue;
        this.graphQLType = graphQLType;
    }

    public Optional<String> getStringValue() {
        return Optional.ofNullable(stringValue);
    }

    public Optional<Integer> getIntegerValue() {
        return Optional.ofNullable(integerValue);
    }

    public Optional<Boolean> getBooleanValue() {
        return Optional.ofNullable(booleanValue);
    }

    public Optional<Double> getFloatValue() {
        return Optional.ofNullable(floatValue);
    }

    public Optional<String> getIdValue() {
        return Optional.ofNullable(idValue);
    }

    public Optional<String> getGraphQLType() {
        return Optional.ofNullable(graphQLType);
    }

    public <T> T getValue() {
        if (stringValue != null) {
            return (T) stringValue;
        }
        if (integerValue != null) {
            return (T) integerValue;
        }
        if (booleanValue != null) {
            return (T) booleanValue;
        }
        if (floatValue != null) {
            return (T) floatValue;
        }
        if (idValue != null) {
            return (T) idValue;
        }
        throw new IllegalStateException("No value found in MappingExecutionValue");
    }
}
