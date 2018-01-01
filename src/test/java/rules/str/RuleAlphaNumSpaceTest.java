package rules.str;

import org.junit.Test;
import util.UtilTest;


public class RuleAlphaNumSpaceTest {

    @Test
    public final void testRuleAlphaNumSpaceOk() throws Exception {
        System.out.println("=== TEST FOR `alpha_num` RULE ===\n");
        UtilTest.testIt("alpha_num_space", "123", true);
        UtilTest.testIt("alpha_num_space", "abc123", true);
        UtilTest.testIt("alpha_num_space", "ab c1 23", true);
        UtilTest.testIt("alpha_num_space", "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", true);
        UtilTest.testIt("alpha_num_space", " 0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", true);
    }

    @Test
    public final void testRuleAlphaNotNumSpaceOk() throws Exception {
        UtilTest.testIt("alpha_num_space", "!@#$%^&", false);
        UtilTest.testIt("alpha_num_space", "9*123 ", false);
        UtilTest.testIt("alpha_num_space", "abc. ", false);
        UtilTest.testIt("alpha_num_space", "[]", false);
        UtilTest.testIt("alpha_num_space", ".0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", false);
    }
}
