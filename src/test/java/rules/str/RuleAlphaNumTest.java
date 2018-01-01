package rules.str;

import org.junit.Test;
import util.UtilTest;


public class RuleAlphaNumTest {

    @Test
    public final void testRuleAlphaNumOk() throws Exception {
        System.out.println("=== TEST FOR `alpha_num` RULE ===\n");
        UtilTest.testIt("alpha_num", "123", true);
        UtilTest.testIt("alpha_num", "abc123", true);
        UtilTest.testIt("alpha_num", "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", true);
    }

    @Test
    public final void testRuleAlphaNotNumOk() throws Exception {
        UtilTest.testIt("alpha_num", " ", false);
        UtilTest.testIt("alpha_num", "123 ", false);
        UtilTest.testIt("alpha_num", "abc ", false);
        UtilTest.testIt("alpha_num", "[]", false);
        UtilTest.testIt("alpha_num", ".0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", false);
    }
}
