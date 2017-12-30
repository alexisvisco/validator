package kwizzy.validation.rules.list.number;

import kwizzy.validation.impl.Form;
import kwizzy.validation.rules.list.AbstractRule;
import kwizzy.validation.rules.DefaultRules;

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
