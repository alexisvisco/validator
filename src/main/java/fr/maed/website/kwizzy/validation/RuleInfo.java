package fr.maed.website.kwizzy.validation;

import fr.maed.website.kwizzy.validation.rules.RuleObj;
import fr.maed.website.kwizzy.validation.parser.RuleLexer;

import java.util.List;

public class RuleInfo {

    private final String field;
    private final RuleObj ruleObj;
    private final String ruleName;
    private final int paramsCount;
    private final List<String> params;

    public RuleInfo(String path, RuleObj ruleObj, RuleLexer.Token tok) {
        this.field = path;
        this.ruleName = ruleObj.getRuleName();
        this.paramsCount = tok.getParams().size();
        this.params = tok.getParams();
        this.ruleObj = ruleObj;
    }

    public String getField() {
        return field;
    }

    public RuleObj getRuleObj() {
        return ruleObj;
    }

    public String getRuleName() {
        return ruleName;
    }

    public int getParamsCount() {
        return paramsCount;
    }

    public List<String> getParams() {
        return params;
    }

    @Override
    public String toString() {
        return "RuleInfo{" +
                "field='" + field + '\'' +
                ", ruleObj=" + ruleObj +
                ", ruleName='" + ruleName + '\'' +
                ", paramsCount=" + paramsCount +
                ", params=" + params +
                '}';
    }
}
