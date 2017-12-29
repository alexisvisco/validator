package rules.number;

import org.junit.Test;
import util.UtilTest;

public class RuleDoubleTest {

    @Test
    public final void testRuleDoubleOk() throws Exception {
        System.out.println("=== TEST FOR `double` RULE ===\n");
        int port = UtilTest.launchWebServer("double");
        UtilTest.testIt("123", port, true);
        UtilTest.testIt("-123", port, true);
        UtilTest.testIt("-56789.3456789", port, true);
        UtilTest.testIt("987654.3456789", port, true);
        UtilTest.testIt(String.valueOf(Double.MAX_VALUE), port, true);
        UtilTest.testIt(String.valueOf(Double.MIN_VALUE), port, true);
    }

    @Test
    public final void testRuleDoubleNotOk() throws Exception {
        int port = UtilTest.launchWebServer("double");
        UtilTest.testIt("!@#$%^&****()_+≠–«‘[", port, false);
        UtilTest.testIt("9*123 ", port, false);
        UtilTest.testIt("9.34567%", port, false);
        UtilTest.testIt("-  123. ", port, false);
    }
}
