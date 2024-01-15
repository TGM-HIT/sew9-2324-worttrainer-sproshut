package model.exceptions;

/**
 * Exception thrown when a word is invalid
 */
public class InvalidWordException extends RuntimeException {
    public InvalidWordException(String s) {
        super(s);
    }
}
