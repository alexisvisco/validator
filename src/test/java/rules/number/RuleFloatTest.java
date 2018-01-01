package rules.number;

import org.junit.Test;
import util.UtilTest;

public class RuleFloatTest {

    @Test
    public final void testRuleFloatOk() throws Exception {
        System.out.println("=== TEST FOR `float` RULE ===\n");
        UtilTest.testIt("float", "123", true);
        UtilTest.testIt("float", "-123", true);
        UtilTest.testIt("float", "-56789.3456789", true);
        UtilTest.testIt("float", "987654.3456789", true);
        UtilTest.testIt("float", String.valueOf(Float.MAX_VALUE), true);
        UtilTest.testIt("float", String.valueOf(Float.MIN_VALUE), true);
    }

    @Test
    public final void testRuleFloatNotOk() throws Exception {
        UtilTest.testIt("float", "!@#$%^&", false);
        UtilTest.testIt("float", "9*123 ", false);
        UtilTest.testIt("float", "9.34567%", false);
        UtilTest.testIt("float", "-  123. ", false);
    }
}
