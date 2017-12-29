package rules.number;

import org.junit.Test;
import util.UtilTest;

public class RuleIntTest {
    @Test
    public final void testRuleIntOk() throws Exception {
        System.out.println("=== TEST FOR `int` RULE ===\n");
        int port = UtilTest.launchWebServer("int");
        UtilTest.testIt("123", port, true);
        UtilTest.testIt("-123", port, true);
        UtilTest.testIt(String.valueOf(Integer.MAX_VALUE), port, true);
        UtilTest.testIt(String.valueOf(Integer.MIN_VALUE), port, true);
    }

    @Test
    public final void testRuleIntNotOk() throws Exception {
        int port = UtilTest.launchWebServer("int");
        UtilTest.testIt("!@#$%^&****()_+≠–«‘[", port, false);
        UtilTest.testIt("9*123 ", port, false);
        UtilTest.testIt("-  123. ", port, false);
        UtilTest.testIt("123 ", port, false);
        UtilTest.testIt("12 4567", port, false);
        UtilTest.testIt("12.34", port, false);
        UtilTest.testIt(String.valueOf(Integer.MAX_VALUE) + "1", port, false);
    }
}
