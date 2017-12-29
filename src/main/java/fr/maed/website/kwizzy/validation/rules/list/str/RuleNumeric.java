package fr.maed.website.kwizzy.validation.rules.list.str;

import fr.maed.website.kwizzy.validation.RuleInfo;
import fr.maed.website.kwizzy.validation.impl.Form;
import fr.maed.website.kwizzy.validation.rules.AbstractRule;
import fr.maed.website.kwizzy.validation.rules.Rules;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
/**
 * Ref to {@link Rules#NUMERIC}<br/>
 * Example:
 * <pre>
 * "abc"    -> false
 * "0123"   -> true
 * "abc12"  -> false
 * "123 "   -> true
 * </pre>
 **/
public class RuleNumeric extends AbstractRule {

    public RuleNumeric(RuleInfo value) {
        super(value);
    }

    @Override
    public boolean isOkay(Form f) {
        Optional<String> s = f.getString(rule.getPath());
        return s.filter(StringUtils::isNumeric).isPresent();
    }
}
