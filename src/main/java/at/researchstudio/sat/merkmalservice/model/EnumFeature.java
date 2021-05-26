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

    public static class OptionValue {
        private String value;
        private String description;

        public OptionValue(String value, String description) {
            this.value = value;
            this.description = description;
        }
    }
}

