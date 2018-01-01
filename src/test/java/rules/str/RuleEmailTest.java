package rules.str;

import org.junit.Test;
import util.UtilTest;


public class RuleEmailTest {

    @Test
    public final void testRuleNumericOk() throws Exception {
        System.out.println("=== TEST FOR `numeric` RULE ===\n");
        UtilTest.testIt("email", "test@gmail.com", true);
        UtilTest.testIt("email", "123@hotmal.com", true);
        UtilTest.testIt("email", "123@hotmal.fr", true);
    }

    @Test
    public final void testRuleNumericNotOk() throws Exception {
        UtilTest.testIt("email", "test@gmail.com.", false);
        UtilTest.testIt("email", "..@fejwn.com", false);
        UtilTest.testIt("email", "..@fejwn.", false);
        UtilTest.testIt("email", "@", false);
        UtilTest.testIt("email", "a.@.fr", false);
        UtilTest.testIt("email", "--@fejwn.com", false);
    }
}
