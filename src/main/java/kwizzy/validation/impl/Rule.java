package kwizzy.validation.impl;

import kwizzy.validation.RuleInfo;
import kwizzy.validation.impl.Form;

public interface Rule {

    boolean isOkay(Form form);

    RuleInfo getRuleInfo();

    void injectRuleInfo(RuleInfo r);

}
