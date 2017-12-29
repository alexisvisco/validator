package fr.maed.website.kwizzy.validation.util;

import fr.maed.website.kwizzy.validation.rules.RuleObj;
import fr.maed.website.kwizzy.validation.RuleInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnumsList extends ArrayList<List<RuleObj>> {

    public Optional<RuleObj> getRule(RuleInfo s) {
        return this.stream()
                .flatMap(List::stream)
                .filter(e -> (s.getParamsCount() == e.getParamsCount() && e.getRuleName().equals(s.getRuleName())))
        .findFirst();
    }
}
