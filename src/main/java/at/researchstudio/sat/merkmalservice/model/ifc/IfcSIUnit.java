package at.researchstudio.sat.merkmalservice.model.ifc;

import at.researchstudio.sat.merkmalservice.vocab.ifc.IfcUnitMeasure;
import at.researchstudio.sat.merkmalservice.vocab.ifc.IfcUnitMeasurePrefix;
import at.researchstudio.sat.merkmalservice.vocab.ifc.IfcUnitType;
import java.lang.invoke.MethodHandles;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IfcSIUnit extends IfcUnit {
    private static final Logger logger =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final IfcUnitMeasure measure;
    private final IfcUnitMeasurePrefix prefix;

    public IfcSIUnit(
            Integer id,
            IfcUnitType type,
            IfcUnitMeasure measure,
            IfcUnitMeasurePrefix prefix,
            boolean projectDefault) {
        super(id, type, projectDefault);
        this.measure = measure;
        this.prefix = prefix;
    }

    public IfcSIUnit(
            Integer id, String type, String measure, String prefix, boolean projectDefault) {
        super(id, type, projectDefault);

        IfcUnitMeasure tempMeasure = IfcUnitMeasure.UNKNOWN;
        try {
            tempMeasure = IfcUnitMeasure.fromString(measure);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
        }

        this.measure = tempMeasure;

        IfcUnitMeasurePrefix tempPrefix = IfcUnitMeasurePrefix.NONE;
        if (Objects.nonNull(prefix)) {
            try {
                tempPrefix = IfcUnitMeasurePrefix.fromString(prefix);
            } catch (IllegalArgumentException e) {
                logger.error(e.getMessage());
            }
        }

        this.prefix = tempPrefix;
    }

    public IfcUnitMeasure getMeasure() {
        return measure;
    }

    public IfcUnitMeasurePrefix getPrefix() {
        return prefix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IfcSIUnit ifcSIUnit = (IfcSIUnit) o;
        return measure == ifcSIUnit.measure && prefix == ifcSIUnit.prefix;
    }

    @Override
    public int hashCode() {
        return Objects.hash(measure, prefix);
    }

    @Override
    public String toString() {
        return "IfcSIUnit{" + "measure=" + measure + ", prefix=" + prefix + '}';
    }
}
