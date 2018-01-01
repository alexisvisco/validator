package rules.str;

import org.junit.Test;
import util.UtilTest;


public class RuleNumericTest {


    @Test
    public final void testRuleNumericOk() throws Exception {
        System.out.println("=== TEST FOR `numeric` RULE ===\n");
        UtilTest.testIt("numeric", "0123456789", true);
        UtilTest.testIt("numeric", "012", true);
        UtilTest.testIt("numeric", "67867880127878", true);
    }

    @Test
    public final void testRuleNumericNotOk() throws Exception {
        UtilTest.testIt("numeric", " 012", false);
        UtilTest.testIt("numeric", "abc 123", false);
        UtilTest.testIt("numeric", "123a", false);
        UtilTest.testIt("numeric", ".456", false);
    }
}
