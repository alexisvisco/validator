package kwizzy.validation.rules.list.js;

import kwizzy.validation.impl.Form;
import kwizzy.validation.rules.list.AbstractRule;
import kwizzy.validation.rules.DefaultRules;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Optional;

/**
 * Ref to {@link DefaultRules#JS_OBJ}<br/>
 * Usage: addRule("field -> json") <br/>
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
