package at.researchstudio.sat.merkmalservice.model.ifc;

import java.util.Objects;

public class IfcDerivedUnitElement {
    private IfcUnit ifcUnit;
    private int exponent;

    public IfcDerivedUnitElement(IfcUnit ifcUnit, int exponent) {
        Objects.requireNonNull(ifcUnit);
        this.ifcUnit = ifcUnit;
        this.exponent = exponent;
    }

    public IfcUnit getIfcUnit() {
        return ifcUnit;
    }

    public int getExponent() {
        return exponent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IfcDerivedUnitElement that = (IfcDerivedUnitElement) o;
        return exponent == that.exponent && ifcUnit.equals(that.ifcUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ifcUnit, exponent);
    }

    @Override
    public String toString() {
        return ifcUnit + "^" + exponent;
    }
}
