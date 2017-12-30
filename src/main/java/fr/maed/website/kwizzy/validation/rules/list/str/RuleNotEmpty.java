package fr.maed.website.kwizzy.validation.rules.list.str;

import fr.maed.website.kwizzy.validation.RuleInfo;
import fr.maed.website.kwizzy.validation.impl.Form;
import fr.maed.website.kwizzy.validation.rules.list.AbstractRule;
import fr.maed.website.kwizzy.validation.rules.DefaultRules;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * Ref to {@link DefaultRules#NOT_EMPTY}<br/>
 * Example:
 * <pre>
 * ""        -> false
 * " "       -> false
 * "     "   -> false
 * "    b "  -> true
 * </pre>
 **/
public class RuleNotEmpty extends AbstractRule {

    @Override
    public boolean isOkay(Form f) {
        Optional<String> s = f.getString(rule.getField());
        return s.filter(str -> StringUtils.isNotEmpty(str) && StringUtils.isNotBlank(str)).isPresent();
    }
}
