package kwizzy.validation.exceptions;

public class TransferFormException extends Exception {
    public TransferFormException(String message) {
        super("Error in object transfer: " + message);
    }
}
