package mms.exceptions;

public class BadItemException extends PackingException{

    public BadItemException() {}

    public BadItemException(String message){
        super(message);
    }
}