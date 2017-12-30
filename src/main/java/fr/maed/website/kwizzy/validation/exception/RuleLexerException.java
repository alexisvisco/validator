package fr.maed.website.kwizzy.validation.exception;

import java.util.Collections;

public class RuleLexerException extends RuleParseException
{

    private LexerException exception;
    private String origin;
    private Integer index;
    private char charr = '0';
    private String rule = "error";

    public RuleLexerException(LexerException exception, String origin, Integer index) {
        this.exception = exception;
        this.origin = origin;
        this.index = index;
    }

    public RuleLexerException(LexerException exception, String origin, Integer index, String rule) {
        this(exception, origin, index);
        this.rule = rule;
    }
    public RuleLexerException(LexerException exception, String origin, Integer index, char charr) {
        this(exception, origin, index);
        this.charr = charr;
    }

    private void printFormattedError() {
        System.err.println(origin);
        String s = String.join("", Collections.nCopies(index, " ")) + "^";
        System.err.println(s);
        String str = exception.getMessage();
        str = str.replace("{index}", String.valueOf(index));
        str = str.replace("{char}", String.valueOf(charr));
        str = str.replace("{rule_name}", String.valueOf(rule));
        System.err.println(str);
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
        System.err.println();
        printFormattedError();
    }
}
