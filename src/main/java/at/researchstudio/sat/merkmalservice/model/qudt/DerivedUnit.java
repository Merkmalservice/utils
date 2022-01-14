package at.researchstudio.sat.merkmalservice.model.qudt;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.util.Values;

class DerivedUnit {
    private IRI unitIri;
    private List<FactorUnit> children = new ArrayList<>();
    private Set<FactorUnit> dangling = new HashSet<>();

    public DerivedUnit(IRI unitIri) {
        Objects.requireNonNull(unitIri);
        this.unitIri = unitIri;
    }

    public void addFactorUnit(FactorUnit factorUnit) {
        if (factorUnit.getDerivedUnitIri().equals(this.unitIri)) {
            children.add(factorUnit);
            return;
        }
        for (FactorUnit child : children) {
            Set<FactorUnit> notDangling = new HashSet<>();
            if (child.addChild(factorUnit)) {
                // try to add the dangling units, if any
                for (FactorUnit fu : dangling) {
                    if (factorUnit.addChild(fu)) {
                        notDangling.add(fu);
                    }
                }
                dangling.removeAll(notDangling);
                return;
            }
        }
        // did not add as child anywhere: add to dangling and try next time
        dangling.add(factorUnit);
    }

    private void errorIfUnfinished() {
        if (!dangling.isEmpty()) {
            throw new IllegalStateException(
                    String.format(
                            "DerivedUnit %s has not been completely initialized: these factor units are still dangling: %s",
                            this,
                            dangling.stream()
                                    .map(Object::toString)
                                    .collect(Collectors.joining(", "))));
        }
    }

    public boolean matches(Map<IRI, Integer> factorUnitSpec) {
        Object[] arr = new Object[factorUnitSpec.size() * 2];
        return matches(
                factorUnitSpec.entrySet().stream()
                        .flatMap(e -> Stream.of(e.getKey(), e.getValue()))
                        .collect(Collectors.toList())
                        .toArray(arr));
    }

    /**
     * Accepts up to 7 pairs of <URI|Unit|String, Integer>, which are interpreted as factor units
     * and respective exponents.
     *
     * @param factorUnitSpec
     * @return true if the specified unit/exponent combination identifies this unit.
     *     (overspecification is counted as a match)
     */
    public boolean matches(Object... factorUnitSpec) {
        errorIfUnfinished();
        if (factorUnitSpec.length % 2 != 0) {
            throw new IllegalArgumentException("An even number of arguments is required");
        }
        if (factorUnitSpec.length > 14) {
            throw new IllegalArgumentException(
                    "No more than 10 arguments (7 factor units) supported");
        }
        boolean abortSearch = false;
        List<FactorUnitSelector> selectors = new ArrayList<>();
        for (int i = 0; i < factorUnitSpec.length && abortSearch == false; i += 2) {
            IRI requestedUnit;
            if (factorUnitSpec[i] instanceof Unit) {
                requestedUnit = ((Unit) factorUnitSpec[i]).getIri();
            } else if (factorUnitSpec[i] instanceof String) {
                requestedUnit = Values.iri((String) factorUnitSpec[i]);
            } else {
                requestedUnit = (IRI) factorUnitSpec[i];
            }
            Integer requestedExponent = (Integer) factorUnitSpec[i + 1];
            selectors.add(new FactorUnitSelector(requestedUnit, requestedExponent));
        }
        FactorUnitSelection selection = new FactorUnitSelection(selectors, new ArrayList<>());
        List<FactorUnitSelection> possibleSelections = List.of(selection);
        for (FactorUnit child : children) {
            possibleSelections = child.match(possibleSelections);
            if (possibleSelections.isEmpty()) {
                return false;
            }
        }
        return !possibleSelections.isEmpty()
                && possibleSelections.stream().anyMatch(FactorUnitSelection::allBound);
    }

    public IRI getUnitIri() {
        return unitIri;
    }

    public Collection<FactorUnit> getLeafFactorUnits() {
        return children.stream()
                .flatMap(f -> f.getLeafFactorUnits().stream())
                .collect(Collectors.toList());
    }

    public Collection<FactorUnit> getDirectFactorUnits() {
        return this.children == null
                ? Collections.emptyList()
                : Collections.unmodifiableList(this.children);
    }

    @Override
    public String toString() {
        return "DerivedUnit{" + "unitIri=" + unitIri + ", children=" + children + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DerivedUnit that = (DerivedUnit) o;
        return unitIri.equals(that.unitIri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitIri);
    }
}
