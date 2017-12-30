package rules.str;

import org.json.JSONObject;
import org.junit.Test;
import util.FTest;
import util.ToMap;
import util.UtilTest;

import java.util.Map;

import static util.ToMap.toMap;


public class RuleConfirmTest {

    @Test
    public final void testRuleConfirmOk() throws Exception {
        FTest.getInstance()
                .test("confirm success")
                .field("pass", "123").field("pass_confirm", "123").rule("pass", "confirm", true)
                .build().test()
                .field("hello", "hello world").field("hello_confirm", "hello world").rule("hello", "confirm", true)
                .build().test()
                .field("hello", "helloxworld").field("hello_confirm", "helloxworld").rule("hello", "confirm", true)
                .build();
    }

    @Test
    public final void testRuleConfirmNotOk() throws Exception {
        FTest.getInstance()
                .test("confirm fail")
                .field("pass", "123").field("pass_confirm", "1234").rule("pass", "confirm", false)
                .build().test()
                .field("hello", "hello world").rule("hello", "confirm", false)
                .build().test()
                .field("hel2lo", "helloxworld").field("hello_confirm", "helloxworld").rule("hello", "confirm", false)
                .build();
    }
}
