package impl;

import kwizzy.validation.impl.Form;
import kwizzy.validation.rules.list.AbstractRule;

import java.util.Optional;

public class RuleContain extends AbstractRule {
    @Override
    public boolean isOkay(Form f) {
        Optional<String> s = f.getString(rule.getField());
        String contain = rule.getParams().get(0);
        return s.filter(str -> str.contains(contain)).isPresent();
    }
}