package at.researchstudio.sat.merkmalservice.model.ifc.ifc4.support;

public class TypeConstraintViolatedException extends RuntimeException {
    private Class<?> violatedType;
    private Object violatingValue;

    public TypeConstraintViolatedException(Class<?> violatedType, Object violatingValue) {
        this.violatedType = violatedType;
        this.violatingValue = violatingValue;
    }

    public TypeConstraintViolatedException(
            String message, Class<?> violatedType, Object violatingValue) {
        super(message);
        this.violatedType = violatedType;
        this.violatingValue = violatingValue;
    }

    public TypeConstraintViolatedException(
            String message, Throwable cause, Class<?> violatedType, Object violatingValue) {
        super(message, cause);
        this.violatedType = violatedType;
        this.violatingValue = violatingValue;
    }

    public TypeConstraintViolatedException(
            Throwable cause, Class<?> violatedType, Object violatingValue) {
        super(cause);
        this.violatedType = violatedType;
        this.violatingValue = violatingValue;
    }

    public TypeConstraintViolatedException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace,
            Class<?> violatedType,
            Object violatingValue) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.violatedType = violatedType;
        this.violatingValue = violatingValue;
    }
}
