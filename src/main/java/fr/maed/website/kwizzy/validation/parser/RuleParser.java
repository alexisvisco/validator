package fr.maed.website.kwizzy.validation.parser;

import fr.maed.website.kwizzy.validation.RuleInfo;
import fr.maed.website.kwizzy.validation.exceptions.RuleParseException;
import fr.maed.website.kwizzy.validation.rules.RuleObj;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static fr.maed.website.kwizzy.validation.config.ValidatorConfig.cfg;

public class RuleParser {
    public static List<RuleInfo> getRuleInfo(String field, String origin) throws RuleParseException {
        List<RuleInfo> lst = new ArrayList<>();
        List<RuleLexer.Token> lex = new RuleLexer(origin).lex();
        for (RuleLexer.Token tok : lex) {
            Optional<RuleObj> rule = cfg().ruleList.getRule(tok.getParams().size(), tok.getRuleName().toString());
            if (!rule.isPresent())
                throw new RuleParseException(origin, field);
            lst.add(new RuleInfo(field, rule.get(), tok));
        }
        return lst;
    }
}
