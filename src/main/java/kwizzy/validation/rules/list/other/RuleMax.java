package kwizzy.validation.rules.list.other;

import kwizzy.validation.impl.Form;
import kwizzy.validation.rules.list.AbstractRule;
import kwizzy.validation.rules.DefaultRules;

import java.util.Optional;

/**
 * Ref to {@link DefaultRules#MAX}<br/>
 * Example:
 * <pre>
 * "123" 3    -> true
 * "ab"  3    -> true
 * ""  3      -> true
 * "abcd" 3   -> false
 * </pre>
 **/
public class RuleMax extends AbstractRule {

    @Override
    public boolean isOkay(Form f) {
        String param = getRuleInfo().getParams().get(0);
        try {
            int max = Integer.parseInt(param);
            Optional<String> s = f.getString(rule.getField());
            return s.filter(str -> str.length() <= max).isPresent();
        } catch (Exception e)
        {
            return false;
        }
    }
}
