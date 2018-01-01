package kwizzy.validation;

import kwizzy.validation.impl.RulesMessages;
import kwizzy.validation.exceptions.LanguageNotFoundException;
import kwizzy.validation.exceptions.RuleParseException;
import kwizzy.validation.impl.Form;
import kwizzy.validation.parser.RuleLexer;
import kwizzy.validation.parser.RuleParser;
import kwizzy.validation.impl.RuleDescriptor;
import kwizzy.validation.impl.Rule;
import kwizzy.validation.util.ConstructRule;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

import static kwizzy.validation.config.ValidatorConfig.cfg;

public class Validator {


    private Form form;
    private Map<String, List<Rule>> rules = new HashMap<>();
    private Map<String, String> errors = new HashMap<>();
    private RulesMessages messages;

    public Validator() {
        setLang(cfg().defaultLang);
    }

    public Validator(Form form) {
        this();
        this.form = form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public void setLang(String lang) {
        try {
            messages = cfg().languageList.getByLanguage(lang);
        } catch (LanguageNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param rule a rule syntax is: field... -> name_rule[:param...][|second_name_rule[:param...]]+ <br>
     *             <pre>
     *             samples of rules: <br>
     *                password -> confirm | min_length:5 <br>
     *                email    -> unique:user, email| email <br>
     *
     *             - All rules are lexed by {@link RuleLexer#lex()} <br>
     *             - All rules are transformed  by {@link RuleParser#getRuleInfo(String, String)}<br>
     *             </pre>
     * @param replacements is optional. if a rule contain array_contain:(:1) and replacement
     *                    a all :1 will be replaced by the first replacement.
     * @return this validator in order to chain {@link Validator#addRule(String, String)}
     * @throws RuleParseException
     */
    public Validator addRule(String rule, String... replacements) throws RuleParseException {
        for (int i = 0; i < replacements.length; i++)
            rule = rule.replace(":" + (i+1), replacements[i]);
        String[] exploded = rule.split(" -> ");
        if (exploded.length !=  2)
            throw new RuleParseException("Syntax is fields... -> rules...");
        List<String> fields = Arrays.stream(exploded[0].split(","))
                .map(StringUtils::deleteWhitespace)
                .collect(Collectors.toList());
        String newRule = exploded[1];

        for (String field : fields)
            addRule(field, newRule);
        return this;
    }

    private void addRule(String field, String rules) throws RuleParseException {
        List<RuleInfo> ruleInfos = RuleParser.getRuleInfo(field, rules);
        for (RuleInfo ruleInfo : ruleInfos) {
            Optional<Rule> rule = ConstructRule.constructRule(ruleInfo.getRuleObj());
            if (!rule.isPresent())
                throw new RuleParseException("Something wrong when construct Rule for rule name " + ruleInfo.getRuleName());
            rule.get().injectRuleInfo(ruleInfo);
            this.rules.computeIfAbsent(field, k -> new ArrayList<>());
            this.rules.get(field).add(rule.get());
        }
    }

    public void addCustomRule(String field, RuleDescriptor rule, String... params) throws RuleParseException {
        RuleInfo ruleInfo = new RuleInfo(field, rule, params);
        Optional<Rule> ruleC = ConstructRule.constructRule(ruleInfo.getRuleObj());
        if (!ruleC.isPresent())
            throw new RuleParseException("Something wrong when construct Rule for rule name " + ruleInfo.getRuleName());
        ruleC.get().injectRuleInfo(ruleInfo);
        this.rules.computeIfAbsent(field, k -> new ArrayList<>());
        this.rules.get(field).add(ruleC.get());
    }

    public Map<String, String> check() {
        rules.forEach((field, value) -> {
            boolean optional = value.stream().anyMatch(e -> e.getRuleInfo().isOptional());
            if (!form.getString(field).isPresent() && !optional) {
                errors.put(field, field + " is undefined.");
                return;
            }
            value.stream()
                    .filter(rule -> !rule.isOkay(form)).map(Rule::getRuleInfo)
                    .findFirst().ifPresent(rule -> addError(field, rule));
        });
        return errors;
    }

    private void addError(String field, RuleInfo ruleInfo) {
        Optional<String> msg = messages.getMessageFor(ruleInfo.getRuleName(), ruleInfo);
        msg.ifPresent(s -> errors.put(field, s));
    }
}
