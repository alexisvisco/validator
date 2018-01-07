package kwizzy.validation.annot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Must be on a field that is an object.
 * Need to create a method inside the class that
 * is non-static and return the type of the field.
 *
 * value = name of the function.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CustomBinding {
    String value();
}
