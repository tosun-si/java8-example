package com.tosunsi.example.designpattern.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by Mazlum on 25/08/2016.
 */
public class Validator<T> {

    private T t;
    private List<Throwable> exceptions = new ArrayList<>();

    private Validator(T t) {
        this.t = t;
    }

    public static <T> Validator<T> of(final T t) {
        return new Validator<T>(t);
    }

    public <U> Validator<T> validate(final Function<T, U> projection, final Predicate<U> filter,
                                     final String message) {

        if (!filter.test(projection.apply(t))) {
            this.exceptions.add(new IllegalStateException(message));
        }

        // final Predicate<T> function = projection.andThen(u -> filter.test(u))::apply;

        return this;
    }

    /**
     * Gets exceptions, if it exists validation errors.
     *
     * @return {@link Throwable} exception if it exists validation errors
     */
    public List<Throwable> get() {
        return exceptions;
    }
}
