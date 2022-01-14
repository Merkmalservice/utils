package at.researchstudio.sat.merkmalservice.qudtifc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import at.researchstudio.sat.merkmalservice.model.ifc.IfcUnit;
import at.researchstudio.sat.merkmalservice.model.qudt.Qudt;
import at.researchstudio.sat.merkmalservice.vocab.ifc.IfcUnitType;
import org.junit.jupiter.api.Test;

public class QudtIfcMapperTest {
    @Test
    public void testBasicFunctionality() {
        IfcUnit unit = QudtIfcMapper.mapQudtUnitToIfcUnit(Qudt.Units.M);
        assertEquals(IfcUnitType.LENGTHUNIT, unit.getType());
    }
}
