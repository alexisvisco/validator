package rules.other;

import org.junit.Test;
import util.UtilTest;


public class RuleRegexTest {

    @Test
    public final void testRuleBoolOk() throws Exception {
        System.out.println("=== TEST FOR `regex` RULE ===\n");
        UtilTest.testIt("regex: ([A-Z])", "AB", true);
        UtilTest.testIt("regex: ([A-Z])", "ABCD", true);
        UtilTest.testIt("regex: ([A-Z])", "F", true);
        UtilTest.testIt("regex: ([0-9])", "012345", true);
        UtilTest.testIt("regex: ([\\(])", "(", true);
    }

    @Test
    public final void testRuleBoolNotOk() throws Exception {
        UtilTest.testIt("regex: ([A-Z])", "123", false);
        UtilTest.testIt("regex: ([A-Z])", "jmskcmd", false);
        UtilTest.testIt("regex: ([A-Z])", "l", false);
        UtilTest.testIt("regex: ([0-9])", "qwe", false);
        UtilTest.testIt("regex: ([\\(])", ")", false);
    }
}
