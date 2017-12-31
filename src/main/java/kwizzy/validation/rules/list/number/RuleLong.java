package kwizzy.validation.rules.list.number;

import kwizzy.validation.impl.Form;
import kwizzy.validation.rules.list.AbstractRule;
import kwizzy.validation.rules.DefaultRules;

import java.util.Optional;

/**
 * Ref to {@link DefaultRules#LONG}<br/>
 * Usage: addRule("field -> long") <br/>
 **/
public class RuleLong extends AbstractRule {

    @Override
    public boolean isOkay(Form f) {
        Optional<Long> s = f.getLong(rule.getField());
        return s.isPresent();
    }
}
