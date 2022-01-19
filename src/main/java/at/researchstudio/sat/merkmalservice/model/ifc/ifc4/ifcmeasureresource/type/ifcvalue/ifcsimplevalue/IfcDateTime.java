package at.researchstudio.sat.merkmalservice.model.ifc.ifc4.ifcmeasureresource.type.ifcvalue.ifcsimplevalue;

import java.util.regex.Pattern;

public class IfcDateTime extends IfcSimpleValue<String> {
    // pattern as defined in
    // https://standards.buildingsmart.org/IFC/RELEASE/IFC4/ADD1/HTML/link/ifcdatetime.htm
    private Pattern pattern =
            Pattern.compile("-?\\d*\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(\\.\\d+)?");

    public IfcDateTime(String value) {
        super(value);
    }

    @Override
    protected boolean constraintViolated(String value) {
        return !pattern.matcher(value).matches();
    }
}
