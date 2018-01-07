package kwizzy.validation.annot;

import kwizzy.validation.Validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Inject rule behavior inside a field.
 *
 * value = rule(s).
 * replacements = $NUMBER or :NUMBER params.
 * Like {@link Validator#addRule(String, String...)}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Rules {
    String value();
    String[] replacements() default {};
}
