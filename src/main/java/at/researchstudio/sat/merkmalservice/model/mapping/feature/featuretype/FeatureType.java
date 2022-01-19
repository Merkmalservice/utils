package at.researchstudio.sat.merkmalservice.model.mapping.feature.featuretype;

import at.researchstudio.sat.merkmalservice.model.builder.BuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.SubBuilderScaffold;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Objects;

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

    protected String id;

    public FeatureType() {}

    public FeatureType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public static Builder<?> builder() {
        return new Builder<>();
    }

    /**
     * Generic equals: compare ids if set, otherwise object identity.
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeatureType that = (FeatureType) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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

        public THIS numericType(String qudtQuantityKindIri, String qudtUnitIri) {
            this.product =
                    new NumericFeatureType(
                            new QuantityKind(qudtQuantityKindIri, null),
                            new Unit(qudtUnitIri, null));
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
