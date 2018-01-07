package kwizzy.validation;

import kwizzy.validation.impl.RuleDescriptor;
import kwizzy.validation.parser.RuleLexer;
import org.json.JSONArray;

import java.util.Arrays;
import java.util.List;

public class RuleInfo {

    private final String field;
    private final RuleDescriptor ruleObj;
    private final String ruleName;
    private final int paramsCount;
    private final List<String> params;
    private boolean isOptional;

    public RuleInfo(String path, RuleDescriptor ruleObj, RuleLexer.Token tok) {
        this.field = path;
        this.ruleName = ruleObj.getRuleName();
        this.paramsCount = tok.getParams().size();
        this.params = tok.getParams();
        this.ruleObj = ruleObj;
        setOptional();
    }

    public RuleInfo(String path, RuleDescriptor rule, String... params) {
        this.field = path;
        this.ruleName = rule.getRuleName();
        this.paramsCount = params.length;
        this.params = Arrays.asList(params);
        this.ruleObj = rule;
        setOptional();
    }

    private void setOptional() {
        if (ruleName.equals("optional") && paramsCount == 0)
            isOptional = true;

        else if (ruleName.contains("optional") && paramsCount == 1) {
            try {
                JSONArray ja = new JSONArray(params.get(0));
                for (int i = 0; i < ja.length(); i++) {
                    if (field.equals(ja.getString(i))) {
                        isOptional = true;
                    }
                }
            } catch (Exception e) { }
        }
    }

    public boolean isOptional() {
        return isOptional;
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
                ", isOptional=" + isOptional +
                '}';
    }

    protected void replacer(String... replacements) {
        for (int i = 0; i < params.size(); i++) {
            for (int y = 0; y < replacements.length; y++) {
                String re = params.get(i).replace("$" + (y + 1), replacements[y]);
                params.set(i, re);
            }
        }
        setOptional();
    }
}
