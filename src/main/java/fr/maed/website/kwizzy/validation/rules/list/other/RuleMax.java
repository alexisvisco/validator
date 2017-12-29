package fr.maed.website.kwizzy.validation.rules.list.other;

import fr.maed.website.kwizzy.validation.RuleInfo;
import fr.maed.website.kwizzy.validation.impl.Form;
import fr.maed.website.kwizzy.validation.rules.AbstractRule;
import fr.maed.website.kwizzy.validation.rules.Rules;

import java.util.Optional;

/**
 * Ref to {@link Rules#MAX}<br/>
 * Example:
 * <pre>
 * "123" 3    -> true
 * "ab"  3    -> true
 * ""  3      -> true
 * "abcd" 3   -> false
 * </pre>
 **/
public class RuleMax extends AbstractRule {

    public RuleMax(RuleInfo value) {
        super(value);
    }

    @Override
    public boolean isOkay(Form f) {
        String param = getRuleInfo().getParams()[0];
        try {
            int max = Integer.parseInt(param);
            Optional<String> s = f.getString(rule.getPath());
            return s.filter(str -> str.length() <= max).isPresent();
        } catch (Exception e)
        {
            return false;
        }
    }
}
