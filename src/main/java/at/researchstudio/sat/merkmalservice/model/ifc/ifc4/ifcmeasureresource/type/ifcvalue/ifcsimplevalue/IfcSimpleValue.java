package at.researchstudio.sat.merkmalservice.model.ifc.ifc4.ifcmeasureresource.type.ifcvalue.ifcsimplevalue;

import at.researchstudio.sat.merkmalservice.model.ifc.ifc4.ifcmeasureresource.type.ifcvalue.IfcValue;

public abstract class IfcSimpleValue<T> extends IfcValue<T> {
    public IfcSimpleValue(T value) {
        super(value);
    }
}
