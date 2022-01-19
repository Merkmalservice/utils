package at.researchstudio.sat.merkmalservice.model.ifc;

import at.researchstudio.sat.merkmalservice.vocab.ifc.IfcUnitType;
import java.lang.invoke.MethodHandles;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IfcUnit {
    private static final Logger logger =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final IfcUnitType type;
    private Integer id;
    private final boolean projectDefault;

    public IfcUnit(Integer id, IfcUnitType type, boolean projectDefault) {
        this.id = id;
        this.type = type;
        this.projectDefault = projectDefault;
    }

    public IfcUnit(Integer id, String type, boolean projectDefault) {
        IfcUnitType tempType = IfcUnitType.UNKNOWN;
        try {
            tempType = IfcUnitType.fromString(type);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
        }

        this.id = id;
        this.type = tempType;
        this.projectDefault = projectDefault;
    }

    public IfcUnitType getType() {
        return type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isProjectDefault() {
        return projectDefault;
    }

    @Override
    public String toString() {
        return "IfcUnit{" + "type=" + type + ", id='" + id + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IfcUnit ifcUnit = (IfcUnit) o;
        return projectDefault == ifcUnit.projectDefault
                && type == ifcUnit.type
                && Objects.equals(id, ifcUnit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, id, projectDefault);
    }
}
