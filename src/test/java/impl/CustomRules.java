package impl;

import kwizzy.validation.impl.Rule;
import kwizzy.validation.impl.RuleDescriptor;
import kwizzy.validation.rules.DefaultRules;

import java.util.Optional;
import java.util.stream.Stream;

public enum CustomRules implements RuleDescriptor {

    CONTAIN(RuleContain.class, "contain", 1, ":attr does not contain :1."),;

    private Class<? extends Rule> rule;
    private String defaultMessage;
    private String definer;
    private int paramsCount;

    CustomRules(Class<? extends Rule> rule, String definer, int paramsCount, String defaultMessage) {
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
        return Stream.of(CustomRules.values())
                .filter(e -> e.getRuleName().equals(ruleName))
                .map(e -> (RuleDescriptor) e).findFirst();
    }
}