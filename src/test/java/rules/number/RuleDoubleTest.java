package rules.number;

import org.junit.Test;
import util.UtilTest;

public class RuleDoubleTest {

    @Test
    public final void testRuleDoubleOk() throws Exception {
        System.out.println("=== TEST FOR `double` RULE ===\n");
        UtilTest.testIt("double", "123", true);
        UtilTest.testIt("double", "-123", true);
        UtilTest.testIt("double", "-56789.3456789", true);
        UtilTest.testIt("double", "987654.3456789", true);
        UtilTest.testIt("double", String.valueOf(Double.MAX_VALUE), true);
        UtilTest.testIt("double", String.valueOf(Double.MIN_VALUE), true);
    }

    @Test
    public final void testRuleDoubleNotOk() throws Exception {
        UtilTest.testIt("double", "!@#$%^&", false);
        UtilTest.testIt("double", "9*123 ", false);
        UtilTest.testIt("double", "9.34567%", false);
        UtilTest.testIt("double", "-  123. ", false);
    }
}
