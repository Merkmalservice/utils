package at.researchstudio.sat.merkmalservice.model.ifc;

import at.researchstudio.sat.merkmalservice.vocab.ifc.IfcUnitType;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class IfcDerivedUnit extends IfcUnit {
    private Map<IfcSIUnit, Integer> derivedUnitElements;

    private String userDefinedLabel;

    public IfcDerivedUnit(String id, IfcUnitType type) {
        super(id, type);
    }

    public IfcDerivedUnit(String id, String type) {
        super(id, type);
    }

    public IfcDerivedUnit(String id, String type, String userDefinedLabel) {
        this(id, type);
        this.userDefinedLabel = userDefinedLabel;
    }

    public IfcDerivedUnit(String id, IfcUnitType type, String userDefinedLabel) {
        this(id, type);
        this.userDefinedLabel = userDefinedLabel;
    }

    public IfcDerivedUnit(
            String id, IfcUnitType type, Map<IfcSIUnit, Integer> derivedUnitElements) {
        this(id, type);
        this.derivedUnitElements = derivedUnitElements;
    }

    public IfcDerivedUnit(
            String id,
            IfcUnitType type,
            String userDefinedLabel,
            Map<IfcSIUnit, Integer> derivedUnitElements) {
        this(id, type, userDefinedLabel);
        this.derivedUnitElements = derivedUnitElements;
    }

    public Map<IfcSIUnit, Integer> getDerivedUnitElements() {
        return derivedUnitElements;
    }

    public String getUserDefinedLabel() {
        return userDefinedLabel;
    }

    public void setUserDefinedLabel(String userDefinedLabel) {
        this.userDefinedLabel = userDefinedLabel;
    }

    public void addDerivedUnitElement(IfcSIUnit unitElement, int exponent) {
        if (derivedUnitElements == null) {
            derivedUnitElements = new HashMap<>();
        }
        derivedUnitElements.put(unitElement, exponent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IfcDerivedUnit that = (IfcDerivedUnit) o;
        return getType() == that.getType()
                && Objects.equals(userDefinedLabel, that.userDefinedLabel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), userDefinedLabel);
    }

    @Override
    public String toString() {
        return "IfcDerivedUnit{"
                + "derivedUnitElements="
                + derivedUnitElements
                + ", userDefinedLabel='"
                + userDefinedLabel
                + '\''
                + '}';
    }
}
