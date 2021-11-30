package at.researchstudio.sat.merkmalservice.model;

import at.researchstudio.sat.merkmalservice.vocab.qudt.QudtQuantityKind;
import at.researchstudio.sat.merkmalservice.vocab.qudt.QudtUnit;
import java.util.ArrayList;

public class NumericFeature extends Feature {
    private FeatureType featureType;

    public NumericFeature() {}

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
        featureType = new FeatureType(QudtQuantityKind.DIMENSIONLESS, QudtUnit.UNITLESS);
    }

    public NumericFeature(String name, ArrayList<FeatureGroup> featureGroups) {
        super(name, featureGroups);
        featureType = new FeatureType(QudtQuantityKind.DIMENSIONLESS, QudtUnit.UNITLESS);
    }

    public String getQuantityKind() {
        return featureType.quantityKind;
    }

    public String getUnit() {
        return featureType.unit;
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
