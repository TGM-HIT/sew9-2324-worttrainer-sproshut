package model.exceptions;

public class InvalidWordException extends RuntimeException {
    public InvalidWordException(String s) {
        super(s);
    }
}
