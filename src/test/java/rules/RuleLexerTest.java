package rules;

import kwizzy.validation.exceptions.RuleLexerException;
import kwizzy.validation.parser.RuleLexer;
import org.junit.Test;
import util.UtilTest;

import static org.junit.Assert.*;

public class RuleLexerTest {

    @Test
    public final void testRuleLExerOk() {

        try {
            new RuleLexer("confirm").lex();
            new RuleLexer("confirm|url").lex();
            new RuleLexer("confirm|email|min_length").lex();
            new RuleLexer("confirm:1|email:12|min_length:123").lex();
            new RuleLexer("confirm: hello : world").lex();
            new RuleLexer("confirm:(123)|email:1|min_length:(1234)").lex();
            new RuleLexer("confirm:(12)|email:;").lex();
            new RuleLexer("confirm:1, 2|email:1, 3, (123)|min_length:qwerty, (qwerty is the world \\) and |,:)").lex();
        } catch (RuleLexerException e) {
            e.printStackTrace();
            fail("No error expected here ...");
        }
    }

    @Test
    public final void testRuleLEexerNotOk() {
        UtilTest.testErrorLexer("wertyuio");
        UtilTest.testErrorLexer("confirm:1|'");
        UtilTest.testErrorLexer("confirm:(123))");
        UtilTest.testErrorLexer("confirm 123");
        UtilTest.testErrorLexer("confirm (123");
        UtilTest.testErrorLexer("confirm:()|");
        UtilTest.testErrorLexer("confirm:(1)|2134");
        UtilTest.testErrorLexer("...");
        UtilTest.testErrorLexer("");
    }
}
