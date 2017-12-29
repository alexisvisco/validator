package rules.number;

import org.junit.Test;
import util.UtilTest;

public class RuleLongTest {
    @Test
    public final void testRuleLongOk() throws Exception {
        System.out.println("=== TEST FOR `long` RULE ===\n");
        int port = UtilTest.launchWebServer("long");
        UtilTest.testIt("123", port, true);
        UtilTest.testIt("-123", port, true);
        UtilTest.testIt(String.valueOf(Long.MAX_VALUE), port, true);
        UtilTest.testIt(String.valueOf(Long.MIN_VALUE), port, true);
    }

    @Test
    public final void testRuleLongNotOk() throws Exception {
        int port = UtilTest.launchWebServer("long");
        UtilTest.testIt("!@#$%^&****()_+≠–«‘[", port, false);
        UtilTest.testIt("9*123 ", port, false);
        UtilTest.testIt("-  123. ", port, false);
        UtilTest.testIt("123 ", port, false);
        UtilTest.testIt("12 4567", port, false);
        UtilTest.testIt("12.34", port, false);
        UtilTest.testIt(String.valueOf(Long.MAX_VALUE) + "1", port, false);
    }
}
