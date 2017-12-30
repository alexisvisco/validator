package kwizzy.validation.rules;

import kwizzy.validation.rules.list.Rule;

public interface RuleObj {

    Class<? extends Rule> getRule();

    String getDefaultMessage();

    void setDefaultMessage(String ruleName, String message);

    String getRuleName();

    int getParamsCount();
}
