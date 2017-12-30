package kwizzy.validation.exceptions;

public enum LexerException {

    BEGIN_PARAM_NO_RULE("Can't begin param section because no rule name found. (Index: {index})"),
    NEED_RULE("Need valid rule name: [_a-zA-Z0-9]. (Index: {index})"),
    NO_RULES_MATCHING("No rule matching for rule name: {rule_name}. (Index: {index})"),
    NEED_SOMETHING_IN_PARENTHESES("Need something in parentheses. (Index: {index})"),
    NEED_SOMETHING_PARAM("Need a non empty param. (Index: {index})"),
    NO_NEW_RULE_CAUSE_NO_RULE_NAME("Can't begin new rule because no rule name found. (Index: {index})"),
    UNKNOWN_CHAR("Lexer: Unexpected symbol: {char}. (Index: {index})"),
    EMPTY("Lexer: Empty string input. Are you sure you are not drunken ? (Index: {index})"),
    ;

    private String message;

    LexerException(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
