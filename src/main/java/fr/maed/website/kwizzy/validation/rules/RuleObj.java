package fr.maed.website.kwizzy.validation.rules;

import fr.maed.website.kwizzy.validation.RuleInfo;

public interface RuleObj {
    Class<? extends AbstractRule> getRule();
    String getDefaultMessage(RuleInfo r);
    String getRuleName();
    int getParamsCount();
}
