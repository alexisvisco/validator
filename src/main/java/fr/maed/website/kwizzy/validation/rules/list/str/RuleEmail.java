package fr.maed.website.kwizzy.validation.rules.list.str;

import fr.maed.website.kwizzy.validation.RuleInfo;
import fr.maed.website.kwizzy.validation.impl.Form;
import fr.maed.website.kwizzy.validation.rules.list.AbstractRule;
import fr.maed.website.kwizzy.validation.rules.DefaultRules;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * Ref to {@link DefaultRules#EMAIL}<br/>
 * Example:
 * <pre>
 * "test@"             -> false
 * "test@gmail.com"    -> true
 * "test@gmail.com."   -> false
 * "--@fejwn.com"      -> false
 * "..@fejwn.com"      -> false
 * </pre>
 **/
public class RuleEmail extends AbstractRule {

    @Override
    public boolean isOkay(Form f) {
        Optional<String> s = f.getString(rule.getField());
        return s.filter(this::isValidEmailAddress).isPresent();
    }

    private boolean isValidEmailAddress(String email) {

        boolean stricterFilter = true;
        String stricterFilterString = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
        String laxString = ".+@.+\\.[A-Za-z]{2}[A-Za-z]*";
        String emailRegex = stricterFilter ? stricterFilterString : laxString;
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(emailRegex);
        java.util.regex.Matcher m = p.matcher(email);
        if(!m.matches())
            return false;
        String[] split = email.split("@");
        if (StringUtils.containsOnly(split[0], ".") || StringUtils.containsOnly(split[0], "-"))
            return false;
        return true;
    }
}
