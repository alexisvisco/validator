package fr.maed.website.kwizzy.validation.rules.list.number;

import fr.maed.website.kwizzy.validation.RuleInfo;
import fr.maed.website.kwizzy.validation.impl.Form;
import fr.maed.website.kwizzy.validation.rules.AbstractRule;
import fr.maed.website.kwizzy.validation.rules.Rules;
import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Optional;

/**
 * Ref to {@link Rules#HIGHER}<br/>
 * Example:
 * <pre>
 * "123"  124  -> false
 * "42"   34   -> true
 * "1"    0    -> true
 * "123"  1000 -> false
 * </pre>
 **/
public class RuleHigher extends AbstractRule {

    public RuleHigher(RuleInfo value) {
        super(value);
    }

    @Override
    public boolean isOkay(Form f) {
        Optional<String> s = f.getString(rule.getPath());
        String param = getRuleInfo().getParams()[0];
        if (s.isPresent() && NumberUtils.isParsable(s.get())) {
            JexlEngine jexl = new JexlBuilder().create();
            String jexlExp = String.format("%s > %s", s.get(), param);
            JexlExpression e = jexl.createExpression(jexlExp);
            return (boolean)e.evaluate(new MapContext());
        }
        return false;
    }
}