package rules.str;

import org.junit.Test;
import util.UtilTest;


public class RuleUrlTest {

    @Test
    public final void testRuleUrlOk() throws Exception {
        System.out.println("=== TEST FOR `numeric` RULE ===\n");
        int port = UtilTest.launchWebServer("url");
        UtilTest.testIt("http://foo.com/blah_blah", port, true);
        UtilTest.testIt("http://foo.com/blah_blah/", port, true);
        UtilTest.testIt("https://www.example.com/foo/?bar=baz&inga=42&quux", port, true);
        UtilTest.testIt("http://142.42.1.1/", port, true);
        UtilTest.testIt("http://j.mp", port, true);
    }

    @Test
    public final void testRuleUrlNotOk() throws Exception {
        int port = UtilTest.launchWebServer("numeric");
        UtilTest.testIt("http://" , port, false);
        UtilTest.testIt("http://##/", port, false);
        UtilTest.testIt("http://foo.bar?q=Spaces should be encoded", port, false);
        UtilTest.testIt("http:// shouldfail.com", port, false);
    }
}
