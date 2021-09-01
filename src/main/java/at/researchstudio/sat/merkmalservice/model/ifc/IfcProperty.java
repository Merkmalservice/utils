package at.researchstudio.sat.merkmalservice.model.ifc;

import at.researchstudio.sat.merkmalservice.utils.Utils;
import at.researchstudio.sat.merkmalservice.vocab.ifc.IfcPropertyType;
import at.researchstudio.sat.merkmalservice.vocab.ifc.IfcUnitType;
import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IfcProperty {
    private static final Logger logger =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final String name;
    private final IfcPropertyType type;

    private IfcUnit unit;

    private Set<String> extractedUniqueValues;

    private Set<String> enumOptionValues;

    public IfcProperty(String name, IfcPropertyType type) {
        this.name = Utils.convertIFCStringToUtf8(name);
        this.type = type;
    }

    public IfcProperty(String name, String type) {
        this.name = Utils.convertIFCStringToUtf8(name);

        IfcPropertyType tempType = IfcPropertyType.UNKNOWN;
        try {
            tempType = IfcPropertyType.fromString(type);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
        }

        this.type = tempType;
    }

    public IfcProperty(String name, IfcPropertyType type, IfcUnit unit) {
        this(name, type);

        if (this.type.isMeasureType()) {
            this.unit = unit;
        }
    }

    private static IfcUnit getIfcUnitFromProjectUnits(
            IfcPropertyType type, Map<IfcUnitType, List<IfcUnit>> projectUnits) {
        if (Objects.nonNull(projectUnits)) {
            IfcUnitType tempUnitType = type.getUnitType();
            List<IfcUnit> units = projectUnits.get(tempUnitType);

            if (Objects.nonNull(units)) {
                if (units.size() == 1) {
                    IfcUnit ifcUnit = units.get(0);
                    return ifcUnit;
                } else {
                    logger.warn(
                            "More than one unit present for IfcPropertyType<{}>, leaving it empty",
                            type);
                    units.forEach(unit -> logger.debug(unit.toString()));
                }
            }
        }
        logger.warn("Could not find Unit for IfcPropertyType<{}>", type);
        logger.warn("within ProjectUnits:");
        projectUnits.forEach(
                (key, value) -> {
                    logger.warn(key.toString());
                    Objects.requireNonNullElse(value, Collections.emptyList())
                            .forEach(unit -> logger.warn("\t{}", unit));
                });
        return null;
    }

    public String getName() {
        return name;
    }

    public IfcPropertyType getType() {
        return type;
    }

    public IfcUnit getUnit() {
        return unit;
    }

    public Set<String> getExtractedUniqueValues() {
        return extractedUniqueValues;
    }

    public Set<String> getEnumOptionValues() {
        return enumOptionValues;
    }

    public void addExtractedValue(String value) {
        if (extractedUniqueValues == null) {
            extractedUniqueValues = new HashSet<>();
        }
        extractedUniqueValues.add(Utils.convertIFCStringToUtf8(value));
    }

    public void addEnumOptionValue(String value) {
        if (enumOptionValues == null) {
            enumOptionValues = new HashSet<>();
        }
        enumOptionValues.add(Utils.convertIFCStringToUtf8(value));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IfcProperty that = (IfcProperty) o;
        return Objects.equals(name, that.name) && type == that.type && unit == that.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, unit);
    }

    @Override
    public String toString() {
        String extractedUniqueValuesString = "NO VALUES";
        if (Objects.nonNull(extractedUniqueValues) && !extractedUniqueValues.isEmpty()) {
            extractedUniqueValuesString =
                    extractedUniqueValues.stream()
                            .collect(Collectors.joining("\n\t", "{\n\t", "\n}"));
        }
        String optionValues = "";
        if (IfcPropertyType.VALUELIST.equals(type)
                && Objects.nonNull(enumOptionValues)
                && !enumOptionValues.isEmpty()) {
            optionValues =
                    ", optionValues="
                            + enumOptionValues.stream()
                                    .collect(Collectors.joining("\n\t", "{\n\t", "\n}"));
        }

        return "IfcProperty{"
                + "name='"
                + name
                + '\''
                + ", type="
                + type
                + ", ifcUnit="
                + unit
                + ", extractedUniqueValues="
                + extractedUniqueValuesString
                + optionValues
                + '}';
    }
}
