package kwizzy.validation.rules.list.number;

import kwizzy.validation.impl.Form;
import kwizzy.validation.rules.list.AbstractRule;
import kwizzy.validation.rules.DefaultRules;

import java.util.Optional;

/**
 * Ref to {@link DefaultRules#FLOAT}<br/>
 * Usage: addRule("field", "float") <br/>
 **/
public class RuleFloat extends AbstractRule {

    @Override
    public boolean isOkay(Form f) {
        Optional<Float> s = f.getFloat(rule.getField());
        return s.isPresent();
    }
}
