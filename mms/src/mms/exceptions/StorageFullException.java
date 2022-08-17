package mms.exceptions;

public class StorageFullException extends PackingException {
    public StorageFullException() {}

    public StorageFullException(String message){
        super(message);
    }
}