package kwizzy.validation.rules.list.other;

import kwizzy.validation.impl.Form;
import kwizzy.validation.rules.DefaultRules;
import kwizzy.validation.rules.list.AbstractRule;

import java.util.Optional;

/**
 * Ref to {@link DefaultRules#UNIQUE}<br/>
 * Usage: addRule("field -> unique: string collection, string field") <br/>
 * This rule check in the base (implemented by Form) if
 * in the current collection where field = field something exist.
 * If exist return false because this rule return only true if nothing
 * in the collection where field = field is value of the form field.
 * <br>
 * I admit that it is complicated to understand but imagine that we have
 * a given user database with a user who has the email foo@gmail.com. If another
 * user decides to register with the same address and the validator receives
 * this address to verify and unique on the user collection and with the email
 * field then the validation will be false.
 **/
public class RuleUnique extends AbstractRule {

    @Override
    public boolean isOkay(Form f) {
        Optional<String> value = f.getString(getRuleInfo().getField());
        if (getRuleInfo().getParamsCount() < 2 || !value.isPresent())
            return false;
        String collection = getRuleInfo().getParams().get(0);
        String field = getRuleInfo().getParams().get(1);
        return !f.exist(collection, field, value.get());
    }
}
