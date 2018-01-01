package kwizzy.validation.rules.list.other;

import kwizzy.validation.impl.Form;
import kwizzy.validation.rules.DefaultRules;
import kwizzy.validation.rules.list.AbstractRule;

import java.util.Optional;

/**
 * Ref to {@link DefaultRules#OPTIONAL}<br/>
 * Usage: addRule("field -> optional") <br/>
 *        addRule("field, hello -> optional: ([hello])") hello field will be ignored<br/>
 * This rule is used to set a field as optional.
 * If no value precised, the field will be ignored.
 **/
public class RuleOptional extends AbstractRule {

    @Override
    public boolean isOkay(Form f) {
        return true;
    }
}
