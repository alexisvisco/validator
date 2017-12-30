package kwizzy.validation.rules.list.str;

import kwizzy.validation.impl.Form;
import kwizzy.validation.rules.list.AbstractRule;
import kwizzy.validation.rules.DefaultRules;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
/**
 * Ref to {@link DefaultRules#NUMERIC}<br/>
 * Example:
 * <pre>
 * "abc"    -> false
 * "0123"   -> true
 * "abc12"  -> false
 * "123 "   -> true
 * </pre>
 **/
public class RuleNumeric extends AbstractRule {

    @Override
    public boolean isOkay(Form f) {
        Optional<String> s = f.getString(rule.getField());
        return s.filter(StringUtils::isNumeric).isPresent();
    }
}
