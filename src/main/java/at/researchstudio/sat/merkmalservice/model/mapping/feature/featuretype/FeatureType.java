package at.researchstudio.sat.merkmalservice.model.mapping.feature.featuretype;

import at.researchstudio.sat.merkmalservice.model.IBuilder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "__typename")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "BooleanValue", value = BooleanFeatureType.class),
    @JsonSubTypes.Type(name = "StringValue", value = StringFeatureType.class),
    @JsonSubTypes.Type(name = "ReferenceValue", value = ReferenceFeatureType.class),
    @JsonSubTypes.Type(name = "NumericValue", value = NumericFeatureType.class),
    @JsonSubTypes.Type(name = "EnumerationValue", value = EnumFeatureType.class),
})
public abstract class FeatureType {
    @JsonIgnore private String type;

    public FeatureType() {}

    public FeatureType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder implements IBuilder<FeatureType> {

        private IBuilder<? extends FeatureType> builder;

        public Builder() {}

        public BooleanFeatureType.Builder booleanFeatureType() {
            this.builder = new BooleanFeatureType.Builder();
            return (BooleanFeatureType.Builder) this.builder;
        }

        public EnumFeatureType.Builder enumFeatureType() {
            this.builder = new EnumFeatureType.Builder();
            return (EnumFeatureType.Builder) this.builder;
        }

        public NumericFeatureType.Builder numericFeatureType() {
            this.builder = new NumericFeatureType.Builder();
            return (NumericFeatureType.Builder) this.builder;
        }

        public ReferenceFeatureType.Builder referenceFeatureType() {
            this.builder = new ReferenceFeatureType.Builder();
            return (ReferenceFeatureType.Builder) this.builder;
        }

        public StringFeatureType.Builder stringFeatureType() {
            this.builder = new StringFeatureType.Builder();
            return (StringFeatureType.Builder) this.builder;
        }

        public FeatureType build() {
            return this.builder.build();
        }
    }
}
