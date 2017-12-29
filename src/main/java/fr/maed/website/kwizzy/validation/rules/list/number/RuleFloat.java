package fr.maed.website.kwizzy.validation.rules.list.number;

import fr.maed.website.kwizzy.validation.RuleInfo;
import fr.maed.website.kwizzy.validation.impl.Form;
import fr.maed.website.kwizzy.validation.rules.AbstractRule;
import fr.maed.website.kwizzy.validation.rules.Rules;

import java.util.Optional;

/**
 * Ref to {@link Rules#FLOAT}<br/>
 **/
public class RuleFloat extends AbstractRule {

    public RuleFloat(RuleInfo value) {
        super(value);
    }

    @Override
    public boolean isOkay(Form f) {
        Optional<Float> s = f.getFloat(rule.getPath());
        return s.isPresent();
    }
}
