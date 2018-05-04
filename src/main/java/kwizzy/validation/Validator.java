package kwizzy.validation;

import kwizzy.validation.annot.Rules;
import kwizzy.validation.exceptions.TransferFormException;
import kwizzy.validation.impl.RulesMessages;
import kwizzy.validation.exceptions.LanguageNotFoundException;
import kwizzy.validation.exceptions.RuleParseException;
import kwizzy.validation.impl.Form;
import kwizzy.validation.parser.RuleLexer;
import kwizzy.validation.parser.RuleParser;
import kwizzy.validation.impl.Rule;
import kwizzy.validation.util.ConstructRule;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

import static kwizzy.validation.config.ValidatorConfig.cfg;
import static org.reflections.ReflectionUtils.*;

public class Validator {


    private Form form;
    private Map<String, List<Rule>> rules = new HashMap<>();
    private Map<String, String> errors = new HashMap<>();
    private boolean hasValidate = false;
    private RulesMessages messages;

    public Validator() {
        setLang(cfg().defaultLang);
    }

    public Validator(Form form) {
        this();
        this.form = form;
    }

    public void setForm(Form form) {
        this.errors.clear();
        this.hasValidate = false;
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
     *                     a all :1 will be replaced by the first replacement.
     *                     If replacement is a $NUMBER, replacement will be executed after
     *                     the lexer.
     * @return this validator in order to chain {@link Validator#addRule(String, String, String...)}
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
            addRule(field, newRule, replacements);
        return this;
    }

    private void addRule(String field, String rules, String... replacements) throws RuleParseException {
        List<RuleInfo> ruleInfos = RuleParser.getRuleInfo(field, rules);
        for (RuleInfo ruleInfo : ruleInfos) {
            ruleInfo.replacer(replacements);
            Optional<Rule> rule = ConstructRule.constructRule(ruleInfo.getRuleObj());
            if (!rule.isPresent())
                throw new RuleParseException("Something wrong when construct Rule for rule name " + ruleInfo.getRuleName());
            rule.get().injectRuleInfo(ruleInfo);
            this.rules.computeIfAbsent(field, k -> new ArrayList<>());
            this.rules.get(field).add(rule.get());
        }
    }

    public Map<String, String> check() {
        hasValidate = true;
        rules.forEach((field, value) -> {
            boolean optional = value.stream().anyMatch(e -> e.getRuleInfo().isOptional());
            if ((!form.getString(field).isPresent() || form.getString(field).get().isEmpty()) && !optional) {
                errors.put(field, field + " est indéfinis.");
                return;
            }
            else if (!form.getString(field).isPresent() || form.getString(field).get().isEmpty() && optional)
                return;
            for (Rule rule : value) {
                if (!rule.isOkay(form)) {
                    addError(field, rule.getRuleInfo());
                    break;
                }
            }
        });
        return getErrors();
    }

    public <T> Optional<T> get(Class<T> cl) throws TransferFormException {
        if (!hasValidate)
            check();
        if (errors.isEmpty())
            return Optional.ofNullable(new ObjectParser<>(cl, form, this).get());
        else
            return Optional.empty();
    }

    public static Validator validatorFromClass(Class<?> t)
    {
        Validator validator = new Validator();
        getFields(t, withAnnotation(Rules.class)).forEach(e -> {
            Rules rule = e.getAnnotation(Rules.class);
            try {
                validator.addRule(e.getName() + " -> " + rule.value(), rule.replacements());
            } catch (RuleParseException ex) {
                ex.printStackTrace();
            }
        });
        return validator;
    }

    private void addError(String field, RuleInfo ruleInfo) {
        Optional<String> msg = messages.getMessageFor(ruleInfo.getRuleName(), ruleInfo);
        msg.ifPresent(s -> errors.put(field, s));
    }

    public Form getForm() {
        return form;
    }

    public Map<String, List<Rule>> getRules() {
        return rules;
    }

    public void setRules(Map<String, List<Rule>> rules) {
        this.rules = rules;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public RulesMessages getMessages() {
        return messages;
    }

    public void setMessages(RulesMessages messages) {
        this.messages = messages;
    }
}
