package at.researchstudio.sat.merkmalservice.model.qudt;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.rdf4j.model.IRI;

class FactorUnitSelector {

    private final IRI unitIri;
    private final int exponent;
    private FactorUnit matchedFactorUnit;

    public FactorUnitSelector(IRI unitIri, int exponent) {
        this.unitIri = unitIri;
        this.exponent = exponent;
        this.matchedFactorUnit = null;
    }

    public FactorUnitSelector(IRI unitIri, int exponent, FactorUnit matchedFactorUnit) {
        this.unitIri = unitIri;
        this.exponent = exponent;
        this.matchedFactorUnit = matchedFactorUnit;
    }

    public List<FactorUnitSelector> forMatch(FactorUnit factorUnit, int cumulativeExponent) {
        if (!this.isAvailable()) {
            throw new IllegalArgumentException("not available - selector is already bound");
        }
        if (!unitIriMatches(factorUnit)) {
            throw new IllegalArgumentException("unit iris must match");
        }
        if (!exponentMatches(factorUnit, cumulativeExponent)) {
            throw new IllegalArgumentException("epxonents must match");
        }
        int remainingPower = this.exponent - factorUnit.getExponentCumulated(cumulativeExponent);
        List ret = new ArrayList();
        ret.add(new FactorUnitSelector(this.unitIri, this.exponent, factorUnit));
        if (remainingPower != 0) {
            ret.add(new FactorUnitSelector(this.unitIri, remainingPower));
        }
        return ret;
    }

    private boolean exponentMatches(FactorUnit factorUnit, int cumulativeExponent) {
        int cumulatedFactorUnitExponent = factorUnit.getExponentCumulated(cumulativeExponent);

        return Math.abs(this.exponent) > 0
                && Math.abs(this.exponent) >= Math.abs(cumulatedFactorUnitExponent)
                && Integer.signum(this.exponent) == Integer.signum(cumulatedFactorUnitExponent);
    }

    private boolean unitIriMatches(FactorUnit factorUnit) {
        return this.unitIri.equals(factorUnit.unitIri);
    }

    public boolean matches(FactorUnit factorUnit, int cumulativeExponent) {
        return unitIriMatches(factorUnit) && exponentMatches(factorUnit, cumulativeExponent);
    }

    public FactorUnitSelector copy() {
        return new FactorUnitSelector(this.unitIri, this.exponent, this.matchedFactorUnit);
    }

    public IRI getUnitIri() {
        return unitIri;
    }

    public int getExponent() {
        return exponent;
    }

    public FactorUnit getMatchedFactorUnit() {
        return matchedFactorUnit;
    }

    public void setMatchedFactorUnit(FactorUnit matchedFactorUnit) {
        this.matchedFactorUnit = matchedFactorUnit;
    }

    public boolean isAvailable() {
        return matchedFactorUnit == null;
    }

    public boolean isBound() {
        return !isAvailable();
    }
}
