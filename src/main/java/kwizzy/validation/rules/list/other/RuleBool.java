package kwizzy.validation.rules.list.other;

import kwizzy.validation.impl.Form;
import kwizzy.validation.rules.list.AbstractRule;
import kwizzy.validation.rules.DefaultRules;

import java.util.Optional;

/**
 * Ref to {@link DefaultRules#BOOL}<br/>
 * Usage: addRule("field -> bool") <br/>
 * Example:
 * <pre>
 * "true"   -> true
 * "false"  -> true
 * "0"      -> true
 * "1"      -> true
 * "yes"    -> false
 * "no"     -> true
 * "abc"    -> true
 * </pre>
 **/
public class RuleBool extends AbstractRule {

    @Override
    public boolean isOkay(Form f) {
        Optional<Boolean> s = f.getBool(rule.getField());
        return s.isPresent();
    }
}
