package rules.js;

import org.junit.Test;
import util.UtilTest;

public class RuleJsArrayTest {

    @Test
    public final void testRuleJsObjOk() throws Exception {
        System.out.println("=== TEST FOR `json array` RULE ===\n");
        int port = UtilTest.launchWebServer("json_arr");
        UtilTest.testIt("[]", port, true);
        UtilTest.testIt("[1, 3, 3]", port, true);
        UtilTest.testIt("[\"1\", {\"1\": 3, \"123\": 123456789}]", port, true);
        UtilTest.testIt("[\"1\", []]", port, true);
        UtilTest.testIt("[\"1\", [1, 2, \"12\"]]", port, true);
        UtilTest.testIt("[1, null]", port, true);
        UtilTest.testIt("[1, 1]", port, true);
        UtilTest.testIt("[1, 2, 3,]", port, true);
    }

    @Test
    public final void testRuleJsObjNotOk() throws Exception {
        int port = UtilTest.launchWebServer("json_arr");
        UtilTest.testIt("!@#$%^&****()_+≠–«‘[", port, false);
        UtilTest.testIt("[", port, false);
        UtilTest.testIt("}, \"test\": 123", port, false);
        UtilTest.testIt("{}", port, false);
        UtilTest.testIt("{\"1\": 123, \"hello\": \"test\"}", port, false);
        UtilTest.testIt("{\"1\": 123, \"hello\": {1: 3, \"123\": 123456789}}", port, false);
        UtilTest.testIt("{\"1\": 123, \"hello\": \"[]\"}", port, false);
        UtilTest.testIt("{]", port, false);
        UtilTest.testIt("{1: }", port, false);
        UtilTest.testIt("{1: {1: 3, $}}", port, false);

    }
}
