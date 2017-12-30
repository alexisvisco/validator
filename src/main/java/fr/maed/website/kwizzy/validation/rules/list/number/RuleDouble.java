package fr.maed.website.kwizzy.validation.rules.list.number;

import fr.maed.website.kwizzy.validation.RuleInfo;
import fr.maed.website.kwizzy.validation.impl.Form;
import fr.maed.website.kwizzy.validation.rules.list.AbstractRule;
import fr.maed.website.kwizzy.validation.rules.DefaultRules;

import java.util.Optional;

/**
 * Ref to {@link DefaultRules#DOUBLE}<br/>
 **/
public class RuleDouble extends AbstractRule {

    @Override
    public boolean isOkay(Form f) {
        Optional<Double> s = f.getDouble(rule.getField());
        return s.isPresent();
    }
}
