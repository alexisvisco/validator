package rules.other;

import org.junit.Test;
import util.UtilTest;


public class RuleDiffTest {

    @Test
    public final void testRuleMinOk() throws Exception {
        System.out.println("=== TEST FOR `diff` RULE ===\n");
        UtilTest.testIt("diff:hello world", "hello", true);
        UtilTest.testIt("diff:123", "1234", true);
    }

    @Test
    public final void testRuleMinNotOk() throws Exception {
        UtilTest.testIt("diff:hello world", "hello world", false);
        UtilTest.testIt("diff:123", "123", false);
    }
}
