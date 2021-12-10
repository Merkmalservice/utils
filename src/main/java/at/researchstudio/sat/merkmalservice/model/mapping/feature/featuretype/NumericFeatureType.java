package at.researchstudio.sat.merkmalservice.model.mapping.feature.featuretype;

import at.researchstudio.sat.merkmalservice.model.IBuilder;
import at.researchstudio.sat.merkmalservice.vocab.qudt.QudtQuantityKind;
import at.researchstudio.sat.merkmalservice.vocab.qudt.QudtUnit;

public class NumericFeatureType extends FeatureType {
    private String quantityKind;
    private String unit;

    public NumericFeatureType(String quantityKind, String unit) {
        super("NumericValue");
        this.quantityKind = quantityKind;
        this.unit = unit;
    }

    public NumericFeatureType() {}

    public String getQuantityKind() {
        return quantityKind;
    }

    public String getUnit() {
        return unit;
    }

    public static class Builder implements IBuilder<NumericFeatureType> {
        private NumericFeatureType product;

        Builder() {
            this.product = new NumericFeatureType();
        }

        public NumericFeatureType build() {
            return product;
        }

        public Builder quantityKind(String quantityKind) {
            this.product.quantityKind = quantityKind;
            return this;
        }

        public Builder unit(String unit) {
            this.product.unit = unit;
            return this;
        }

        public Builder quantityKind(QudtQuantityKind quantityKind) {
            this.product.quantityKind = quantityKind.toString();
            return this;
        }

        public Builder unit(QudtUnit qudtUnit) {
            this.product.unit = qudtUnit.toString();
            return this;
        }
    }
}
