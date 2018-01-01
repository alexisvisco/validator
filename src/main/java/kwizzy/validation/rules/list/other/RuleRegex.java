package kwizzy.validation.rules.list.other;

import kwizzy.validation.impl.Form;
import kwizzy.validation.rules.DefaultRules;
import kwizzy.validation.rules.list.AbstractRule;

import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Ref to {@link DefaultRules#REGEX}<br/>
 * Usage: addRule("field -> regex: string value") <br/>
 * Return true if the field match the regex pass in parameter.
 **/
public class RuleRegex extends AbstractRule {

    @Override
    public boolean isOkay(Form f) {
        if (getRuleInfo().getParamsCount() != 1)
            return false;
        String regex = getRuleInfo().getParams().get(0);
        Pattern compile = Pattern.compile(regex);
        Optional<String> s = f.getString(rule.getField());
        return s.map(s1 -> compile.matcher(s1).find()).orElse(false);
    }
}
