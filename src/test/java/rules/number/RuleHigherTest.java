package rules.number;

import org.junit.Test;
import util.UtilTest;

public class RuleHigherTest {
    @Test
    public final void testRuleHigherOk() throws Exception {
        System.out.println("=== TEST FOR `lesser` RULE ===\n");
        UtilTest.testIt("higher:5", "124", true);
        UtilTest.testIt("higher:5", "12", true);
        UtilTest.testIt("higher:-15", "-12", true);
        UtilTest.testIt("higher:-15", "67", true);
    }

    @Test
    public final void testRuleHigherNotOk() throws Exception {
        UtilTest.testIt("higher:5", "!@#$%^&", false);
        UtilTest.testIt("higher:5", "12a", false);
        UtilTest.testIt("higher:5", "4", false);
        UtilTest.testIt("higher:5", "3", false);
        UtilTest.testIt("higher:5", "-4", false);
        UtilTest.testIt("higher:5", "0", false);
    }
}
