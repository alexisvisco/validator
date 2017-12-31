package kwizzy.validation.parser;

import kwizzy.validation.RuleInfo;
import kwizzy.validation.exceptions.RuleParseException;
import kwizzy.validation.rules.RuleDescriptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static kwizzy.validation.config.ValidatorConfig.cfg;

public class RuleParser {
    public static List<RuleInfo> getRuleInfo(String field, String origin) throws RuleParseException {
        List<RuleInfo> lst = new ArrayList<>();
        List<RuleLexer.Token> lex = new RuleLexer(origin).lex();
        for (RuleLexer.Token tok : lex) {
            Optional<RuleDescriptor> rule = cfg().ruleList.getRule(tok.getParams().size(), tok.getRuleName().toString());
            if (!rule.isPresent())
                throw new RuleParseException(origin, field);
            lst.add(new RuleInfo(field, rule.get(), tok));
        }
        return lst;
    }
}
