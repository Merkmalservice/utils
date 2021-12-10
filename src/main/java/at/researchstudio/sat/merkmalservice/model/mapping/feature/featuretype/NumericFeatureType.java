package at.researchstudio.sat.merkmalservice.model.mapping.feature.featuretype;

import at.researchstudio.sat.merkmalservice.model.builder.BuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.SubBuilderScaffold;
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

    public static Builder<?> numericFeatureBuilder() {
        return new Builder();
    }

    public static <PARENT extends BuilderScaffold<?, PARENT>> Builder<PARENT> numericFeatureBuilder(
            PARENT parent) {
        return new Builder<PARENT>(parent);
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
            extends SubBuilderScaffold<NumericFeatureType, THIS, PARENT> {

        private NumericFeatureType product = new NumericFeatureType();

        public MyBuilderScaffold(PARENT parent) {
            super(parent);
        }

        public MyBuilderScaffold() {}

        public NumericFeatureType build() {
            return product;
        }

        public THIS quantityKind(String quantityKind) {
            this.product.quantityKind = quantityKind;
            return (THIS) this;
        }

        public THIS unit(String unit) {
            this.product.unit = unit;
            return (THIS) this;
        }

        public THIS quantityKind(QudtQuantityKind quantityKind) {
            this.product.quantityKind = quantityKind.toString();
            return (THIS) this;
        }

        public THIS unit(QudtUnit qudtUnit) {
            this.product.unit = qudtUnit.toString();
            return (THIS) this;
        }
    }
}
