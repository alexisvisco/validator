package rules.number;

import org.junit.Test;
import util.UtilTest;

public class RuleLesserTest {
    @Test
    public final void testRuleLesserOk() throws Exception {
        System.out.println("=== TEST FOR `lesser` RULE ===\n");
        UtilTest.testIt("lesser:5", "4", true);
        UtilTest.testIt("lesser:5", "3", true);
        UtilTest.testIt("lesser:5", "0", true);
        UtilTest.testIt("lesser:5", "0", true);
    }

    @Test
    public final void testRuleLesserNotOk() throws Exception {
        UtilTest.testIt("lesser:5", "!@#$%^&", false);
        UtilTest.testIt("lesser:5", "12", false);
        UtilTest.testIt("lesser:5", "12a", false);
        UtilTest.testIt("lesser:5", "678345678903456789234567893456789023456789034567890", false);
    }
}
