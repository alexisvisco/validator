package fr.maed.website.kwizzy.validation;

import fr.maed.website.kwizzy.validation.exception.RuleParseException;
import fr.maed.website.kwizzy.validation.impl.Form;
import fr.maed.website.kwizzy.validation.rules.Rule;
import fr.maed.website.kwizzy.validation.rules.RuleObj;
import fr.maed.website.kwizzy.validation.rules.Rules;
import fr.maed.website.kwizzy.validation.util.ConstructRule;
import fr.maed.website.kwizzy.validation.util.EnumsList;

import java.util.*;

public class Validator {

    private EnumsList enumsList = new EnumsList();
    private final Form form;
    private Map<String, Rule> rules = new HashMap<>();
    private Map<String, String> errors = new HashMap<>();

    public Validator(Form form) {
        this.form = form;
        enumsList.add(Arrays.asList(Rules.values()));
    }

    public void addEnumRules(List<RuleObj> list) {
        enumsList.add(list);
    }

    public void addEnumRules(RuleObj... list) {
        enumsList.add(Arrays.asList(list));
    }

    public Validator addRule(String path, String definer) throws RuleParseException {
        RuleInfo r = new RuleInfo(path, definer, enumsList);
        Optional<RuleObj> ruleObj = r.getRuleObj();
        if (!ruleObj.isPresent())
            throw new RuleParseException(definer, path);
        Optional<Rule> rule = ConstructRule.constructRule(ruleObj.get(), r);
        if (!rule.isPresent())
            throw new RuleParseException(path, definer);
        rules.put(path, rule.get());
        return this;
    }

    public Map<String, String> check() {
        rules.forEach((key, value) -> {
            if (!form.getString(key).isPresent()) {
                errors.put(key, key + " is undefined.");
                return ;
            }
            boolean okay = value.isOkay(form);
            RuleInfo ruleInfo = value.getRuleInfo();
            Optional<RuleObj> ruleObj = ruleInfo.getRuleObj();
            if (ruleObj.isPresent() && !okay)
                errors.put(key,
                        ruleObj.get().getDefaultMessage(ruleInfo));
        });
        return errors;
    }

    public void addCustomRule(String path, Rule rule) {
        rules.put(path, rule);
    }

}
