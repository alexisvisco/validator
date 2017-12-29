package rules.number;

import org.junit.Test;
import util.UtilTest;

public class RuleLesserTest {
    @Test
    public final void testRuleLesserOk() throws Exception {
        System.out.println("=== TEST FOR `lesser` RULE ===\n");
        int port = UtilTest.launchWebServer("lesser:5");
        UtilTest.testIt("4", port, true);
        UtilTest.testIt("3", port, true);
        UtilTest.testIt("0", port, true);
        UtilTest.testIt("0", port, true);
    }

    @Test
    public final void testRuleLesserNotOk() throws Exception {
        int port = UtilTest.launchWebServer("lesser:5");
        UtilTest.testIt("!@#$%^&****()_+≠–«‘[", port, false);
        UtilTest.testIt("12", port, false);
        UtilTest.testIt("12a", port, false);
        UtilTest.testIt("678345678903456789234567893456789023456789034567890", port, false);
    }
}
