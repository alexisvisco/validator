package kwizzy.validation.rules.list.str;

import kwizzy.validation.impl.Form;
import kwizzy.validation.rules.list.AbstractRule;
import kwizzy.validation.rules.DefaultRules;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * Ref to {@link DefaultRules#ALPHA_NUM_SPACE}<br/>
 * Usage: addRule("field", "alpha_num_space") <br/>
 * Example:
 * <pre>
 * "123abc"     -> true
 * "123"        -> true
 * "  "         -> true
 * "  abc"      -> true
 * ".abc"       -> false
 * "@#$%^&*("   -> false
 * "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"  -> true
 * </pre>
 **/
public class RuleAlphaNumSpace extends AbstractRule {

    @Override
    public boolean isOkay(Form f) {
        Optional<String> s = f.getString(rule.getField());
        return s.filter(StringUtils::isAlphanumericSpace).isPresent();
    }
}
