package com.tosunsi.example.designpattern.java8.action;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by Mazlum on 25/08/2016.
 */
public class Action<T> {

  private Action(T t) {
    this.t = t;
  }

  private T t;
  private Map<Boolean, Consumer<T>> map = new HashMap<>();

  public static <T> Action<T> of(T t) {
    return new Action<T>(t);
  }

  public Action<T> add(final Predicate<T> filter, final Consumer<T> consumer) {

    map.put(filter.test(t), consumer::accept);

    return this;
  }

  public void execute() {
    map.getOrDefault(true, t -> System.out.println("No action has been found")).accept(t);
  }
}
