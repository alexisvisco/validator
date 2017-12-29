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
 * "true"   -> true
 * "false"  -> true
 * "0"      -> true
 * "1"      -> true
 * "yes"    -> false
 * "no"     -> true
 * "abc"    -> true
 * </pre>
 **/
public class RuleBool extends AbstractRule {

    public RuleBool(RuleInfo value) {
        super(value);
    }

    @Override
    public boolean isOkay(Form f) {
        Optional<Boolean> s = f.getBool(rule.getPath());
        return s.isPresent();
    }
}
