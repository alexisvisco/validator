package rules.other;

import org.junit.Test;
import util.UtilTest;


public class RuleBool {

    @Test
    public final void testRuleBoolOk() throws Exception {
        System.out.println("=== TEST FOR `bool` RULE ===\n");
        int port = UtilTest.launchWebServer("bool");
        UtilTest.testIt("true", port, true);
        UtilTest.testIt("false", port, true);
        UtilTest.testIt("0", port, true);
        UtilTest.testIt("1", port, true);
    }

    @Test
    public final void testRuleBoolNotOk() throws Exception {
        int port = UtilTest.launchWebServer("bool");
        UtilTest.testIt("truee", port, false);
        UtilTest.testIt("flasee", port, false);
        UtilTest.testIt(" 0", port, false);
        UtilTest.testIt(" 1", port, false);
        UtilTest.testIt("2", port, false);
        UtilTest.testIt("yes", port, false);
        UtilTest.testIt("no", port, false);
    }
}
