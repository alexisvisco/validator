package fr.maed.website.kwizzy.validation.util;

import fr.maed.website.kwizzy.validation.RuleInfo;
import fr.maed.website.kwizzy.validation.rules.Rule;
import fr.maed.website.kwizzy.validation.rules.RuleObj;

import java.util.Optional;

public class ConstructRule {

    public static Optional<Rule> constructRule(RuleObj rule, RuleInfo supposedRule) {
        try {
            return Optional.of(rule.getRule()
                    .getConstructor(RuleInfo.class)
                    .newInstance(supposedRule));
        } catch (ReflectiveOperationException e)
        {
            return Optional.empty();
        }
    }
}
