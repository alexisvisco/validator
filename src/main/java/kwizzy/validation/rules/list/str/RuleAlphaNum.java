package kwizzy.validation.rules.list.str;

import kwizzy.validation.impl.Form;
import kwizzy.validation.rules.list.AbstractRule;
import kwizzy.validation.rules.DefaultRules;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * Ref to {@link DefaultRules#ALPHA_NUM}<br/>
 * Usage: addRule("field", "alpha_num") <br/>
 * Example:
 * <pre>
 * "123abc"  -> true
 * "123"     -> true
 * "  "      -> false
 * "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"  -> true
 * </pre>
 **/
public class RuleAlphaNum extends AbstractRule {

    @Override
    public boolean isOkay(Form f) {
        Optional<String> s = f.getString(rule.getField());
        return s.filter(StringUtils::isAlphanumeric).isPresent();
    }
}
