package at.researchstudio.sat.merkmalservice.model.qudt;

import java.util.Map;
import java.util.Objects;
import org.eclipse.rdf4j.model.IRI;

class FactorUnits {
    private Map<IRI, FactorUnit> factorUnits;

    private static class UnitAndExponent {
        private final IRI unit;
        private final int exponent;

        public UnitAndExponent(IRI unit, int exponent) {
            Objects.requireNonNull(unit);
            Objects.requireNonNull(exponent);
            this.unit = unit;
            this.exponent = exponent;
        }

        public IRI getUnit() {
            return unit;
        }

        public int getExponent() {
            return exponent;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UnitAndExponent that = (UnitAndExponent) o;
            return exponent == that.exponent && Objects.equals(unit, that.unit);
        }

        @Override
        public int hashCode() {
            return Objects.hash(unit, exponent);
        }
    }
}
