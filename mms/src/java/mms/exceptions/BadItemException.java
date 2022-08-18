package mms.exceptions;

/**
 * Exception class for adding wrong items to storage containers
 */
public class BadItemException extends PackingException {

    /**
     * No-arg constructor
     */
    public BadItemException() {}

    /**
     * A Message constructor
     * @param message custom message to be displayed
     */
    public BadItemException(String message) {
        super(message);
    }
}