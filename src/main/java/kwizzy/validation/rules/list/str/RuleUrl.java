package kwizzy.validation.rules.list.str;

import kwizzy.validation.impl.Form;
import kwizzy.validation.rules.list.AbstractRule;
import kwizzy.validation.rules.DefaultRules;
import org.apache.commons.validator.routines.UrlValidator;

import java.util.Optional;

/**
 * Ref to {@link DefaultRules#URL}<br/>
 * Usage: addRule("field -> url") <br/>
 * Example:
 * <pre>
 * "http://foo.com/blah_blah"                           -> true
 * "http://foo.com/blah_blah/"                          -> true
 * "https://www.example.com/foo/?bar=baz&inga=42&quux"  -> true
 * "http://142.42.1.1/"                                 -> true
 * "http://j.mp"                                        -> true
 * "http://"                                            -> false
 * "http://##/"                                         -> false
 * "http://foo.bar?q=Spaces should be encoded"          -> false
 * "http:// shouldfail.com"                             -> false
 * </pre>
 **/
public class RuleUrl extends AbstractRule {

    @Override
    public boolean isOkay(Form f) {
        Optional<String> s = f.getString(rule.getField());
        return s.filter(str -> UrlValidator.getInstance().isValid(str)).isPresent();
    }
}
