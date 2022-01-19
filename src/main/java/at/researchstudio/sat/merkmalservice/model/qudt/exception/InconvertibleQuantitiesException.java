package at.researchstudio.sat.merkmalservice.model.qudt.exception;

public class InconvertibleQuantitiesException extends QudtException {
    public InconvertibleQuantitiesException() {}

    public InconvertibleQuantitiesException(String message) {
        super(message);
    }

    public InconvertibleQuantitiesException(String message, Throwable cause) {
        super(message, cause);
    }

    public InconvertibleQuantitiesException(Throwable cause) {
        super(cause);
    }

    public InconvertibleQuantitiesException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
