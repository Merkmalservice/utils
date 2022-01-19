package at.researchstudio.sat.merkmalservice.model.qudt.exception;

public class QudtException extends RuntimeException {
    public QudtException() {}

    public QudtException(String message) {
        super(message);
    }

    public QudtException(String message, Throwable cause) {
        super(message, cause);
    }

    public QudtException(Throwable cause) {
        super(cause);
    }

    public QudtException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
