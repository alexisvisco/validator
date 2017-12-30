package fr.maed.website.kwizzy.validation.rules.list.str;

import fr.maed.website.kwizzy.validation.RuleInfo;
import fr.maed.website.kwizzy.validation.impl.Form;
import fr.maed.website.kwizzy.validation.rules.list.AbstractRule;
import fr.maed.website.kwizzy.validation.rules.DefaultRules;

import java.util.Optional;

/**
 * Ref to {@link DefaultRules#ALPHA}<br/>
 * Example:
 * <pre>
 * ""        -> false
 * " "       -> false
 * "     "   -> false
 * "    b "  -> true
 * </pre>
 **/
public class RuleConfirm extends AbstractRule {

    @Override
    public boolean isOkay(Form f) {
        Optional<String> s = f.getString(rule.getField());
        Optional<String> confirm = f.getString(rule.getField() + "_confirm");
        if (!(s.isPresent() && confirm.isPresent()))
            return false;
        return s.get().equals(confirm.get());
    }
}
