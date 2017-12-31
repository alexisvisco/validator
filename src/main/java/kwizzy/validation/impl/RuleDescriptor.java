package kwizzy.validation.impl;

public interface RuleDescriptor {

    /**
     * Is a way to get the Rule to execute the {@link Rule#isOkay(Form)}
     * function.
     * @return a Rule.
     */
    Class<? extends Rule> getRule();

    /**
     * Is the default message write in english (this is a convention that
     * i have created).
     * :attr is the ref to the attribute field.
     * :NUMBER is the ref to the param name X from 1 to the number of
     * params that the rule have.
     * @return a messages.
     */
    String getDefaultMessage();

    /**
     * Is here if one day i decide to implement message injection.
     * @param ruleName name of the rule.
     * @param message to inject
     */
    void setDefaultMessage(String ruleName, String message);

    /**
     * Retrieve the rule name.
     * @return
     */
    String getRuleName();

    /**
     * Get number of params for instance range rule have two params.
     * If params count is -1 the rule has a dynamic param like ... in
     * Java.
     * @return the number of params of the rule.
     */
    int getParamsCount();
}
