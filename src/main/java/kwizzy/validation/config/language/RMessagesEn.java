package kwizzy.validation.config.language;

import kwizzy.validation.RuleInfo;
import kwizzy.validation.rules.DefaultRules;
import kwizzy.validation.rules.RuleObj;

import java.util.Optional;

public class RMessagesEn implements RulesMessages {

    @Override
    public String getLang() {
        return "en";
    }

    @Override
    public Optional<String> getMessageFor(String ruleName, RuleInfo r) {
        Optional<RuleObj> o = DefaultRules.getByRuleName(ruleName);
        if (!o.isPresent())
                return Optional.empty();
        return Optional.ofNullable(replaceInfos(o.get().getDefaultMessage(), r));
    }

    @Override
    public void setMessageFor(String ruleName, String message) {
        Optional<RuleObj> o = DefaultRules.getByRuleName(ruleName);
        o.ifPresent(ruleObj -> ruleObj.setDefaultMessage(ruleName, message));
    }
}
