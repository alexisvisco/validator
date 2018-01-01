package rules.other;

import org.junit.Test;
import util.UtilTest;


public class RuleMinTest {

    @Test
    public final void testRuleMinOk() throws Exception {
        System.out.println("=== TEST FOR `min` RULE ===\n");
        UtilTest.testIt("min_length:3", "abcd", true);
        UtilTest.testIt("min_length:3", "ajknkjwnd", true);
        UtilTest.testIt("min_length:3", "11111111111111111111", true);
        UtilTest.testIt("min_length:0", "wdwdwdwdwa", true);
        UtilTest.testIt("min_length:0", " f eneo", true);
        UtilTest.testIt("min_length:0", ".", true);
    }

    @Test
    public final void testRuleMinNotOk() throws Exception {
        UtilTest.testIt("min_length:3", "a", false);
        UtilTest.testIt("min_length:3", "", false);
        UtilTest.testIt("min_length:10", "abcdefg", false);
        UtilTest.testIt("min_length:10", " f eneo", false);
        UtilTest.testIt("min_length:10", "", false);
        UtilTest.testIt("min_length:10", "..", false);

    }
}
