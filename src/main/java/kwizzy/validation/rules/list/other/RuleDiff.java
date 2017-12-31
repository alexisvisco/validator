package kwizzy.validation.rules.list.other;

import kwizzy.validation.impl.Form;
import kwizzy.validation.rules.list.AbstractRule;
import kwizzy.validation.rules.DefaultRules;

import java.util.Optional;

/**
 * Ref to {@link DefaultRules#DIFF}<br/>
 * Usage: addRule("field", "diff:string str") <br/>
 * Example:
 * <pre>
 * "123" "123"    -> false
 * "123" "456"    -> true
 * "abc" "abc"    -> false
 * "123" "456"    -> true
 * </pre>
 **/
public class RuleDiff extends AbstractRule {

    @Override
    public boolean isOkay(Form f) {
        String param = getRuleInfo().getParams().get(0);
        Optional<String> s = f.getString(rule.getField());
        return s.filter(str -> !str.equals(param)).isPresent();
    }
}
