package rules.other;

import org.junit.Test;
import util.UtilTest;


public class RuleMax {

    @Test
    public final void testRuleMinOk() throws Exception {
        System.out.println("=== TEST FOR `max` RULE ===\n");
        int port = UtilTest.launchWebServer("max:3");
        UtilTest.testIt("abc", port, true);
        UtilTest.testIt("a", port, true);
        UtilTest.testIt("", port, true);
        port = UtilTest.launchWebServer("max:10");
        UtilTest.testIt("abcdefg", port, true);
        UtilTest.testIt(" f eneo", port, true);
        UtilTest.testIt(".", port, true);
        port = UtilTest.launchWebServer("max:0");
        UtilTest.testIt("", port, true);
    }

    @Test
    public final void testRuleMaxNotOk() throws Exception {
        int port = UtilTest.launchWebServer("max:3");
        UtilTest.testIt("abcd", port, false);
        UtilTest.testIt("ajknkjwnd", port, false);
        UtilTest.testIt("11111111111111111111", port, false);
        port = UtilTest.launchWebServer("max:0");
        UtilTest.testIt("wdwdwdwdwa", port, false);
        UtilTest.testIt(" f eneo", port, false);
        UtilTest.testIt(".", port, false);
    }
}
