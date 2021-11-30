package at.researchstudio.sat.merkmalservice.model.mapping.feature.featuretype;

public class NumericFeatureType extends FeatureType {
    private String quantityKind;
    private String unit;

    public NumericFeatureType(String quantityKind, String unit) {
        super("NUMERIC");
        this.quantityKind = quantityKind;
        this.unit = unit;
    }

    public String getQuantityKind() {
        return quantityKind;
    }

    public String getUnit() {
        return unit;
    }
}
