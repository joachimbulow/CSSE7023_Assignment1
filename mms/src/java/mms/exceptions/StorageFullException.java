package mms.exceptions;

/**
 * Exception class when storage is full and you try packing more stuff
 */
public class StorageFullException extends PackingException {

    /**
     * No-arg constructor
     */
    public StorageFullException() {}

    /**
     * Message constructor
     * @param message custom message to be displayed
     */
    public StorageFullException(String message) {
        super(message);
    }
}