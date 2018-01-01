package rules.other;

import org.junit.Test;
import util.FTest;
import util.UtilTest;


public class RuleOptionalTest {

    @Test
    public final void testRuleOptionalOk() throws Exception {
        FTest.getInstance().test("optional")
                .rule("test", "optional", true)
                .field("test", "1").build()
                .test().rule("test, facebook", "optional: ([facebook])", true)
                .field("test", "1").build()
                .test().rule("test", "optional", true)
                .build().test()
                .rule("test", "optional|email", true)
                .field("test", "email@email.com")
                .build();
    }

    @Test
    public final void testRuleOptionalNotOk() throws Exception {
        FTest.getInstance().test("optional")
                .rule("test", "optional|higher:5", false)
                .field("test", "1").build()
                .test().rule("test, facebook", "optional: ([test])", false)
                .rule("test", "optional", true) // to detect that test is optional but not facebook
                .field("test", "1").build();
    }
}
