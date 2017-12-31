package rules.other;

import impl.RandomUser;
import org.junit.Test;
import util.UtilTest;


public class RuleUniqueTest {

    @Test
    public final void testRuleBoolOk() throws Exception {
        System.out.println("=== TEST FOR `unique` RULE ===\n");
        int port = UtilTest.launchWebServer("unique: user, mail|email");
        UtilTest.testIt("jean@mail.com", port, true);
        UtilTest.testIt("christian@gmail.com", port, true);
    }

    @Test
    public final void testRuleExistNotOk() throws Exception {
        int port = UtilTest.launchWebServer("unique: user, mail|email");
        UtilTest.testIt(RandomUser.randoms.get(0).email, port, false);
        UtilTest.testIt(RandomUser.randoms.get(1).email, port, false);
        UtilTest.testIt(RandomUser.randoms.get(3).email, port, false);
        UtilTest.testIt("not email an email", port, false);
    }
}
