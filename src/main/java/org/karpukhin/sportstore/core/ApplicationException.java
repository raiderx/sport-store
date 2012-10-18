package org.karpukhin.sportstore.core;

/**
 * @author Pavel Karpukhin
 * @since 12.10.12
 */
public class ApplicationException extends RuntimeException {

    private Object[] args;

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Object... args) {
        super(message);
        this.args = args;
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        if (args == null) {
            return super.getMessage();
        }
        return String.format(super.getMessage(), args);
    }
}
