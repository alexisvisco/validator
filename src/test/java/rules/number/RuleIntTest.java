package rules.number;

import org.junit.Test;
import util.UtilTest;

public class RuleIntTest {
    @Test
    public final void testRuleIntOk() throws Exception {
        System.out.println("=== TEST FOR `int` RULE ===\n");
        UtilTest.testIt("int", "123", true);
        UtilTest.testIt("int", "-123", true);
        UtilTest.testIt("int", String.valueOf(Integer.MAX_VALUE), true);
        UtilTest.testIt("int", String.valueOf(Integer.MIN_VALUE), true);
    }

    @Test
    public final void testRuleIntNotOk() throws Exception {
        UtilTest.testIt("int", "!@#$%^&", false);
        UtilTest.testIt("int", "9*123 ", false);
        UtilTest.testIt("int", "-  123. ", false);
        UtilTest.testIt("int", "123 ", false);
        UtilTest.testIt("int", "12 4567", false);
        UtilTest.testIt("int", "12.34", false);
        UtilTest.testIt("int", String.valueOf(Integer.MAX_VALUE) + "1", false);
    }
}
