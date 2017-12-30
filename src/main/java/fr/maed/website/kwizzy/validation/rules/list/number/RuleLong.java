package fr.maed.website.kwizzy.validation.rules.list.number;

import fr.maed.website.kwizzy.validation.RuleInfo;
import fr.maed.website.kwizzy.validation.impl.Form;
import fr.maed.website.kwizzy.validation.rules.list.AbstractRule;
import fr.maed.website.kwizzy.validation.rules.DefaultRules;

import java.util.Optional;

/**
 * Ref to {@link DefaultRules#LONG}<br/>
 **/
public class RuleLong extends AbstractRule {

    @Override
    public boolean isOkay(Form f) {
        Optional<Long> s = f.getLong(rule.getField());
        return s.isPresent();
    }
}
