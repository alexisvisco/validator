package fr.maed.website.kwizzy.validation.util;

public class BiValue<T, U> {
    public T t;
    public U u;

    public BiValue(T t, U u) {
        this.t = t;
        this.u = u;
    }
}
