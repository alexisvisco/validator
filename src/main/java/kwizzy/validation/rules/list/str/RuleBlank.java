package kwizzy.validation.rules.list.str;

import kwizzy.validation.impl.Form;
import kwizzy.validation.rules.list.AbstractRule;
import kwizzy.validation.rules.DefaultRules;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * Ref to {@link DefaultRules#BLANK}<br/>
 * Usage: addRule("field -> blank") <br/>
 * Example:
 * <pre>
 * "   "     -> true
 * " "       -> true
 * ""        -> true
 * "    b "  -> false
 * </pre>
 **/
public class RuleBlank extends AbstractRule {

    @Override
    public boolean isOkay(Form f) {
        Optional<String> s = f.getString(rule.getField());
        return s.filter(StringUtils::isBlank).isPresent();
    }
}
