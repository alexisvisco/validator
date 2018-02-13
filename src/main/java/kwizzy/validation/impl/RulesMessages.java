package kwizzy.validation.impl;

import kwizzy.validation.RuleInfo;
import kwizzy.validation.config.ValidatorConfig;

import java.util.Optional;

public interface RulesMessages {

    String getLang();

    default Optional<String> getMessageFor(String ruleName, RuleInfo r)
    {
        return ValidatorConfig.cfg().ruleList.stream().filter(e -> e.getRuleName().equals(ruleName))
                .map(e -> replaceInfos(e.getDefaultMessage(), r)).findAny();
    }

    void setMessageFor(String ruleName, String message);

    default String replaceInfos(String str, RuleInfo r) {
        String s = str.replace(":attr", r.getField());
        for (int i = 0; i < r.getParamsCount(); i++)
            s = s.replace(":" + (i + 1), r.getParams().get(i));
        return s;
    }
}
