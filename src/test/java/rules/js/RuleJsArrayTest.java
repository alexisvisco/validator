package rules.js;

import org.junit.Test;
import util.FTest;
import util.UtilTest;

public class RuleJsArrayTest {

    @Test
    public final void testRuleJsArrayOk() throws Exception {
        FTest.getInstance()
        .test("json array")
        .field("test", "[]").rule("test", "json_arr", true).build()
        .test().field("test", "[]").rule("test", "json_arr", true).build()
        .test().field("test", "[1, 3, 3]").rule("test", "json_arr", true).build()
        .test().field("test", "[\"1\", {\"1\": 3, \"123\": 123456789}]").rule("test", "json_arr", true).build()
        .test().field("test", "[\"1\", []]").rule("test", "json_arr", true).build()
        .test().field("test", "[\"1\", [1, 2, \"12\"]]").rule("test", "json_arr", true).build()
        .test().field("test", "[1, null]").rule("test", "json_arr", true).build()
        .test().field("test", "[1, 1]").rule("test", "json_arr", true).build()
        .test().field("test", "[1, 2, 3,]").rule("test", "json_arr", true).build();
    }

    @Test
    public final void testRuleJsArrayNotOk() throws Exception {
        FTest.getInstance()
        .test("json array")
        .field("test", "!@#$%^&").rule("test", "json_arr", false).build()
        .test().field("test", "[").rule("test", "json_arr", false).build()
        .test().field("test", "[1, 2 {}...]").rule("test", "json_arr", false).build()
        .test().field("test", "}").rule("test", "json_arr", false).build()
        .test().field("test", "{}").rule("test", "json_arr", false).build()
        .test().field("test", "{\"1\": 123, \"hello\": \"test\"}").rule("test", "json_arr", false).build()
        .test().field("test", "{\"1\": 123, \"hello\": {1: 3, \"123\": 123456789}}").rule("test", "json_arr", false).build()
        .test().field("test", "{\"1\": 123, \"hello\": \"[]\"}").rule("test", "json_arr", false).build()
        .test().field("test", "{]").rule("test", "json_arr", false).build()
        .test().field("test", "{1: }").rule("test", "json_arr", false).build()
        .test().field("test", "{1: {1: 3, $}}").rule("test", "json_arr", false).build();

    }
}
