package gameoflife.worldsloader;

/*
    This exception is used in situation, when file with initial pattern is in wrong format (just wrong written).
 */
public class FileFormatException extends Exception {

    public FileFormatException(String message) {
        super(message);
    }
}
