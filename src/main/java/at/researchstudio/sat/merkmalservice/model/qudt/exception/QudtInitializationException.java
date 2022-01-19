package at.researchstudio.sat.merkmalservice.model.qudt.exception;

public class QudtInitializationException extends QudtException {
    public QudtInitializationException() {}

    public QudtInitializationException(String message) {
        super(message);
    }

    public QudtInitializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public QudtInitializationException(Throwable cause) {
        super(cause);
    }

    public QudtInitializationException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
