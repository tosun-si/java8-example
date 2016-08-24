package com.tosunsi.example.designpattern.java8.visitor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by Mazlum on 24/08/2016.
 */
public class Visitor<R> {

    private Map<Class<?>, Function<Object, R>> map = new HashMap<>();

    private Visitor() {
    }

    public static <R> Visitor<R> builder() {
        return new Visitor<R>();
    }

    public <T> Visitor<R> when(final Class<T> clazz, final Function<T, R> function) {

        map.put(clazz, object -> function.apply(clazz.cast(object)));

        //map.put(clazz, function.compose(clazz::cast));
        return this;
    }

    public R call(final Object object) {
        return map.getOrDefault(object.getClass(), o -> {
            throw new IllegalArgumentException("Unknown : " + object.getClass());
        }).apply(object);
    }
}
