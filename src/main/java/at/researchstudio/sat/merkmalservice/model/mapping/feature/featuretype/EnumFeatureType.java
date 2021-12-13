package at.researchstudio.sat.merkmalservice.model.mapping.feature.featuretype;

import at.researchstudio.sat.merkmalservice.model.builder.BuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.SubBuilderScaffold;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnumFeatureType extends FeatureType {
    private List<OptionValue> options;
    private Boolean allowMultiple;

    public EnumFeatureType() {
        super(Types.EnumerationValue.name());
    }

    public EnumFeatureType(List<OptionValue> options, Boolean allowMultiple) {
        super(Types.EnumerationValue.name());
        this.options = options;
        this.allowMultiple = allowMultiple;
    }

    public List<OptionValue> getOptions() {
        return Collections.unmodifiableList(this.options);
    }

    public Boolean isAllowMultiple() {
        return allowMultiple;
    }

    public abstract static class OptionValue {
        private transient String value;
        private String description;

        public OptionValue(String value, String description) {
            this.value = value;
            this.description = description;
        }

        public String getValue() {
            return value;
        }

        public String getDescription() {
            return description;
        }

        public OptionValue(String description) {
            this.description = description;
        }
    }

    public static class MEStringValue extends OptionValue {
        private String stringValue;

        public MEStringValue(String stringValue, String description) {
            super(stringValue, description);
            this.stringValue = stringValue;
        }

        public String getStringValue() {
            return stringValue;
        }
    }

    public static class MEIntegerValue extends OptionValue {
        private Integer integerValue;

        public MEIntegerValue(Integer integerValue, String description) {
            super(String.valueOf(integerValue), description);
            this.integerValue = integerValue;
        }

        public Integer getIntegerValue() {
            return integerValue;
        }
    }

    public static class MEFloatValue extends OptionValue {
        private Float floatValue;

        public MEFloatValue(Float floatValue, String description) {
            super(String.valueOf(floatValue), description);
            this.floatValue = floatValue;
        }

        public Float getFloatValue() {
            return floatValue;
        }
    }

    public static class MEBooleanValue extends OptionValue {
        private Boolean booleanValue;

        public MEBooleanValue(Boolean value, String description) {
            super(value.toString(), description);
            this.booleanValue = value;
        }

        public Boolean getBooleanValue() {
            return booleanValue;
        }
    }

    public static Builder<?> enumTypeBuilder() {
        return new Builder<>();
    }

    public static <PARENT extends BuilderScaffold<?, PARENT>> Builder<PARENT> enumTypeBuilder(
            PARENT parent) {
        return new Builder<>(parent);
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
            extends SubBuilderScaffold<EnumFeatureType, THIS, PARENT> {
        private EnumFeatureType product = new EnumFeatureType();

        MyBuilderScaffold(PARENT parent) {
            super(parent);
        }

        MyBuilderScaffold() {}

        public EnumFeatureType build() {
            return product;
        }

        private THIS option(OptionValue optionValue) {
            if (product.options == null) {
                product.options = new ArrayList<>();
            }
            product.options.add(optionValue);
            return (THIS) this;
        }

        public THIS option(String value, String description) {
            return option(new MEStringValue(value, description));
        }

        public THIS option(Integer value, String description) {
            return option(new MEIntegerValue(value, description));
        }

        public THIS option(Double value, String description) {
            return option(new MEFloatValue(value.floatValue(), description));
        }

        public THIS option(Float value, String description) {
            return option(new MEFloatValue(value, description));
        }

        public THIS option(Boolean value, String description) {
            return option(new MEBooleanValue(value, description));
        }

        public THIS allowMultiple(boolean allowMultiple) {
            product.allowMultiple = allowMultiple;
            return (THIS) this;
        }
    }
}
