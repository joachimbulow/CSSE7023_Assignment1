package mms.exceptions;

/**
 * Exception class for packing items in wrong order
 */
public class PackingOrderException extends PackingException {

    /**
     * No-arg constructor
     */
    public PackingOrderException() {}

    /**
     * Message constructor
     * @param message custom message to be displayed
     */
    public PackingOrderException(String message) {
        super(message);
    }
}