package fr.maed.website.kwizzy.validation.rules.list.other;

import fr.maed.website.kwizzy.validation.RuleInfo;
import fr.maed.website.kwizzy.validation.impl.Form;
import fr.maed.website.kwizzy.validation.rules.AbstractRule;
import fr.maed.website.kwizzy.validation.rules.Rules;

import java.util.Optional;

/**
 * Ref to {@link Rules#DIFF}<br/>
 * Example:
 * <pre>
 * "123" "123"    -> false
 * "123" "456"    -> true
 * "abc" "abc"    -> false
 * "123" "456"    -> true
 * </pre>
 **/
public class RuleDiff extends AbstractRule {

    public RuleDiff(RuleInfo value) {
        super(value);
    }

    @Override
    public boolean isOkay(Form f) {
        String param = getRuleInfo().getParams()[0];
        Optional<String> s = f.getString(rule.getPath());
        return s.filter(str -> !str.equals(param)).isPresent();
    }
}
