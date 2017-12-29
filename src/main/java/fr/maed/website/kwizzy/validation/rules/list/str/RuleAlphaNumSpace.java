package fr.maed.website.kwizzy.validation.rules.list.str;

import fr.maed.website.kwizzy.validation.RuleInfo;
import fr.maed.website.kwizzy.validation.impl.Form;
import fr.maed.website.kwizzy.validation.rules.AbstractRule;
import fr.maed.website.kwizzy.validation.rules.Rules;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * Ref to {@link Rules#ALPHA_NUM_SPACE}<br/>
 * Example:
 * <pre>
 * "123abc"  -> true
 * "123"     -> true
 * "  "      -> true
 * "  abc"      -> true
 * ".abc"      -> false
 * "@#$%^&*("      -> false
 * "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"  -> true
 * </pre>
 **/
public class RuleAlphaNumSpace extends AbstractRule {

    public RuleAlphaNumSpace(RuleInfo value) {
        super(value);
    }

    @Override
    public boolean isOkay(Form f) {
        Optional<String> s = f.getString(rule.getPath());
        return s.filter(StringUtils::isAlphanumericSpace).isPresent();
    }
}
