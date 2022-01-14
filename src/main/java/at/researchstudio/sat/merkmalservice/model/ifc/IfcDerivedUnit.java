package at.researchstudio.sat.merkmalservice.model.ifc;

import at.researchstudio.sat.merkmalservice.vocab.ifc.IfcUnitType;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class IfcDerivedUnit extends IfcUnit {
    private Map<IfcSIUnit, Integer> derivedUnitElements;

    private String userDefinedLabel;

    public IfcDerivedUnit(Integer id, IfcUnitType type, boolean projectDefault) {
        super(id, type, projectDefault);
    }

    public IfcDerivedUnit(Integer id, String type, boolean projectDefault) {
        super(id, type, projectDefault);
    }

    public IfcDerivedUnit(
            Integer id, String type, String userDefinedLabel, boolean projectDefault) {
        this(id, type, projectDefault);
        this.userDefinedLabel = userDefinedLabel;
    }

    public IfcDerivedUnit(
            Integer id, IfcUnitType type, String userDefinedLabel, boolean projectDefault) {
        this(id, type, projectDefault);
        this.userDefinedLabel = userDefinedLabel;
    }

    public IfcDerivedUnit(
            Integer id,
            IfcUnitType type,
            Map<IfcSIUnit, Integer> derivedUnitElements,
            boolean projectDefault) {
        this(id, type, projectDefault);
        this.derivedUnitElements = derivedUnitElements;
    }

    public IfcDerivedUnit(
            Integer id,
            IfcUnitType type,
            String userDefinedLabel,
            Map<IfcSIUnit, Integer> derivedUnitElements,
            boolean projectDefault) {
        this(id, type, userDefinedLabel, projectDefault);
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
