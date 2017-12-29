package rules.other;

import org.junit.Test;
import util.UtilTest;


public class RuleMin {

    @Test
    public final void testRuleMinOk() throws Exception {
        System.out.println("=== TEST FOR `min` RULE ===\n");
        int port = UtilTest.launchWebServer("min:3");
        UtilTest.testIt("abcd", port, true);
        UtilTest.testIt("ajknkjwnd", port, true);
        UtilTest.testIt("11111111111111111111", port, true);
        port = UtilTest.launchWebServer("min:0");
        UtilTest.testIt("wdwdwdwdwa", port, true);
        UtilTest.testIt(" f eneo", port, true);
        UtilTest.testIt(".", port, true);
    }

    @Test
    public final void testRuleMinNotOk() throws Exception {

        int port = UtilTest.launchWebServer("min:3");
        UtilTest.testIt("a", port, false);
        UtilTest.testIt("", port, false);
        port = UtilTest.launchWebServer("min:10");
        UtilTest.testIt("abcdefg", port, false);
        UtilTest.testIt(" f eneo", port, false);
        UtilTest.testIt("", port, false);
        UtilTest.testIt("..", port, false);

    }
}
