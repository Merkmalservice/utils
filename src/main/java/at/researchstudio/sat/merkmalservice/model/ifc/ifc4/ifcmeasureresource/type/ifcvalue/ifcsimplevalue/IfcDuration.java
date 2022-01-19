package at.researchstudio.sat.merkmalservice.model.ifc.ifc4.ifcmeasureresource.type.ifcvalue.ifcsimplevalue;

import java.util.regex.Pattern;

public class IfcDuration extends IfcSimpleValue<String> {
    // pattern as described in
    // https://standards.buildingsmart.org/IFC/RELEASE/IFC4/ADD1/HTML/link/ifcduration.htm
    Pattern pattern = Pattern.compile("P\\d+Y\\d+M\\d+DT\\d+H\\d+M\\d+(\\.\\d+)?S");

    public IfcDuration(String value) {
        super(value);
    }

    @Override
    protected boolean constraintViolated(String value) {
        return !pattern.matcher(value).matches();
    }
}
