package at.researchstudio.sat.merkmalservice.model.ifc.ifc4.ifcmeasureresource.type.ifcvalue.ifcmeasurevalue;

import at.researchstudio.sat.merkmalservice.model.ifc.ifc4.ifcmeasureresource.type.ifcvalue.IfcValue;

// hierarchy as described in
// https://standards.buildingsmart.org/IFC/RELEASE/IFC4/ADD1/HTML/link/ifcmeasurevalue.htm
public abstract class IfcMeasureValue<T> extends IfcValue<T> {
    public IfcMeasureValue(T value) {
        super(value);
    }
}
