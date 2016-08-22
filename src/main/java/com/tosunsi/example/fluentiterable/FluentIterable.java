package com.tosunsi.example.fluentiterable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by Mazlum on 22/08/2016.
 */
public final class FluentIterable<T> {

  private List<T> list;

  /**
   * Private constructor.
   * 
   * @param list list
   */
  private FluentIterable(List<T> list) {
    this.list = new ArrayList<>();
  }

  /**
   * Static factory that allows to initialize fluent iterable from te given list.
   * 
   * @param list list
   * @param <T> type of list
   * @return FluentIterable
   */
  public static <T> FluentIterable<T> from(final List<T> list) {
    return new FluentIterable<T>(list);
  }

  /**
   * Allows to filter a list by the given predicate.
   * 
   * @param predicate predicate
   * @return FluentIterable
   */
  public FluentIterable<T> filter(final Predicate<T> predicate) {

    final List<T> filteredList = new ArrayList<>();
    this.list.forEach(t -> {
      if (predicate.test(t)) {
        list.add(t);
      }
    });

    return from(filteredList);
  }

  /**
   * Allows to transform a list of objects to another list.
   * 
   * @param function current function
   * @param <R> type of transformed list
   * @return FluentIterable
   */
  public <R> FluentIterable<R> map(final Function<T, R> function) {

    final List<R> transformedList = new ArrayList<>();
    this.list.forEach(t -> {
      transformedList.add(function.apply(t));
    });

    return from(transformedList);
  }

  public List<T> toList() {
    return this.list;
  }
}
