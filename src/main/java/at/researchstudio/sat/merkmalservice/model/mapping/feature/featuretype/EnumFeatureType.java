package at.researchstudio.sat.merkmalservice.model.mapping.feature.featuretype;

import at.researchstudio.sat.merkmalservice.model.IBuilder;
import java.util.ArrayList;
import java.util.List;

public class EnumFeatureType extends FeatureType {
    private List<OptionValue> options;
    private Boolean allowMultiple;

    public EnumFeatureType() {}

    public EnumFeatureType(List<OptionValue> options, Boolean allowMultiple) {
        super("ENUMERATION");
        this.options = options;
        this.allowMultiple = allowMultiple;
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

    public static class Builder implements IBuilder<EnumFeatureType> {
        private EnumFeatureType product;

        Builder() {
            this.product = new EnumFeatureType();
        }

        public EnumFeatureType build() {
            return product;
        }

        public Builder option(OptionValue optionValue) {
            if (product.options == null) {
                product.options = new ArrayList<>();
            }
            product.options.add(optionValue);
            return this;
        }

        public Builder option(String value, String description) {
            return option(new MEStringValue(value, description));
        }

        public Builder option(Integer value, String description) {
            return option(new MEIntegerValue(value, description));
        }

        public Builder option(Double value, String description) {
            return option(new MEFloatValue(value.floatValue(), description));
        }

        public Builder option(Float value, String description) {
            return option(new MEFloatValue(value, description));
        }

        public Builder option(Boolean value, String description) {
            return option(new MEBooleanValue(value, description));
        }

        public Builder allowMultiple(boolean allowMultiple) {
            product.allowMultiple = allowMultiple;
            return this;
        }
    }
}
