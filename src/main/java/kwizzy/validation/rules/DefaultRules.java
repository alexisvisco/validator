package kwizzy.validation.rules;

import kwizzy.validation.impl.RuleDescriptor;
import kwizzy.validation.impl.Rule;
import kwizzy.validation.rules.list.js.RuleJsArray;
import kwizzy.validation.rules.list.js.RuleJsObj;
import kwizzy.validation.rules.list.other.*;
import kwizzy.validation.rules.list.number.*;
import kwizzy.validation.rules.list.str.*;

import java.util.Optional;
import java.util.stream.Stream;

public enum DefaultRules implements RuleDescriptor {

    /// STRING RULES
    ALPHA(RuleAlpha.class, "alpha", 0, ":attr is not a alpha string."),
    EMAIL(RuleEmail.class, "email", 0, ":attr is not an email."),
    URL(RuleUrl.class, "url", 0, ":attr is not an url."),
    ALPHA_NUM(RuleAlphaNum.class, "alpha_num", 0, ":attr is not alpha numeric."),
    ALPHA_NUM_SPACE(RuleAlphaNumSpace.class, "alpha_num_space", 0, ":attr is not alpha numeric or space."),
    NOT_EMPTY(RuleNotEmpty.class, "not_empty", 0, ":attr is an empty string."),
    BLANK(RuleBlank.class, "blank", 0, ":attr is not a blank string."),
    NUMERIC(RuleNumeric.class, "numeric", 0, ":attr is not a numeric string."),

    /// NUMBER RULES
    INT(RuleInt.class, "int", 0, ":attr is not a integer."),
    DOUBLE(RuleDouble.class, "double", 0, ":attr is not a double."),
    FLOAT(RuleFloat.class, "float", 0, ":attr is not a float."),
    LONG(RuleLong.class, "long", 0, ":attr is not a long."),
    LESSER(RuleLesser.class, "lesser", 1, ":attr must be lesser than :1."),
    HIGHER(RuleHigher.class, "higher", 1, ":attr must be higher than :1."),
    RANGE(RuleRange.class, "range", 2, ":attr must be between :1 and :2."),
    CONFIRM(RuleConfirm.class, "confirm", 0, ":attr not match with confirmation field."),

    /// JSON RULES
    JS_ARRAY(RuleJsArray.class, "json_arr", 0, ":attr is not a json array."),
    JS_OBJ(RuleJsObj.class, "json", 0, ":attr is not a json object."),

    /// OTHER RULES
    MAX(RuleMax.class, "max_length", 1, ":attr must be lesser than :1 length."),
    MIN(RuleMin.class, "min_length", 1, ":attr must be at least of :1 length."),
    DIFF(RuleDiff.class, "diff", 1, ":attr is same as :1."),
    BOOL(RuleBool.class, "bool", 0, ":attr is not a boolean (0, 1, true, false)."),
    OPTIONAL(RuleOptional.class, "optional", -1, ":attr is optional."),
    UNIQUE(RuleUnique.class, "unique", 2, ":attr already exist."),

    ;

    private Class<? extends Rule> rule;
    private String defaultMessage;
    private String definer;
    private int paramsCount;

    DefaultRules(Class<? extends Rule> rule, String definer, int paramsCount, String defaultMessage) {
        this.rule = rule;
        this.defaultMessage = defaultMessage;
        this.paramsCount = paramsCount;
        this.definer = definer;
    }


    @Override
    public Class<? extends Rule> getRule() {
        return rule;
    }

    @Override
    public String getDefaultMessage() {
        return defaultMessage;
    }

    @Override
    public void setDefaultMessage(String ruleName, String message) {
        this.defaultMessage = message;
    }


    @Override
    public String getRuleName() {
        return definer;
    }

    @Override
    public int getParamsCount() {
        return paramsCount;
    }

    @Override
    public String toString() {
        return "DefaultRules{" +
                "rule=" + rule +
                ", defaultMessage='" + defaultMessage + '\'' +
                ", definer='" + definer + '\'' +
                ", paramsCount=" + paramsCount +
                '}';
    }

    public static Optional<RuleDescriptor> getByRuleName(String ruleName) {
        return Stream.of(DefaultRules.values())
                .filter(e -> e.getRuleName().equals(ruleName))
                .map(e -> (RuleDescriptor)e).findFirst();
    }
}