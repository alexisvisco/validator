package rules.str;

import org.junit.Test;
import util.UtilTest;


public class RuleNumericTest {


    @Test
    public final void testRuleNumericOk() throws Exception {
        System.out.println("=== TEST FOR `numeric` RULE ===\n");
        int port = UtilTest.launchWebServer("numeric");
        UtilTest.testIt("0123456789", port, true);
        UtilTest.testIt("012", port, true);
        UtilTest.testIt("67867880127878", port, true);
    }

    @Test
    public final void testRuleNumericNotOk() throws Exception {
        int port = UtilTest.launchWebServer("numeric");
        UtilTest.testIt(" 012", port, false);
        UtilTest.testIt("abc 123", port, false);
        UtilTest.testIt("123a", port, false);
        UtilTest.testIt(".456", port, false);
    }
}
