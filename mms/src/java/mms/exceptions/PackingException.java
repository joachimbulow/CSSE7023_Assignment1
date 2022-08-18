package mms.exceptions;

/**
 * Exception class for generic packing exceptions
 */
public class PackingException extends Exception {

    /**
     * No-arg constructor
     */
    public PackingException() {}

    /**
     * Message constructor
     * @param message custom message to be displayed
     */
    public PackingException(String message) {
        super(message);
    }
}