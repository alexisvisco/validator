package fr.maed.website.kwizzy.validation;

import fr.maed.website.kwizzy.validation.rules.RuleObj;
import fr.maed.website.kwizzy.validation.util.EnumsList;

import java.util.Arrays;
import java.util.Optional;

public class RuleInfo {

    private String path;
    private String rule;
    private String[] explodedRule;
    private EnumsList list;
    private int paramsCount;
    private String[] params= {};
    private String ruleName;
    private RuleObj obj;

    public RuleInfo(String path, String rule, EnumsList list) {
        this.path = path;
        this.rule = rule;
        this.explodedRule = rule.split(":");
        this.list = list;
        this.ruleName = explodedRule[0];
        this.paramsCount = explodedRule.length == 1 ? 0 : explodedRule[1].split(",").length;
        if (paramsCount > 0)
            params = explodedRule[1].split(",");

    }

    public String getRule() {
        return rule;
    }

    public String[] getExplodedRule() {
        return explodedRule;
    }

    public int getParamsCount() {
        return paramsCount;
    }

    public String[] getParams() {
        return params;
    }

    public String getRuleName() {
        return ruleName;
    }

    public Optional<RuleObj> getRuleObj() {
        if (obj == null)
        {
            Optional<RuleObj> rule = list.getRule(this);
            rule.ifPresent(ruleObj -> obj = ruleObj);
            return rule;
        }
        return Optional.of(obj);
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "RuleInfo{" +
                "path='" + path + '\'' +
                ", rule='" + rule + '\'' +
                ", explodedRule=" + Arrays.toString(explodedRule) +
                ", paramsCount=" + paramsCount +
                ", params=" + Arrays.toString(params) +
                ", ruleName='" + ruleName + '\'' +
                ", obj=" + obj +
                '}';
    }
}
