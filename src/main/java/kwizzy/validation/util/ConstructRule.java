package kwizzy.validation.util;

import kwizzy.validation.rules.list.Rule;
import kwizzy.validation.rules.RuleDescriptor;

import java.util.Optional;

public class ConstructRule {

    public static Optional<Rule> constructRule(RuleDescriptor rule) {
        try {
            return Optional.of(rule.getRule()
                    .getConstructor()
                    .newInstance());
        } catch (ReflectiveOperationException e) {
            return Optional.empty();
        }
    }
}
