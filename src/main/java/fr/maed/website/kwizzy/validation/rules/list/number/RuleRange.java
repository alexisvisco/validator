package fr.maed.website.kwizzy.validation.rules.list.number;

import fr.maed.website.kwizzy.validation.RuleInfo;
import fr.maed.website.kwizzy.validation.impl.Form;
import fr.maed.website.kwizzy.validation.rules.AbstractRule;
import fr.maed.website.kwizzy.validation.rules.Rules;
import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Optional;

/**
 * Ref to {@link Rules#RANGE}<br/>
 * Example:
 * <pre>
 * "123"  12,124  -> true
 * "42"   45,46   -> false
 * </pre>
 **/
public class RuleRange extends AbstractRule {

    public RuleRange(RuleInfo value) {
        super(value);
    }

    @Override
    public boolean isOkay(Form f) {
        Optional<String> s = f.getString(rule.getPath());
        String p1 = getRuleInfo().getParams()[0];
        String p2 = getRuleInfo().getParams()[1];
        if (s.isPresent() && NumberUtils.isParsable(s.get())) {
            System.out.println(String.format("%s <= %s && %s >= %s", s.get(), p2, s.get(), p1));
            JexlEngine jexl = new JexlBuilder().create();
            String jexlExp = String.format("%s <= %s && %s >= %s", s.get(), p2, s.get(), p1);
            JexlExpression e = jexl.createExpression(jexlExp);
            return (boolean)e.evaluate(new MapContext());
        }
        return false;
    }
}