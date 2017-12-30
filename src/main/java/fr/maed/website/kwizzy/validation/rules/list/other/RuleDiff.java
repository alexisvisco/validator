package fr.maed.website.kwizzy.validation.rules.list.other;

import fr.maed.website.kwizzy.validation.RuleInfo;
import fr.maed.website.kwizzy.validation.impl.Form;
import fr.maed.website.kwizzy.validation.rules.list.AbstractRule;
import fr.maed.website.kwizzy.validation.rules.DefaultRules;

import java.util.Optional;

/**
 * Ref to {@link DefaultRules#DIFF}<br/>
 * Example:
 * <pre>
 * "123" "123"    -> false
 * "123" "456"    -> true
 * "abc" "abc"    -> false
 * "123" "456"    -> true
 * </pre>
 **/
public class RuleDiff extends AbstractRule {

    @Override
    public boolean isOkay(Form f) {
        String param = getRuleInfo().getParams().get(0);
        Optional<String> s = f.getString(rule.getField());
        return s.filter(str -> !str.equals(param)).isPresent();
    }
}
