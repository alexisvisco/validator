package rules.other;

import org.junit.Test;
import util.UtilTest;


public class RuleBoolTest {

    @Test
    public final void testRuleBoolOk() throws Exception {
        System.out.println("=== TEST FOR `bool` RULE ===\n");
        UtilTest.testIt("bool", "true", true);
        UtilTest.testIt("bool", "false", true);
        UtilTest.testIt("bool", "0", true);
        UtilTest.testIt("bool", "1", true);
    }

    @Test
    public final void testRuleBoolNotOk() throws Exception {
        UtilTest.testIt("bool", "truee", false);
        UtilTest.testIt("bool", "flasee", false);
        UtilTest.testIt("bool", " 0", false);
        UtilTest.testIt("bool", " 1", false);
        UtilTest.testIt("bool", "2", false);
        UtilTest.testIt("bool", "yes", false);
        UtilTest.testIt("bool", "no", false);
    }
}
