package at.researchstudio.sat.merkmalservice.model.ifc.ifc4.ifcmeasureresource.type.ifcvalue.ifcsimplevalue;

import java.util.regex.Pattern;

public class IfcTime extends IfcSimpleValue<String> {
    // pattern as described in
    // https://standards.buildingsmart.org/IFC/RELEASE/IFC4/ADD1/HTML/link/ifctime.htm
    Pattern pattern = Pattern.compile("\\d{2}:\\d{2}:\\d{2}(\\.\\d+)?([+-]\\d{2}(:\\d{2})?)?");

    public IfcTime(String value) {
        super(value);
    }

    @Override
    protected boolean constraintViolated(String value) {
        return !pattern.matcher(value).matches();
    }
}
