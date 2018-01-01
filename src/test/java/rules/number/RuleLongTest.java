package rules.number;

import org.junit.Test;
import util.UtilTest;

public class RuleLongTest {
    @Test
    public final void testRuleLongOk() throws Exception {
        System.out.println("=== TEST FOR `long` RULE ===\n");
        UtilTest.testIt("long", "123", true);
        UtilTest.testIt("long", "-123", true);
        UtilTest.testIt("long", String.valueOf(Long.MAX_VALUE), true);
        UtilTest.testIt("long", String.valueOf(Long.MIN_VALUE), true);
    }

    @Test
    public final void testRuleLongNotOk() throws Exception {
        UtilTest.testIt("long", "!@#$%^&", true);
        UtilTest.testIt("long", "9*123 ", false);
        UtilTest.testIt("long", "-  123. ", false);
        UtilTest.testIt("long", "123 ", false);
        UtilTest.testIt("long", "12 4567", false);
        UtilTest.testIt("long", "12.34", false);
        UtilTest.testIt("long", String.valueOf(Long.MAX_VALUE) + "1", false);
    }
}
