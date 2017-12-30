package fr.maed.website.kwizzy.validation.rules.list;

import fr.maed.website.kwizzy.validation.RuleInfo;

public abstract class AbstractRule implements Rule {

    protected RuleInfo rule;

    public AbstractRule() { }

    @Override
    public RuleInfo getRuleInfo() {
        return rule;
    }

    @Override
    public void injectRuleInfo(RuleInfo r) {
        this.rule = r;
    }
}
