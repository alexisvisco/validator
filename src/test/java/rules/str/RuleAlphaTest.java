package rules.str;

import org.junit.Test;
import util.UtilTest;


public class RuleAlphaTest {

    @Test
    public final void testRuleAlphaOk() throws Exception {
        System.out.println("=== TEST FOR `alpha` RULE ===\n");
        UtilTest.testIt("alpha", "abcdefghijklmnopqrstuvwxyz", true);
        UtilTest.testIt("alpha", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", true);
        UtilTest.testIt("alpha", "abcABC", true);
    }

    @Test
    public final void testRuleAlphaNotOk() throws Exception {
        UtilTest.testIt("alpha", "abc123", false);
        UtilTest.testIt("alpha", "abc 123", false);
        UtilTest.testIt("alpha", "  124 ", false);
        UtilTest.testIt("alpha", "   ", false);
    }
}
