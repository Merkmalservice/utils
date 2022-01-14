package at.researchstudio.sat.merkmalservice.model.ifc.ifc4.ifcmeasureresource.type.ifcvalue;

import at.researchstudio.sat.merkmalservice.model.ifc.ifc4.support.TypeConstraintViolatedException;
import java.util.Objects;

public abstract class IfcValue<T> {
    private final T value;

    public IfcValue(T value) {
        Objects.requireNonNull(value);
        this.value = value;
        if (constraintViolated(value)) {
            throw new TypeConstraintViolatedException(this.getClass(), value);
        }
    }

    protected boolean constraintViolated(T value) {
        return false;
    }

    public T getValue() {
        return value;
    }
}
