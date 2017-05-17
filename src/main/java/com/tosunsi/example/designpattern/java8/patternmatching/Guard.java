package com.tosunsi.example.designpattern.java8.patternmatching;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import com.google.common.collect.Maps;

import com.tosunsi.example.designpattern.java8.action.Direction;

/**
 * Created by Mazlum on 31/03/2017.
 */
public class Guard<T> {

  private T object;
  private final Map<Boolean, Function<Object, Object>> cases;

  private Guard(T object) {
    this.object = object;
    this.cases = Maps.newHashMap();
  }

  public static <T> Guard<T> of(T object) {
    return new Guard<T>(object);
  }

  public <R> Guard<T> when(final Predicate<T> predicate, final Function<R, R> function) {
    cases.putIfAbsent(predicate.test(object), object -> function.apply((R) object));
    return this;
  }

  public <R> R apply(final R object) {
    return (R) cases.getOrDefault(true, t -> {
      throw new IllegalStateException("");
    }).apply(object);
  }

  public static void main(String[] args) {

    final Direction direction = Direction.RIGHT;

    final String result = Guard.of(direction).when(Direction.LEFT::equals, t -> "LEFT")
        .when(Direction.RIGHT::equals, Function.identity()).apply("Lolo");
  }
}
