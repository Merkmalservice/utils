package at.researchstudio.sat.merkmalservice.model.mapping.feature.featuretype;

import at.researchstudio.sat.merkmalservice.model.builder.BuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.SubBuilderScaffold;
import at.researchstudio.sat.merkmalservice.vocab.qudt.QudtQuantityKind;
import at.researchstudio.sat.merkmalservice.vocab.qudt.QudtUnit;
import java.util.Objects;

public class NumericFeatureType extends FeatureType {
    private QuantityKind quantityKind;
    private Unit unit;

    public NumericFeatureType(QuantityKind quantityKind, Unit unit) {
        super(Types.NumericType.name());
        this.quantityKind = quantityKind;
        this.unit = unit;
    }

    public NumericFeatureType(String quantityKindIri, String unitIri) {
        this(new QuantityKind(quantityKindIri, null), new Unit(unitIri, null));
    }

    public NumericFeatureType() {
        super(Types.NumericType.name());
    }

    public QuantityKind getQuantityKind() {
        return quantityKind;
    }

    public Unit getUnit() {
        return unit;
    }

    /**
     * Compare unit and quantitykind, but not id.
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumericFeatureType that = (NumericFeatureType) o;
        return quantityKind.equals(that.quantityKind) && unit.equals(that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantityKind, unit);
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

        public THIS id(String id) {
            this.product.id = id;
            return (THIS) this;
        }

        public THIS quantityKind(QuantityKind quantityKind) {
            this.product.quantityKind = quantityKind;
            return (THIS) this;
        }

        public THIS quantityKind(String quantityKindIri) {
            return quantityKind(quantityKindIri, null);
        }

        public THIS quantityKind(String quantityKindIri, String label) {
            this.product.quantityKind = new QuantityKind(quantityKindIri, label);
            return (THIS) this;
        }

        public THIS unit(Unit unit) {
            this.product.unit = unit;
            return (THIS) this;
        }

        public THIS unit(String unitIri, String label) {
            this.product.unit = new Unit(unitIri, label);
            return (THIS) this;
        }

        public THIS unit(String unitIri) {
            return unit(unitIri, null);
        }

        public THIS unitless() {
            return unit(QudtUnit.UNITLESS);
        }

        public THIS dimensionless() {
            return quantityKind(QudtQuantityKind.DIMENSIONLESS);
        }
    }
}
