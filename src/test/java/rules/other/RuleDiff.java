package rules.other;

import org.junit.Test;
import util.UtilTest;


public class RuleDiff {

    @Test
    public final void testRuleMinOk() throws Exception {
        System.out.println("=== TEST FOR `diff` RULE ===\n");
        int port = UtilTest.launchWebServer("diff:hello world");
        UtilTest.testIt("hello", port, true);
        port = UtilTest.launchWebServer("diff:123");
        UtilTest.testIt("1234", port, true);
    }

    @Test
    public final void testRuleMinNotOk() throws Exception {
        int port = UtilTest.launchWebServer("diff:hello world");
        UtilTest.testIt("hello world", port, false);
        port = UtilTest.launchWebServer("diff:123");
        UtilTest.testIt("123", port, false);
    }
}
