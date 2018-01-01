package rules.str;

import org.junit.Test;
import util.UtilTest;


public class RuleUrlTest {

    @Test
    public final void testRuleUrlOk() throws Exception {
        System.out.println("=== TEST FOR `numeric` RULE ===\n");
        UtilTest.testIt("url", "http://foo.com/blah_blah", true);
        UtilTest.testIt("url", "http://foo.com/blah_blah/", true);
        UtilTest.testIt("url", "https://www.example.com/foo/?bar=baz&inga=42&quux", true);
        UtilTest.testIt("url", "http://142.42.1.1/", true);
        UtilTest.testIt("url", "http://j.mp", true);
        UtilTest.testIt("url", "http://foo.bar?q=Spaces should be encoded", true);
    }

    @Test
    public final void testRuleUrlNotOk() throws Exception {
        UtilTest.testIt("url", "http://" , false);
        UtilTest.testIt("url", "hello world" , false);
        UtilTest.testIt("url", "http://##/", false);
        UtilTest.testIt("url", "http:// shouldfail.com", false);
    }
}
