package rules.str;

import org.junit.Test;
import util.UtilTest;


public class RuleAlphaNumTest {

    @Test
    public final void testRuleAlphaNumOk() throws Exception {
        System.out.println("=== TEST FOR `alpha_num` RULE ===\n");
        int port = UtilTest.launchWebServer("alpha_num");
        UtilTest.testIt("123", port, true);
        UtilTest.testIt("abc123", port, true);
        UtilTest.testIt("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", port, true);
    }

    @Test
    public final void testRuleAlphaNotNumOk() throws Exception {
        int port = UtilTest.launchWebServer("alpha_num");
        UtilTest.testIt(" ", port, false);
        UtilTest.testIt("123 ", port, false);
        UtilTest.testIt("abc ", port, false);
        UtilTest.testIt("[]", port, false);
        UtilTest.testIt(".0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", port, false);
    }
}
