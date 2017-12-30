package fr.maed.website.kwizzy.validation.rules.list.js;

import fr.maed.website.kwizzy.validation.RuleInfo;
import fr.maed.website.kwizzy.validation.impl.Form;
import fr.maed.website.kwizzy.validation.rules.list.AbstractRule;
import fr.maed.website.kwizzy.validation.rules.DefaultRules;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Optional;

/**
 * Ref to {@link DefaultRules#JS_OBJ}<br/>
 * Example:
 * <pre>
 * "{}"                         -> true
 * "{"test": 1}"                -> true
 * "{"test": 1, "2": "hello"}"  -> true
 * "{"                          -> false
 * " "                          -> false
 * "undefined"                  -> false
 * </pre>
 **/
public class RuleJsObj extends AbstractRule {

    @Override
    public boolean isOkay(Form f) {
        Optional<String> s = f.getString(rule.getField());
        if (!s.isPresent())
            return false;
        try {
            new JSONObject(s.get());
        } catch (JSONException e)
        {
            return false;
        }
        return true;
    }
}
