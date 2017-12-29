package rules.str;

import org.junit.Test;
import util.UtilTest;

import static org.junit.Assert.fail;


public class RuleNotEmptyTest {

    @Test
    public final void testRuleNotEmptyOk() throws Exception {
        System.out.println("=== TEST FOR `not_empty` RULE ===\n");
        int port = UtilTest.launchWebServer("not_empty");
        UtilTest.testIt("  1 ", port, true);
        UtilTest.testIt("  123", port, true);
        UtilTest.testIt("123", port, true);
        UtilTest.testIt("  ab  ", port, true);
    }

    @Test
    public final void testRuleNotEmptyNotOk() throws Exception {
        int port = UtilTest.launchWebServer("not_empty");
        UtilTest.testIt("", port, false);
        UtilTest.testIt(" ", port, false);
        UtilTest.testIt("    ", port, false);
    }
}
