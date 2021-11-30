package at.researchstudio.sat.merkmalservice.model.mapping.feature.featuretype;

import at.researchstudio.sat.merkmalservice.model.EnumFeature;
import java.util.List;

public class EnumFeatureType extends FeatureType {
    private List<EnumFeature.OptionValue> options;
    private Boolean allowMultiple;

    public EnumFeatureType(List<EnumFeature.OptionValue> options, Boolean allowMultiple) {
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

    public static class MEStringValue extends EnumFeature.OptionValue {
        private String stringValue;

        public MEStringValue(String stringValue, String description) {
            super(stringValue, description);
            this.stringValue = stringValue;
        }

        public String getStringValue() {
            return stringValue;
        }
    }

    public static class MEIntegerValue extends EnumFeature.OptionValue {
        private Integer integerValue;

        public MEIntegerValue(Integer integerValue, String description) {
            super(String.valueOf(integerValue), description);
            this.integerValue = integerValue;
        }

        public Integer getIntegerValue() {
            return integerValue;
        }
    }

    public static class MEFloatValue extends EnumFeature.OptionValue {
        private Float floatValue;

        public MEFloatValue(Float floatValue, String description) {
            super(String.valueOf(floatValue), description);
            this.floatValue = floatValue;
        }

        public Float getFloatValue() {
            return floatValue;
        }
    }

    public static class MEBooleanValue extends EnumFeature.OptionValue {
        private String name;

        public MEBooleanValue(String name, String description) {
            super(name, description);
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
