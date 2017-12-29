package fr.maed.website.kwizzy.validation.rules.list.number;

import fr.maed.website.kwizzy.validation.RuleInfo;
import fr.maed.website.kwizzy.validation.impl.Form;
import fr.maed.website.kwizzy.validation.rules.AbstractRule;
import fr.maed.website.kwizzy.validation.rules.Rules;

import java.util.Optional;

/**
 * Ref to {@link Rules#LONG}<br/>
 **/
public class RuleLong extends AbstractRule {

    public RuleLong(RuleInfo value) {
        super(value);
    }

    @Override
    public boolean isOkay(Form f) {
        Optional<Long> s = f.getLong(rule.getPath());
        return s.isPresent();
    }
}
