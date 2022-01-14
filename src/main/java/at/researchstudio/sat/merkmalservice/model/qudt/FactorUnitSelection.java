package at.researchstudio.sat.merkmalservice.model.qudt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FactorUnitSelection {
    private List<FactorUnitSelector> selectors;

    public FactorUnitSelection(
            List<FactorUnitSelector> unmatchedSelectors, List<FactorUnit> marked) {
        this.selectors = unmatchedSelectors;
    }

    public FactorUnitSelection(List<FactorUnitSelector> unmatchedSelectors) {
        this.selectors = unmatchedSelectors;
    }

    public FactorUnitSelection copy() {
        return new FactorUnitSelection(
                this.selectors.stream().map(FactorUnitSelector::copy).collect(Collectors.toList()));
    }

    public List<FactorUnitSelector> getSelectors() {
        return selectors;
    }

    public boolean isMarked(FactorUnit factorUnit) {
        return this.selectors.stream().anyMatch(s -> factorUnit.equals(s.getMatchedFactorUnit()));
    }

    public boolean allMarked(Collection<FactorUnit> factorUnits) {
        return this.selectors.stream()
                .allMatch(
                        s ->
                                factorUnits.stream()
                                        .anyMatch(u -> u.equals(s.getMatchedFactorUnit())));
    }

    public boolean isMatchingSelectorAvailable(FactorUnit factorUnit, int cumulativeExponent) {
        return selectors.stream()
                .anyMatch(s -> s.isAvailable() && s.matches(factorUnit, cumulativeExponent));
    }

    public FactorUnitSelection forMatch(FactorUnit factorUnit, int cumulativeExponent) {
        List<FactorUnitSelector> newSelectors = new ArrayList<>();
        boolean matched = false;
        for (FactorUnitSelector s : this.selectors) {
            if (!matched && s.isAvailable() && s.matches(factorUnit, cumulativeExponent)) {
                matched = true;
                newSelectors.addAll(s.forMatch(factorUnit, cumulativeExponent));
            } else {
                newSelectors.add(s.copy());
            }
        }
        return new FactorUnitSelection(newSelectors);
    }

    public boolean allBound() {
        return selectors.stream().allMatch(FactorUnitSelector::isBound);
    }
}
