package at.researchstudio.sat.merkmalservice.model.mapping.feature.featuretype;

import at.researchstudio.sat.merkmalservice.model.builder.BuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.SubBuilderScaffold;
import at.researchstudio.sat.merkmalservice.vocab.qudt.QudtQuantityKind;
import at.researchstudio.sat.merkmalservice.vocab.qudt.QudtUnit;
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

    public static enum Types {
        BooleanValue,
        StringValue,
        ReferenceValue,
        NumericValue,
        EnumerationValue;
    }

    @JsonIgnore private String type;

    public FeatureType() {}

    public FeatureType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static Builder<?> builder() {
        return new Builder<>();
    }

    public static <PARENT extends BuilderScaffold<?, PARENT>> Builder<PARENT> builder(
            PARENT parent) {
        return new Builder(parent);
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
            extends SubBuilderScaffold<FeatureType, THIS, PARENT> {

        private FeatureType product = null;
        private EnumFeatureType.Builder<THIS> enumFeatureTypeBuilder = null;

        public MyBuilderScaffold(PARENT parent) {
            super(parent);
        }

        public MyBuilderScaffold() {}

        public THIS booleanType() {
            this.product = new BooleanFeatureType();
            return (THIS) this;
        }

        public EnumFeatureType.Builder<THIS> enumType() {
            this.enumFeatureTypeBuilder = EnumFeatureType.enumTypeBuilder((THIS) this);
            return this.enumFeatureTypeBuilder;
        }

        public THIS numericType(QudtQuantityKind quantityKind, QudtUnit qudtUnit) {
            this.product = new NumericFeatureType(quantityKind.toString(), qudtUnit.toString());
            return (THIS) this;
        }

        public THIS referenceType() {
            this.product = new ReferenceFeatureType();
            return (THIS) this;
        }

        public THIS stringType() {
            this.product = new StringFeatureType();
            return (THIS) this;
        }

        public FeatureType build() {
            if (this.enumFeatureTypeBuilder != null) {
                return this.enumFeatureTypeBuilder.build();
            }
            if (this.product != null) {
                return this.product;
            }
            throw new IllegalStateException("No feature type selected yet, cannot build!");
        }
    }
}
