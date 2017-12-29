package rules.str;

import org.junit.Test;
import util.UtilTest;


public class RuleAlphaTest {

    @Test
    public final void testRuleAlphaOk() throws Exception {
        System.out.println("=== TEST FOR `alpha` RULE ===\n");
        int port = UtilTest.launchWebServer("alpha");
        UtilTest.testIt("abcdefghijklmnopqrstuvwxyz", port, true);
        UtilTest.testIt("ABCDEFGHIJKLMNOPQRSTUVWXYZ", port, true);
        UtilTest.testIt("abcABC", port, true);
    }

    @Test
    public final void testRuleAlphaNotOk() throws Exception {
        int port = UtilTest.launchWebServer("alpha");
        UtilTest.testIt("abc123", port, false);
        UtilTest.testIt("abc 123", port, false);
        UtilTest.testIt("  124 ", port, false);
        UtilTest.testIt("   ", port, false);
    }
}
