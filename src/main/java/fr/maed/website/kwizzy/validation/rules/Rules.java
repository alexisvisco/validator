package fr.maed.website.kwizzy.validation.rules;

import fr.maed.website.kwizzy.validation.RuleInfo;
import fr.maed.website.kwizzy.validation.rules.list.other.RuleBool;
import fr.maed.website.kwizzy.validation.rules.list.other.RuleDiff;
import fr.maed.website.kwizzy.validation.rules.list.other.RuleMax;
import fr.maed.website.kwizzy.validation.rules.list.other.RuleMin;
import fr.maed.website.kwizzy.validation.rules.list.number.*;
import fr.maed.website.kwizzy.validation.rules.list.str.*;

public enum Rules implements RuleObj {

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

    /// OTHER RULES
    MAX(RuleMax.class, "max", 1, ":attr must be lesser than :1 length."),
    MIN(RuleMin.class, "min", 1, ":attr must be at least of :1 length."),
    DIFF(RuleDiff.class, "diff", 1, ":attr is same as :1."),
    BOOL(RuleBool.class, "bool", 0, ":attr is not a boolean (0, 1, true, false)."),
    ;

    private Class<? extends AbstractRule> rule;
    private String defaultMessage;
    private String definer;
    private int paramsCount;

    Rules(Class<? extends AbstractRule> rule, String definer, int paramsCount, String defaultMessage) {
        this.rule = rule;
        this.defaultMessage = defaultMessage;
        this.paramsCount = paramsCount;
        this.definer = definer;
    }


    @Override
    public Class<? extends AbstractRule> getRule() {
        return rule;
    }

    @Override
    public String getDefaultMessage(RuleInfo r) {
        String s = this.defaultMessage.replace(":attr", r.getPath());
        for (int i = 0; i < r.getParamsCount(); i++)
            s = s.replace(":" + (i+1), s);
        return s;
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
        return "Rules{" +
                "rule=" + rule +
                ", defaultMessage='" + defaultMessage + '\'' +
                ", definer='" + definer + '\'' +
                ", paramsCount=" + paramsCount +
                '}';
    }
}
