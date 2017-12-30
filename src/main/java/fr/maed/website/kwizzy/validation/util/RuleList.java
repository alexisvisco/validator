package fr.maed.website.kwizzy.validation.util;

import fr.maed.website.kwizzy.validation.rules.DefaultRules;
import fr.maed.website.kwizzy.validation.rules.RuleObj;
import fr.maed.website.kwizzy.validation.RuleInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
