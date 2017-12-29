package rules.number;

import org.junit.Test;
import util.UtilTest;

public class RuleFloatTest {

    @Test
    public final void testRuleFloatOk() throws Exception {
        System.out.println("=== TEST FOR `float` RULE ===\n");
        int port = UtilTest.launchWebServer("float");
        UtilTest.testIt("123", port, true);
        UtilTest.testIt("-123", port, true);
        UtilTest.testIt("-56789.3456789", port, true);
        UtilTest.testIt("987654.3456789", port, true);
        UtilTest.testIt(String.valueOf(Float.MAX_VALUE), port, true);
        UtilTest.testIt(String.valueOf(Float.MIN_VALUE), port, true);
    }

    @Test
    public final void testRuleFloatNotOk() throws Exception {
        int port = UtilTest.launchWebServer("float");
        UtilTest.testIt("!@#$%^&****()_+≠–«‘[", port, false);
        UtilTest.testIt("9*123 ", port, false);
        UtilTest.testIt("9.34567%", port, false);
        UtilTest.testIt("-  123. ", port, false);
    }
}
