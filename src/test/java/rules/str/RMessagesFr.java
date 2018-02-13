package rules.str;

import kwizzy.validation.RuleInfo;
import kwizzy.validation.exceptions.LanguageNotFoundException;
import kwizzy.validation.impl.RulesMessages;
import kwizzy.validation.rules.DefaultRules;
import kwizzy.validation.impl.RuleDescriptor;

import java.util.Optional;
import java.util.stream.Stream;

import static kwizzy.validation.config.ValidatorConfig.*;

class RMessagesFr implements RulesMessages {

    @Override
    public String getLang() {
        return "fr";
    }

    @Override
    public Optional<String> getMessageFor(String ruleName, RuleInfo r) {
        System.out.println("HEllo world");
        Optional<ListRulesFr> o = ListRulesFr.getRuleByName(ruleName);
        System.out.println(o.isPresent());
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
        ListRulesFr.getRuleByName(ruleName).ifPresent(e -> e.message = msg);
    }

    public static Optional<RuleDescriptor> getByRuleName(String ruleName) {
        return Stream.of(DefaultRules.values())
                .filter(e -> e.getRuleName().equals(ruleName))
                .map(e -> (RuleDescriptor) e).findFirst();
    }

    private enum ListRulesFr {

        ALPHA("alpha", 0, ":attr is not a alpha string."),
        EMAIL("email", 0, ":attr is not an email."),
        URL("url", 0, ":attr is not an url."),
        ALPHA_NUM("alpha_num", 0, ":attr is not alpha numeric."),
        ALPHA_NUM_SPACE("alpha_num_space", 0, ":attr is not alpha numeric or space."),
        NOT_EMPTY("not_empty", 0, ":attr is an empty string."),
        BLANK("blank", 0, ":attr is not a blank string."),
        NUMERIC("numeric", 0, ":attr is not a numeric string."),
        INT("int", 0, ":attr is not a integer."),
        DOUBLE("double", 0, ":attr is not a double."),
        FLOAT("float", 0, ":attr is not a float."),
        LONG("long", 0, ":attr is not a long."),
        LESSER("lesser", 1, ":attr must be lesser than :1."),
        HIGHER("higher", 1, ":attr must be higher than :1."),
        RANGE("range", 2, ":attr must be between :1 and :2."),
        CONFIRM("confirm", 0, ":attr not match with confirmation field."),
        JSON_ARR("json_arr", 0, ":attr is not a json array."),
        JSON("json", 0, ":attr is not a json object."),
        MAX_LENGTH("max_length", 1, ":attr must be lesser than :1 length."),
        MIN_LENGTH("min_length", 1, ":attr must be at least of :1 length."),
        DIFF("diff", 1, ":attr is same as :1."),
        BOOL("bool", 0, ":attr is not a boolean (0, 1, true, false)."),
        OPTIONAL("optional", -1, ":attr is optional."),
        UNIQUE("unique", 2, ":attr already exist."),
        REGEX("regex", 1, ":attr not match regex: :1."),
        CONTAIN("contain", 1, ":attr ne contient pas :1."),;
        String message;
        String ruleName;
        int params;

        ListRulesFr() {
        }

        ListRulesFr(String ruleName, int params, String message) {
            this.message = message;
            this.ruleName = ruleName;
            this.params = params;
        }

        private static Optional<ListRulesFr> getRuleByName(String rn) {
            return Stream.of(ListRulesFr.values())
                    .filter(e -> {

                        System.out.println(e.ruleName + " == " + rn);
                        return e.ruleName.equals(rn);
                    })
                    .findFirst();
        }
    }
}