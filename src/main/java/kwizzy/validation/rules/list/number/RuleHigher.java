package kwizzy.validation.rules.list.number;

import kwizzy.validation.impl.Form;
import kwizzy.validation.rules.list.AbstractRule;
import kwizzy.validation.rules.DefaultRules;
import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Optional;

/**
 * Ref to {@link DefaultRules#HIGHER}<br/>
 * Usage: addRule("field -> higher:int i") <br/>
 * Example:
 * <pre>
 * "123"  124  -> false
 * "42"   34   -> true
 * "1"    0    -> true
 * "123"  1000 -> false
 * </pre>
 **/
public class RuleHigher extends AbstractRule {

    @Override
    public boolean isOkay(Form f) {
        Optional<String> s = f.getString(rule.getField());
        String param = getRuleInfo().getParams().get(0);
        if (s.isPresent() && NumberUtils.isNumber(s.get())) {
            JexlEngine jexl = new JexlBuilder().create();
            String jexlExp = String.format("%s > %s", s.get(), param);
            JexlExpression e = jexl.createExpression(jexlExp);
            return (boolean)e.evaluate(new MapContext());
        }
        return false;
    }
}