package kwizzy.validation.rules.list.number;

import kwizzy.validation.impl.Form;
import kwizzy.validation.rules.list.AbstractRule;
import kwizzy.validation.rules.DefaultRules;

import java.util.Optional;

/**
 * Ref to {@link DefaultRules#INT}<br/>
 **/
public class RuleInt extends AbstractRule {

    @Override
    public boolean isOkay(Form f) {
        Optional<Integer> s = f.getInt(rule.getField());
        return s.isPresent();
    }
}
