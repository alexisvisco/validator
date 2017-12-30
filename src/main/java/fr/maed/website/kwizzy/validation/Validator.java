package fr.maed.website.kwizzy.validation;

import fr.maed.website.kwizzy.validation.exceptions.LanguageNotFoundException;
import fr.maed.website.kwizzy.validation.exceptions.RuleParseException;
import fr.maed.website.kwizzy.validation.impl.Form;
import fr.maed.website.kwizzy.validation.parser.RuleLexer;
import fr.maed.website.kwizzy.validation.parser.RuleParser;
import fr.maed.website.kwizzy.validation.rules.list.Rule;
import fr.maed.website.kwizzy.validation.rules.RuleObj;
import fr.maed.website.kwizzy.validation.util.ConstructRule;

import java.util.*;

import static fr.maed.website.kwizzy.validation.config.ValidatorConfig.cfg;

public class Validator {


    private Form form;
    private Map<String, Rule> rules = new HashMap<>();
    private Map<String, String> errors = new HashMap<>();
    private String lang = "en";

    public Validator() { }

    public Validator(Form form) {
        this.form = form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    /**
     *
     * @param field where validator execute the rules
     * @param rules a rule syntax is: name_rule[:param...][|second_name_rule(:param...)]+ <br>
     * <pre>
     * samples of rules: <br>
     *    password -> confirm | min_length:5 <br>
     *    email    -> unique:user, email| email <br>
     *
     * - All rules are lexed by {@link RuleLexer#lex()} <br>
     * - All rules are transformed  by {@link RuleParser#getRuleInfo(String, String)}<br>
     *</pre>
     * @return this validator in order {@link Validator#addRule(String, String)}
     * @throws RuleParseException
     */
    public Validator addRule(String field, String rules) throws RuleParseException {
        List<RuleInfo> ruleInfos = RuleParser.getRuleInfo(field, rules);
        for (RuleInfo ruleInfo : ruleInfos) {
            Optional<Rule> rule = ConstructRule.constructRule(ruleInfo.getRuleObj());
            if (!rule.isPresent())
                throw new RuleParseException("Something wrong when construct Rule for rule name " + ruleInfo.getRuleName());
            rule.get().injectRuleInfo(ruleInfo);
            this.rules.put(field, rule.get());
        }
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
            Optional<String> messageFor = Optional.empty();
            try {
                messageFor = cfg().languageList.getByLanguage(lang).getMessageFor(ruleInfo.getRuleName(), ruleInfo);
            } catch (LanguageNotFoundException e) {
                e.printStackTrace();
                System.exit(1);
            }
            if (messageFor.isPresent() && !okay)
                errors.put(key, messageFor.get());
        });
        return errors;
    }

    public void addCustomRule(String path, Rule rule) {
        rules.put(path, rule);
    }

}
