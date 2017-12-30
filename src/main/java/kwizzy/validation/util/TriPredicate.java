package kwizzy.validation.util;

@FunctionalInterface
public interface TriPredicate<T, U, V> {

    boolean test(T c, U i, V firstChar);

}
