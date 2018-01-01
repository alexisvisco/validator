package rules.other;

import impl.RandomUser;
import org.junit.Test;
import util.UtilTest;


public class RuleUniqueTest {

    @Test
    public final void testRuleUniqueOk() throws Exception {
        System.out.println("=== TEST FOR `unique` RULE ===\n");
        UtilTest.testIt("unique: user, mail|email", "jean@mail.com", true);
        UtilTest.testIt("unique: user, mail|email", "christian@gmail.com", true);
        UtilTest.testIt("unique: user, mail|email", "123@gmail.com", true);

    }

    @Test
    public final void testRuleUniqueNotOk() throws Exception {
        UtilTest.testIt("unique: user, mail|email", RandomUser.randoms.get(0).email, false);
        UtilTest.testIt("unique: user, mail|email", RandomUser.randoms.get(1).email, false);
        UtilTest.testIt("unique: user, mail|email", RandomUser.randoms.get(3).email, false);
        UtilTest.testIt("unique: user, mail|email", "not email an email", false);
    }
}
