package fr.maed.website.kwizzy.validation.util;

import fr.maed.website.kwizzy.validation.rules.list.Rule;
import fr.maed.website.kwizzy.validation.rules.RuleObj;

import java.util.Optional;

public class ConstructRule {

    public static Optional<Rule> constructRule(RuleObj rule) {
        try {
            return Optional.of(rule.getRule()
                    .getConstructor()
                    .newInstance());
        } catch (ReflectiveOperationException e) {
            return Optional.empty();
        }
    }
}
