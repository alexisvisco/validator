package rules.number;

import org.junit.Test;
import util.UtilTest;

public class RuleRangeTest {
    @Test
    public final void testRuleRangeOk() throws Exception {
        System.out.println("=== TEST FOR `range` RULE ===\n");
        UtilTest.testIt("range:5, 10", "5", true);
        UtilTest.testIt("range:5, 10", "6", true);
        UtilTest.testIt("range:5, 10", "7", true);
        UtilTest.testIt("range:5, 10", "7.77", true);
        UtilTest.testIt("range:5, 10", "8", true);
        UtilTest.testIt("range:5, 10", "9", true);
        UtilTest.testIt("range:5, 10", "10", true);
    }

    @Test
    public final void testRuleRangeNotOk() throws Exception {
        UtilTest.testIt("range:5, 10", "!@#$%^&", false);
        UtilTest.testIt("range:5, 10", "4", false);
        UtilTest.testIt("range:5, 10", "4.45678", false);
        UtilTest.testIt("range:5, 10", "11", false);
        UtilTest.testIt("range:5, 10", "11.45678", false);
        UtilTest.testIt("range:5, 10", "-4", false);
        UtilTest.testIt("range:5, 10", "10.456789", false);
        UtilTest.testIt("range:5, 10", "678345678903456789234567893456789023456789034567890", false);
    }
}
