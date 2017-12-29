package fr.maed.website.kwizzy.validation.rules.list.str;

import fr.maed.website.kwizzy.validation.RuleInfo;
import fr.maed.website.kwizzy.validation.impl.Form;
import fr.maed.website.kwizzy.validation.rules.AbstractRule;
import fr.maed.website.kwizzy.validation.rules.Rules;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;

import java.util.Optional;

/**
 * Ref to {@link Rules#URL}<br/>
 * Example:
 * <pre>
 * "http://foo.com/blah_blah"   -> true
 * "http://foo.com/blah_blah/"  -> true
 * "https://www.example.com/foo/?bar=baz&inga=42&quux"  -> true
 * "http://142.42.1.1/"  -> true
 * "http://j.mp"  -> true
 * "http://"  -> false
 * "http://##/"  -> false
 * "http://foo.bar?q=Spaces should be encoded"  -> false
 * "http:// shouldfail.com"  -> false
 * </pre>
 **/
public class RuleUrl extends AbstractRule {

    public RuleUrl(RuleInfo value) {
        super(value);
    }

    @Override
    public boolean isOkay(Form f) {
        Optional<String> s = f.getString(rule.getPath());
        return s.filter(str -> UrlValidator.getInstance().isValid(str)).isPresent();
    }
}
