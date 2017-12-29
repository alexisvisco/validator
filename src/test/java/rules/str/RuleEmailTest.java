package rules.str;

import org.junit.Test;
import util.UtilTest;


public class RuleEmailTest {

    @Test
    public final void testRuleNumericOk() throws Exception {
        System.out.println("=== TEST FOR `numeric` RULE ===\n");
        int port = UtilTest.launchWebServer("email");
        UtilTest.testIt("test@gmail.com", port, true);
        UtilTest.testIt("123@hotmal.com", port, true);
        UtilTest.testIt("123@hotmal.fr", port, true);
    }

    @Test
    public final void testRuleNumericNotOk() throws Exception {
        int port = UtilTest.launchWebServer("email");
        UtilTest.testIt("test@gmail.com.", port, false);
        UtilTest.testIt("..@fejwn.com", port, false);
        UtilTest.testIt("..@fejwn.", port, false);
        UtilTest.testIt("@", port, false);
        UtilTest.testIt("a.@.fr", port, false);
        UtilTest.testIt("--@fejwn.com", port, false);
    }
}
