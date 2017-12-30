package fr.maed.website.kwizzy.validation.rules;

import fr.maed.website.kwizzy.validation.RuleInfo;
import fr.maed.website.kwizzy.validation.rules.list.Rule;

public interface RuleObj {

    Class<? extends Rule> getRule();

    String getDefaultMessage();

    void setDefaultMessage(String ruleName, String message);

    String getRuleName();

    int getParamsCount();
}
