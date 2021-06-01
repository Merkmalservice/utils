package at.researchstudio.sat.merkmalservice.model;

import java.util.List;

public class EnumFeature extends Feature {
    private final FeatureType featureType;

    public EnumFeature(String name, List<OptionValue> options, Boolean allowMultiple) {
        super(name);
        this.featureType = new FeatureType(options, allowMultiple);
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

    public static abstract class OptionValue {
        private String value;
        private String description;

        public OptionValue(String value, String description) {
            this.value = value;
            this.description = description;
        }

        public OptionValue(String description) {
            this.description = description;
        }
    }

    public static class MEStringValue extends OptionValue {
        private String stringValue;

        public MEStringValue(String stringValue, String description) {
            super(description);
            this.stringValue = stringValue;
        }
    }

    public static class MEIntegerValue extends OptionValue {
        private Integer integerValue;

        public MEIntegerValue(Integer integerValue, String description) {
            super(description);
            this.integerValue = integerValue;
        }
    }

    public static class MEFloatValue extends OptionValue {
        private Float floatValue;

        public MEFloatValue(Float floatValue, String description) {
            super(description);
            this.floatValue = floatValue;
        }
    }

    public static class MEBooleanValue extends OptionValue {
        private Boolean booleanValue;

        public MEBooleanValue(Boolean booleanValue, String description) {
            super(description);
            this.booleanValue = booleanValue;
        }
    }
}

