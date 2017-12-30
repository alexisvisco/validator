package kwizzy.validation.config.language;

import kwizzy.validation.RuleInfo;
import kwizzy.validation.exceptions.LanguageNotFoundException;
import kwizzy.validation.rules.DefaultRules;
import kwizzy.validation.rules.RuleObj;
import kwizzy.validation.config.ValidatorConfig;

import java.util.Optional;
import java.util.stream.Stream;

class RM__LANG__ implements RulesMessages {

    @Override
    public String getLang() {
        return "__LANG_MIN__";
    }

    @Override
    public Optional<String> getMessageFor(String ruleName, RuleInfo r) {
        Optional<ListRules__LANG__> o = ListRules__LANG__.getRuleByName(ruleName);
        if (!o.isPresent()) {
            try {
                return ValidatorConfig.cfg().languageList.getByLanguage("en").getMessageFor(ruleName, r);
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

    public static Optional<RuleObj> getByRuleName(String ruleName) {
        return Stream.of(DefaultRules.values())
                .filter(e -> e.getRuleName().equals(ruleName))
                .map(e -> (RuleObj)e).findFirst();
    }

    private enum ListRules__LANG__ {
        // __NAME_ENUM_RULE__("__RULE_NAME__", "__DEFAULT_MESSAGE"),
        __ENUM_HERE__
        ;
        String message;
        String ruleName;

        ListRules__LANG__() {}

        ListRules__LANG__(String message, String ruleName) {
            this.message = message;
            this.ruleName = ruleName;
        }

        private static Optional<ListRules__LANG__> getRuleByName(String rn) {
            return Stream.of(ListRules__LANG__.values())
                    .filter(e -> e.ruleName.equals(rn))
                    .findFirst();
        }
    }
}
