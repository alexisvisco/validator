package rules.number;

import org.junit.Test;
import util.UtilTest;

public class RuleRangeTest {
    @Test
    public final void testRuleRangeOk() throws Exception {
        System.out.println("=== TEST FOR `range` RULE ===\n");
        int port = UtilTest.launchWebServer("range:5, 10");
        UtilTest.testIt("5", port, true);
        UtilTest.testIt("6", port, true);
        UtilTest.testIt("7", port, true);
        UtilTest.testIt("7.77", port, true);
        UtilTest.testIt("8", port, true);
        UtilTest.testIt("9", port, true);
        UtilTest.testIt("10", port, true);
    }

    @Test
    public final void testRuleRangeNotOk() throws Exception {
        int port = UtilTest.launchWebServer("range:5, 10");
        UtilTest.testIt("!@#$%^&", port, false);
        UtilTest.testIt("4", port, false);
        UtilTest.testIt("4.45678", port, false);
        UtilTest.testIt("11", port, false);
        UtilTest.testIt("11.45678", port, false);
        UtilTest.testIt("-4", port, false);
        UtilTest.testIt("10.456789", port, false);
        UtilTest.testIt("678345678903456789234567893456789023456789034567890", port, false);
    }
}
