package at.researchstudio.sat.merkmalservice.model.qudt;

import java.util.Objects;

public class UnitFactor {
    private final int exponent;
    private final Unit unit;

    public UnitFactor(int exponent, Unit unit) {
        Objects.requireNonNull(unit);
        if (exponent == 0) {
            throw new IllegalArgumentException("exponent 0 is not allowed");
        }
        this.exponent = exponent;
        this.unit = unit;
    }

    public int getExponent() {
        return exponent;
    }

    public Unit getUnit() {
        return unit;
    }

    public String getKind() {
        return unit.getIri() + " " + Integer.signum(exponent);
    }

    public static UnitFactor combine(UnitFactor left, UnitFactor right) {
        if (!left.getKind().equals(right.getKind())) {
            throw new IllegalArgumentException(
                    "Cannot combine UnitFactors of different kind (left: "
                            + left.getKind()
                            + ", right: "
                            + right.getKind()
                            + ")");
        }
        return new UnitFactor(left.getExponent() + right.getExponent(), left.getUnit());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnitFactor that = (UnitFactor) o;
        return exponent == that.exponent && unit.equals(that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exponent, unit);
    }

    @Override
    public String toString() {
        return "UnitFactor{" + "unit=" + unit + ", exponent=" + exponent + '}';
    }
}
