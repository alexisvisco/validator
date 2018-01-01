package rules.js;

import org.junit.Test;
import util.UtilTest;

public class RuleJsObjTest {

    @Test
    public final void testRuleJsObjOk() throws Exception {
        System.out.println("=== TEST FOR `json obj` RULE ===\n");
        UtilTest.testIt("json", "{}", true);
        UtilTest.testIt("json", "{\"1\": 123, \"hello\": \"test\"}", true);
        UtilTest.testIt("json", "{\"1\": 123, \"hello\": {1: 3, \"123\": 123456789}}", true);
        UtilTest.testIt("json", "{\"1\": 123, \"hello\": \"[]\"}", true);
        UtilTest.testIt("json", "{\"1\": 123, \"hello\": \"[1, 2, 3]\"}", true);
        UtilTest.testIt("json", "{1: \"123\",}", true);
        UtilTest.testIt("json", "{1: 345}", true);
        UtilTest.testIt("json", "{1: null}", true);
    }

    @Test
    public final void testRuleJsObjNotOk() throws Exception {
        UtilTest.testIt("json", "!@#$%^&", false);
        UtilTest.testIt("json", "{ ", false);
        UtilTest.testIt("json", "}, \"test\": 123", false);
        UtilTest.testIt("json", "[]", false);
        UtilTest.testIt("json", "[1, 2, 3]", false);
        UtilTest.testIt("json", "{]", false);
        UtilTest.testIt("json", "{1: }", false);
        UtilTest.testIt("json", "{1: ,}", false);
        UtilTest.testIt("json", "{1: \"helll}", false);
        UtilTest.testIt("json", "{1: helll\"}", false);
        UtilTest.testIt("json", "{1: 'undefined}", false);
        UtilTest.testIt("json", "{1: 1, 3: \"hi\", 3: 1}", false);
    }
}
