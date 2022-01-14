package at.researchstudio.sat.merkmalservice.model.ifc.ifc4.ifcmeasureresource.type.ifcvalue.ifcsimplevalue;

public class IfcLabel extends IfcSimpleValue<String> {
    public IfcLabel(String value) {
        super(value);
    }

    @Override
    protected boolean constraintViolated(String value) {
        return value.length() > 255;
    }
}
