package kwizzy.validation.util;

import kwizzy.validation.rules.DefaultRules;
import kwizzy.validation.rules.RuleObj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class RuleList extends ArrayList<RuleObj> {

    public RuleList() {
        addAll(Arrays.asList(DefaultRules.values()));
    }

    public Optional<RuleObj> getRule(Integer paramCount, String ruleName) {
        return this.stream()
                .filter(e -> (paramCount == e.getParamsCount() && e.getRuleName().equals(ruleName)))
        .findFirst();
    }
}
