package at.researchstudio.sat.merkmalservice.model.ifc;

import at.researchstudio.sat.merkmalservice.vocab.ifc.IfcUnitType;
import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IfcUnit {
    private static final Logger logger =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final IfcUnitType type;
    private final String id;

    public IfcUnit(String id, IfcUnitType type) {
        this.id = id;
        this.type = type;
    }

    public IfcUnit(String id, String type) {
        IfcUnitType tempType = IfcUnitType.UNKNOWN;
        try {
            tempType = IfcUnitType.fromString(type);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
        }

        this.id = id;
        this.type = tempType;
    }

    public IfcUnitType getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "IfcUnit{" + "type=" + type + ", id='" + id + '\'' + '}';
    }
}
