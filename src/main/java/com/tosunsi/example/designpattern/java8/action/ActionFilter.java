package com.tosunsi.example.designpattern.java8.action;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Allows to execute an action with consumer preceding by a predicate.
 * 
 * Created by Mazlum on 25/08/2016.
 */
public class ActionFilter<T> {

  private ActionFilter(T t) {
    this.t = t;
  }

  private T t;
  private Map<Boolean, Consumer<T>> map = new HashMap<>();

  public static <T> ActionFilter<T> of(T t) {
    return new ActionFilter<T>(t);
  }

  public ActionFilter<T> add(final Predicate<T> filter, final Consumer<T> consumer) {
    map.put(filter.test(t), consumer::accept);
    return this;
  }

  public void execute() {
    map.getOrDefault(true, t -> System.out.println("No action has been found")).accept(t);
  }
}
