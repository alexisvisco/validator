package fr.maed.website.kwizzy.validation.rules.list.str;

import fr.maed.website.kwizzy.validation.RuleInfo;
import fr.maed.website.kwizzy.validation.impl.Form;
import fr.maed.website.kwizzy.validation.rules.AbstractRule;
import org.apache.commons.lang3.StringUtils;
import fr.maed.website.kwizzy.validation.rules.Rules;

import java.util.Optional;

/**
 * Ref to {@link Rules#NOT_EMPTY}<br/>
 * Example:
 * <pre>
 * ""        -> false
 * " "       -> false
 * "     "   -> false
 * "    b "  -> true
 * </pre>
 **/
public class RuleNotEmpty extends AbstractRule {

    public RuleNotEmpty(RuleInfo value) {
        super(value);
    }

    @Override
    public boolean isOkay(Form f) {
        Optional<String> s = f.getString(rule.getPath());
        return s.filter(str -> StringUtils.isNotEmpty(str) && StringUtils.isNotBlank(str)).isPresent();
    }
}
