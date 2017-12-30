package fr.maed.website.kwizzy.validation.rules.list.other;

import fr.maed.website.kwizzy.validation.RuleInfo;
import fr.maed.website.kwizzy.validation.impl.Form;
import fr.maed.website.kwizzy.validation.rules.list.AbstractRule;
import fr.maed.website.kwizzy.validation.rules.DefaultRules;

import java.util.Optional;

/**
 * Ref to {@link DefaultRules#MIN}<br/>
 * Example:
 * <pre>
 * "123"  3    -> true
 * "ab"   3    -> false
 * ""     0    -> true
 * "abcd" 3    -> true
 * </pre>
 **/
public class RuleMin extends AbstractRule {

    @Override
    public boolean isOkay(Form f) {
        String param = getRuleInfo().getParams().get(0);
        try {
            int min = Integer.parseInt(param);
            Optional<String> s = f.getString(rule.getField());
            return s.filter(str -> str.length() >= min).isPresent();
        } catch (Exception e)
        {
            return false;
        }
    }
}
