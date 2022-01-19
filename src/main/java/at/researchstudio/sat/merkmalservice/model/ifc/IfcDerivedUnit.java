package at.researchstudio.sat.merkmalservice.model.ifc;

import static java.util.stream.Collectors.joining;

import at.researchstudio.sat.merkmalservice.vocab.ifc.IfcUnitType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class IfcDerivedUnit extends IfcUnit {
    private List<IfcDerivedUnitElement> derivedUnitElements = new ArrayList<>();

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
            String type,
            List<IfcDerivedUnitElement> derivedUnitElements,
            boolean projectDefault,
            String userDefinedLabel) {
        super(id, type, projectDefault);
        this.derivedUnitElements = derivedUnitElements;
        this.userDefinedLabel = userDefinedLabel;
    }

    public IfcDerivedUnit(
            Integer id,
            IfcUnitType type,
            Map<IfcSIUnit, Integer> derivedUnitElements,
            boolean projectDefault) {
        this(id, type, projectDefault);
        this.derivedUnitElements = elementsFromMap(derivedUnitElements);
    }

    private List<IfcDerivedUnitElement> elementsFromMap(
            Map<IfcSIUnit, Integer> derivedUnitElements) {
        return derivedUnitElements.entrySet().stream()
                .map(e -> new IfcDerivedUnitElement(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    public IfcDerivedUnit(
            Integer id,
            IfcUnitType type,
            String userDefinedLabel,
            Map<IfcSIUnit, Integer> derivedUnitElements,
            boolean projectDefault) {
        this(id, type, userDefinedLabel, projectDefault);
        this.derivedUnitElements = elementsFromMap(derivedUnitElements);
    }

    public List<IfcDerivedUnitElement> getDerivedUnitElements() {
        return derivedUnitElements;
    }

    public String getUserDefinedLabel() {
        return userDefinedLabel;
    }

    public void setUserDefinedLabel(String userDefinedLabel) {
        this.userDefinedLabel = userDefinedLabel;
    }

    public void addDerivedUnitElement(IfcSIUnit unit, int exponent) {
        derivedUnitElements.add(new IfcDerivedUnitElement(unit, exponent));
    }

    public void addDerivedUnitElement(IfcDerivedUnitElement element) {
        derivedUnitElements.add(element);
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
        return "IfcDerivedUnit{" + userDefinedLabel != null
                ? "'" + userDefinedLabel + "'"
                : ""
                        + derivedUnitElements.stream().map(Object::toString).collect(joining(", "))
                        + '}';
    }
}
