package model.exceptions;

/**
 * Exception thrown when the URL is invalid
 */
public class InvalidURLException extends RuntimeException {
    public InvalidURLException(String s) {
        super(s);
    }
}
