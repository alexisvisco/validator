package rules.str;

import org.junit.Test;
import util.UtilTest;


public class RuleAlphaNumSpaceTest {

    @Test
    public final void testRuleAlphaNumSpaceOk() throws Exception {
        System.out.println("=== TEST FOR `alpha_num` RULE ===\n");
        int port = UtilTest.launchWebServer("alpha_num_space");
        UtilTest.testIt("123", port, true);
        UtilTest.testIt("abc123", port, true);
        UtilTest.testIt("ab c1 23", port, true);
        UtilTest.testIt("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", port, true);
        UtilTest.testIt(" 0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", port, true);
    }

    @Test
    public final void testRuleAlphaNotNumSpaceOk() throws Exception {
        int port = UtilTest.launchWebServer("alpha_num_space");
        UtilTest.testIt("!@#$%^&****()_+≠–«‘[", port, false);
        UtilTest.testIt("9*123 ", port, false);
        UtilTest.testIt("abc. ", port, false);
        UtilTest.testIt("[]", port, false);
        UtilTest.testIt(".0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", port, false);
    }
}
