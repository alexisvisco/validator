package fr.maed.website.kwizzy.validation.config;

import fr.maed.website.kwizzy.validation.rules.RuleObj;
import fr.maed.website.kwizzy.validation.util.RuleList;

import java.util.List;
import java.util.stream.Collectors;

public class ValidatorConfig {


    private static ValidatorConfig cfg;

    public RuleList ruleList = new RuleList();

    private ValidatorConfig() { }

    public List<String> getAllRulesNames()
    {
        return ruleList.stream().map(RuleObj::getRuleName).collect(Collectors.toList());
    }

    public static ValidatorConfig cfg() {
        if (cfg == null)
            cfg = new ValidatorConfig();
        return cfg;
    }
}
