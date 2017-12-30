package fr.maed.website.kwizzy.validation.rules.list.js;

import fr.maed.website.kwizzy.validation.RuleInfo;
import fr.maed.website.kwizzy.validation.impl.Form;
import fr.maed.website.kwizzy.validation.rules.list.AbstractRule;
import fr.maed.website.kwizzy.validation.rules.DefaultRules;
import org.json.JSONArray;

import java.util.Optional;

/**
 * Ref to {@link DefaultRules#JS_OBJ}<br/>
 * Example:
 * <pre>
 * "[]"         -> true
 * "[1, 2, 3]"  -> true
 * "["", "1"]"  -> true
 * "null"       -> false
 * "1, 2, 3"    -> false
 * </pre>
 **/
public class RuleJsArray extends AbstractRule {

    @Override
    public boolean isOkay(Form f) {
        Optional<String> s = f.getString(rule.getField());
        if (!s.isPresent())
            return false;
        try {
            new JSONArray(s.get());
            return true;
        } catch (Exception e)
        {
            return false;
        }
    }
}
