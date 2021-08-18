package at.researchstudio.sat.merkmalservice.model;

import java.util.ArrayList;
import java.util.List;

public class EnumFeature extends Feature {
    private final FeatureType featureType;

    public EnumFeature(String name, List<OptionValue> options, Boolean allowMultiple) {
        super(name);
        this.featureType = new FeatureType(options, allowMultiple);
    }

    public EnumFeature(
            String name,
            ArrayList<FeatureGroup> featureGroups,
            List<OptionValue> options,
            Boolean allowMultiple) {
        super(name, featureGroups);
        this.featureType = new FeatureType(options, allowMultiple);
    }

    public FeatureType getFeatureType() {
        return featureType;
    }

    public List<OptionValue> getOptions() {
        return featureType.options;
    }

    private class FeatureType {
        private final String type = "ENUMERATION";
        private final List<OptionValue> options;
        private final Boolean allowMultiple;

        public FeatureType(List<OptionValue> options, Boolean allowMultiple) {
            this.options = options;
            this.allowMultiple = allowMultiple;
        }
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
