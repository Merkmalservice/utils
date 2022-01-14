package at.researchstudio.sat.merkmalservice.model.ifc.ifc4.ifcmeasureresource.type.ifcvalue.ifcsimplevalue;

public class IfcIdentifier extends IfcSimpleValue<String> {
    public IfcIdentifier(String value) {
        super(value);
    }

    @Override
    protected boolean constraintViolated(String value) {
        return value.length() > 255;
    }
}
