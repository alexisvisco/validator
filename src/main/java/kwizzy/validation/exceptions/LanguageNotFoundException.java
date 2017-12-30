package kwizzy.validation.exceptions;

public class LanguageNotFoundException extends Exception {
    public LanguageNotFoundException(String message) {
        super("Language not found " + message);
    }
}
