package com.tosunsi.example.designpattern.java8.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Monad that allows to compose and chain operation in order to validate many field of the given
 * object {@code <T>}.
 * 
 * Created by Mazlum on 25/08/2016.
 */
public class Validator<T> {

  private T t;
  private List<Throwable> exceptions = new ArrayList<>();

  /**
   * Constructor with given object.
   *
   * @param t current object
   */
  private Validator(final T t) {
    this.t = t;
  }

  /**
   * Static factory method that allows to create new {@link Validator} instance, with given object.
   *
   * @param t current object
   * @return {@link Validator} with object
   */
  public static <T> Validator<T> of(final T t) {
    return new Validator<T>(t);
  }

  /**
   * Allows to validate a projection that contains current field to validate. Projection is chained
   * with a {@link Predicate} that matches with function return field.
   *
   * @param projection current projection
   * @param filter current predicate
   * @param message current message to add in exception list, if current {@link Predicate} returns
   *        false
   * @return current {@link Validator}
   */
  public <U> Validator<T> validate(Function<? super T, ? extends U> projection,
      Predicate<? super U> filter, final String message) {

    if (!filter.test(projection.apply(t))) {
      this.exceptions.add(new IllegalStateException(message));
    }

    // final Predicate<T> function = projection.andThen(predicate::test)::apply;

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
