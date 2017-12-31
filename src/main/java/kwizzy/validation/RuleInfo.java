package kwizzy.validation;

import kwizzy.validation.impl.RuleDescriptor;
import kwizzy.validation.parser.RuleLexer;

import java.util.Arrays;
import java.util.List;

public class RuleInfo {

    private final String field;
    private final RuleDescriptor ruleObj;
    private final String ruleName;
    private final int paramsCount;
    private final List<String> params;

    public RuleInfo(String path, RuleDescriptor ruleObj, RuleLexer.Token tok) {
        this.field = path;
        this.ruleName = ruleObj.getRuleName();
        this.paramsCount = tok.getParams().size();
        this.params = tok.getParams();
        this.ruleObj = ruleObj;
    }

    public RuleInfo(String path, RuleDescriptor rule, String... params) {
        this.field = path;
        this.ruleName = rule.getRuleName();
        this.paramsCount = params.length;
        this.params = Arrays.asList(params);
        this.ruleObj = rule;
    }

    public String getField() {
        return field;
    }

    public RuleDescriptor getRuleObj() {
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
