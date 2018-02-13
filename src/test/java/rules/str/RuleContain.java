package rules.str;

import impl.CustomRules;
import kwizzy.validation.Validator;
import kwizzy.validation.config.ValidatorConfig;
import kwizzy.validation.config.language.ConstructRMLang;
import kwizzy.validation.config.language.RMessagesEn;
import org.junit.Test;
import util.UtilTest;

import java.util.Collections;

public class RuleContain {
    @Test
    public final void testRuleLesserOk() throws Exception {
        Collections.addAll(ValidatorConfig.cfg().ruleList, CustomRules.values());
        ValidatorConfig.cfg().defaultLang = "fr";
        ValidatorConfig.cfg().languageList.add(new RMessagesFr());
        System.out.println("=== TEST FOR `lesser` RULE ===\n");
        UtilTest.testIt("contain:(Hello)", "Hello world", true);
    }

//    @Test
//    public final void testRuleLesserNotOk() throws Exception {
//        UtilTest.testIt("lesser:5", "!@#$%^&", false);
//        UtilTest.testIt("lesser:5", "12", false);
//        UtilTest.testIt("lesser:5", "12a", false);
//        UtilTest.testIt("lesser:5", "678345678903456789234567893456789023456789034567890", false);
//    }
}
