package rules.js;

import org.junit.Test;
import util.UtilTest;

public class RuleJsObjTest {

    @Test
    public final void testRuleJsObjOk() throws Exception {
        System.out.println("=== TEST FOR `json obj` RULE ===\n");
        int port = UtilTest.launchWebServer("json");
        UtilTest.testIt("{}", port, true);
        UtilTest.testIt("{\"1\": 123, \"hello\": \"test\"}", port, true);
        UtilTest.testIt("{\"1\": 123, \"hello\": {1: 3, \"123\": 123456789}}", port, true);
        UtilTest.testIt("{\"1\": 123, \"hello\": \"[]\"}", port, true);
        UtilTest.testIt("{\"1\": 123, \"hello\": \"[1, 2, 3]\"}", port, true);
        UtilTest.testIt("{1: \"123\",}", port, true);
        UtilTest.testIt("{1: 345}", port, true);
        UtilTest.testIt("{1: null}", port, true);
    }

    @Test
    public final void testRuleJsObjNotOk() throws Exception {
        int port = UtilTest.launchWebServer("json");
        UtilTest.testIt("!@#$%^&", port, false);
        UtilTest.testIt("{ ", port, false);
        UtilTest.testIt("}, \"test\": 123", port, false);
        UtilTest.testIt("[]", port, false);
        UtilTest.testIt("[1, 2, 3]", port, false);
        UtilTest.testIt("{]", port, false);
        UtilTest.testIt("{1: }", port, false);
        UtilTest.testIt("{1: ,}", port, false);
        UtilTest.testIt("{1: \"helll}", port, false);
        UtilTest.testIt("{1: helll\"}", port, false);
        UtilTest.testIt("{1: 'undefined}", port, false);
        UtilTest.testIt("{1: 1, 3: \"hi\", 3: 1}", port, false);
    }
}
