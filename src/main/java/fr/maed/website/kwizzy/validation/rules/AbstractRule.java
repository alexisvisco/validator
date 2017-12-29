package fr.maed.website.kwizzy.validation.rules;

import fr.maed.website.kwizzy.validation.RuleInfo;

public abstract class AbstractRule implements Rule {

    protected RuleInfo rule;

    public AbstractRule(RuleInfo value) {
        this.rule = value;
    }

    @Override
    public RuleInfo getRuleInfo() {
        return rule;
    }
}
