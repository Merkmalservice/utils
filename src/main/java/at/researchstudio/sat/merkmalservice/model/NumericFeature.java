package at.researchstudio.sat.merkmalservice.model;

import java.util.ArrayList;

public class NumericFeature extends Feature {
    private final FeatureType featureType;

    public NumericFeature(String name, String quantityKind, String unit) {
        super(name);
        featureType = new FeatureType(quantityKind, unit);
    }

    public NumericFeature(
            String name, ArrayList<FeatureGroup> featureGroups, String quantityKind, String unit) {
        super(name, featureGroups);
        featureType = new FeatureType(quantityKind, unit);
    }

    public NumericFeature(String name) {
        super(name);
        featureType =
                new FeatureType(
                        "http://qudt.org/vocab/quantitykind/Dimensionless",
                        "http://qudt.org/vocab/unit/UNITLESS");
    }

    public NumericFeature(String name, ArrayList<FeatureGroup> featureGroups) {
        super(name, featureGroups);
        featureType =
                new FeatureType(
                        "http://qudt.org/vocab/quantitykind/Dimensionless",
                        "http://qudt.org/vocab/unit/UNITLESS");
    }

    private class FeatureType {
        private final String type = "NUMERIC";
        private final String quantityKind;
        private final String unit;

        public FeatureType(String quantityKind, String unit) {
            this.quantityKind = quantityKind;
            this.unit = unit;
        }
    }
}
