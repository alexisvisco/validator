package kwizzy.validation.config;

import kwizzy.validation.config.language.LanguageList;
import kwizzy.validation.rules.RuleDescriptor;
import kwizzy.validation.util.RuleList;

import java.util.List;
import java.util.stream.Collectors;

public class ValidatorConfig {


    private static ValidatorConfig cfg;

    public RuleList ruleList = new RuleList();
    public LanguageList languageList = new LanguageList();
    public String defaultLang = "en";

    private ValidatorConfig() { }

    public List<String> getAllRulesNames()
    {
        return ruleList.stream().map(RuleDescriptor::getRuleName).collect(Collectors.toList());
    }

    public static ValidatorConfig cfg() {
        if (cfg == null)
            cfg = new ValidatorConfig();
        return cfg;
    }
}
