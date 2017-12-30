package fr.maed.website.kwizzy.validation.rules.list;

import fr.maed.website.kwizzy.validation.RuleInfo;
import fr.maed.website.kwizzy.validation.impl.Form;

public interface Rule {

    boolean isOkay(Form form);

    RuleInfo getRuleInfo();

    void injectRuleInfo(RuleInfo r);

}
