package rules.other;

import org.junit.Test;
import util.UtilTest;


public class RuleMaxTest {

    @Test
    public final void testRuleMinOk() throws Exception {
        System.out.println("=== TEST FOR `max` RULE ===\n");
        UtilTest.testIt("max_length:3", "abc", true);
        UtilTest.testIt("max_length:3", "a", true);
        UtilTest.testIt("max_length:10", "abcdefg", true);
        UtilTest.testIt("max_length:10", " f eneo", true);
        UtilTest.testIt("max_length:10", ".", true);
    }

    @Test
    public final void testRuleMaxNotOk() throws Exception {
        UtilTest.testIt("max_length:3", "abcd", false);
        UtilTest.testIt("max_length:3", "ajknkjwnd", false);
        UtilTest.testIt("max_length:3", "11111111111111111111", false);
        UtilTest.testIt("max_length:0", "wdwdwdwdwa", false);
        UtilTest.testIt("max_length:0", " f eneo", false);
        UtilTest.testIt("max_length:0", ".", false);
    }
}
