package rules.str;

import org.junit.Test;
import util.UtilTest;


public class RuleBlank {

    @Test
    public final void testRuleBlankOk() throws Exception {
        System.out.println("=== TEST FOR `blank` RULE ===\n");
        int port = UtilTest.launchWebServer("blank");
        UtilTest.testIt("  ", port, true);
        UtilTest.testIt("", port, true);
        UtilTest.testIt("             ", port, true);
        UtilTest.testIt("   ", port, true);
    }

    @Test
    public final void testRuleBlankNotOk() throws Exception {
        int port = UtilTest.launchWebServer("blank");
        UtilTest.testIt("a", port, false);
        UtilTest.testIt(". ", port, false);
        UtilTest.testIt("   - ", port, false);
    }
}
