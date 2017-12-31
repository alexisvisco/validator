package kwizzy.validation.config.language;

import kwizzy.validation.RuleInfo;
import kwizzy.validation.exceptions.LanguageNotFoundException;
import kwizzy.validation.impl.RulesMessages;
import kwizzy.validation.rules.DefaultRules;
import kwizzy.validation.impl.RuleDescriptor;

import java.util.Optional;
import java.util.stream.Stream;

import static kwizzy.validation.config.ValidatorConfig.*;

class RMessages__LANG__ implements RulesMessages {

    @Override
    public String getLang() {
        return "__LANG_MIN__";
    }

    @Override
    public Optional<String> getMessageFor(String ruleName, RuleInfo r) {
        Optional<ListRules__LANG__> o = ListRules__LANG__.getRuleByName(ruleName);
        if (!o.isPresent()) {
            try {
                return cfg().languageList.getByLanguage("en").getMessageFor(ruleName, r);
            } catch (LanguageNotFoundException e) {
                e.printStackTrace();
            }
            return Optional.empty();
        }
        return Optional.ofNullable(replaceInfos(o.get().message, r));
    }

    @Override
    public void setMessageFor(String ruleName, String msg) {
        ListRules__LANG__.getRuleByName(ruleName).ifPresent(e -> e.message = msg);
    }

    public static Optional<RuleDescriptor> getByRuleName(String ruleName) {
        return Stream.of(DefaultRules.values())
                .filter(e -> e.getRuleName().equals(ruleName))
                .map(e -> (RuleDescriptor)e).findFirst();
    }

    private enum ListRules__LANG__ {

        __ENUM_HERE__
        ;
        String message;
        String ruleName;
        int params;

        ListRules__LANG__() {}

        ListRules__LANG__(String message, int params, String ruleName) {
            this.message = message;
            this.ruleName = ruleName;
            this.params = params;
        }

        private static Optional<ListRules__LANG__> getRuleByName(String rn) {
            return Stream.of(ListRules__LANG__.values())
                    .filter(e -> e.ruleName.equals(rn))
                    .findFirst();
        }
    }
}
