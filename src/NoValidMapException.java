/*
    This exception is used in situation, when predefined folder from which we get initial patterns, contains no valid
    pattern.
 */
public class NoValidMapException extends Exception {

    public NoValidMapException(String message) {
        super(message);
    }
}
