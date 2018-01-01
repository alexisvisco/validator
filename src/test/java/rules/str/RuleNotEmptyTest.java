package rules.str;

import org.junit.Test;
import util.UtilTest;

import static org.junit.Assert.fail;


public class RuleNotEmptyTest {

    @Test
    public final void testRuleNotEmptyOk() throws Exception {
        System.out.println("=== TEST FOR `not_empty` RULE ===\n");
        UtilTest.testIt("not_empty", "  1 ", true);
        UtilTest.testIt("not_empty", "  123", true);
        UtilTest.testIt("not_empty", "123", true);
        UtilTest.testIt("not_empty", "  ab  ", true);
    }

    @Test
    public final void testRuleNotEmptyNotOk() throws Exception {
        UtilTest.testIt("not_empty", "", false);
        UtilTest.testIt("not_empty", " ", false);
        UtilTest.testIt("not_empty", "    ", false);
    }
}
