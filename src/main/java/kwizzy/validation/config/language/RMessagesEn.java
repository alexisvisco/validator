package kwizzy.validation.config.language;

import kwizzy.validation.RuleInfo;
import kwizzy.validation.Validator;
import kwizzy.validation.config.ValidatorConfig;
import kwizzy.validation.impl.RulesMessages;
import kwizzy.validation.rules.DefaultRules;
import kwizzy.validation.impl.RuleDescriptor;

import java.util.Optional;

public class RMessagesEn implements RulesMessages {

    @Override
    public String getLang() {
        return "en";
    }



    @Override
    public void setMessageFor(String ruleName, String message) {
        Optional<RuleDescriptor> o = DefaultRules.getByRuleName(ruleName);
        o.ifPresent(ruleObj -> ruleObj.setDefaultMessage(ruleName, message));
    }
}
