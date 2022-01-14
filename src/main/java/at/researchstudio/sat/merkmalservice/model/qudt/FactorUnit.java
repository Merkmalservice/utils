package at.researchstudio.sat.merkmalservice.model.qudt;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.eclipse.rdf4j.model.IRI;

class FactorUnit {
    final IRI derivedUnitIri;
    final int exponent;
    final IRI unitIri;
    List<FactorUnit> children;
    FactorUnit parent;

    public FactorUnit(IRI derivedUnitIri, IRI unitIri, int exponent) {
        this(derivedUnitIri, unitIri, exponent, null);
    }

    public FactorUnit(IRI derivedUnitIri, IRI unitIri, int exponent, FactorUnit parent) {
        Objects.requireNonNull(derivedUnitIri);
        Objects.requireNonNull(unitIri);
        this.exponent = exponent;
        this.unitIri = unitIri;
        this.parent = parent;
        this.derivedUnitIri = derivedUnitIri;
    }

    public FactorUnit toChildOf(FactorUnit parent) {
        FactorUnit asChild = new FactorUnit(this.derivedUnitIri, this.unitIri, exponent, parent);
        if (this.children != null) {
            for (FactorUnit child : children) {
                asChild.addChild(child);
            }
        }
        return asChild;
    }

    public boolean addChild(FactorUnit child) {
        if (!child.derivedUnitIri.equals(this.unitIri)) {
            return false;
        }
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        this.children.add(child.toChildOf(this));
        return true;
    }

    public int getExponent() {
        return exponent;
    }

    public int getExponentCumulated(int cumulatedExponent) {
        return exponent * cumulatedExponent;
    }

    public IRI getUnitIri() {
        return unitIri;
    }

    public List<FactorUnit> getChildren() {
        return children;
    }

    public List<FactorUnitSelection> match(List<FactorUnitSelection> possibleSelections) {
        return match(possibleSelections, 1);
    }

    private List<FactorUnitSelection> match(
            List<FactorUnitSelection> selection, int cumulativeExponent) {
        List<FactorUnitSelection> mySelection = new ArrayList<>(selection);
        if (children != null) {
            for (FactorUnit child : children) {
                mySelection = child.match(mySelection, getExponentCumulated(cumulativeExponent));
            }
        }
        List<FactorUnitSelection> ret = new ArrayList<>();
        for (FactorUnitSelection factorUnitSelection : mySelection) {
            // add one solution where this node is matched
            FactorUnitSelection processedSelection =
                    factorUnitSelection.forMatch(this, cumulativeExponent);
            if (!processedSelection.equals(factorUnitSelection)) {
                // if there was a match, (i.e, we modified the selection),
                // it's a new partial solution - return it
                ret.add(processedSelection);
            }
            // also regard the selection without the match as a possible partial solution
            ret.add(factorUnitSelection);
        }
        if (parent == null) {
            // toplevel
            return pruneMatchResults(ret);
        }
        // lower level
        return ret;
    }

    private List<FactorUnitSelection> pruneMatchResults(List<FactorUnitSelection> ret) {
        return ret.stream().filter(this::isMatched).collect(Collectors.toList());
    }

    private boolean isMatched(FactorUnitSelection selection) {
        if (selection.isMarked(this)) {
            return true;
        }
        if (children != null) {
            for (FactorUnit child : children) {
                if (!child.isMatched(selection)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "FactorUnit{"
                + "unitIri="
                + unitIri
                + ", exp="
                + exponent
                + ", children: "
                + (children == null ? 0 : children.size())
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FactorUnit that = (FactorUnit) o;
        return exponent == that.exponent
                && derivedUnitIri.equals(that.derivedUnitIri)
                && unitIri.equals(that.unitIri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(derivedUnitIri, exponent, unitIri);
    }

    public IRI getDerivedUnitIri() {
        return derivedUnitIri;
    }

    public List<FactorUnit> getLeafFactorUnits() {
        if (this.children == null || this.children.isEmpty()) {
            return List.of(this);
        }
        return children.stream()
                .flatMap(c -> c.getLeafFactorUnits().stream())
                .collect(Collectors.toList());
    }

    public List<FactorUnit> getLeafFactorUnitsWithCumulativeExponents() {
        if (this.children == null || this.children.isEmpty()) {
            return List.of(this);
        }
        return children.stream()
                .flatMap(c -> c.getLeafFactorUnits().stream())
                .map(f -> f.withExponentMultiplied(this.getExponent()))
                .collect(Collectors.toList());
    }

    private FactorUnit withExponentMultiplied(int by) {
        FactorUnit ret = new FactorUnit(derivedUnitIri, unitIri, this.exponent * by);
        if (this.children != null) {
            for (FactorUnit child : children) {
                ret.addChild(child);
            }
        }
        return ret;
    }

    public void addDerivedUnitAsChildren(DerivedUnit derivedUnit) {
        for (FactorUnit child : derivedUnit.getDirectFactorUnits()) {
            addChild(child);
        }
    }
}
