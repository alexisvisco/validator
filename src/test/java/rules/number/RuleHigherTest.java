package rules.number;

import org.junit.Test;
import util.UtilTest;

public class RuleHigherTest {
    @Test
    public final void testRuleHigherOk() throws Exception {
        System.out.println("=== TEST FOR `lesser` RULE ===\n");
        int port = UtilTest.launchWebServer("higher:5");
        UtilTest.testIt("124", port, true);
        UtilTest.testIt("12", port, true);
        port = UtilTest.launchWebServer("higher:-15");
        UtilTest.testIt("-12", port, true);
        UtilTest.testIt("67", port, true);
    }

    @Test
    public final void testRuleHigherNotOk() throws Exception {
        int port = UtilTest.launchWebServer("higher:5");
        UtilTest.testIt("!@#$%^&****()_+≠–«‘[", port, false);
        UtilTest.testIt("12a", port, false);
        UtilTest.testIt("4", port, false);
        UtilTest.testIt("3", port, false);
        UtilTest.testIt("-4", port, false);
        UtilTest.testIt("0", port, false);
    }
}
