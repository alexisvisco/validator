package rules.str;

import org.junit.Test;
import util.UtilTest;


public class RuleBlankTest {

    @Test
    public final void testRuleBlankOk() throws Exception {
        System.out.println("=== TEST FOR `blank` RULE ===\n");
        UtilTest.testIt("blank", "  ", true);
        UtilTest.testIt("blank", "", true);
        UtilTest.testIt("blank", "             ", true);
        UtilTest.testIt("blank", "   ", true);
    }

    @Test
    public final void testRuleBlankNotOk() throws Exception {
        UtilTest.testIt("blank", "a", false);
        UtilTest.testIt("blank", ". ", false);
        UtilTest.testIt("blank", "   - ", false);
    }
}
