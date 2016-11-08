package com.tosunsi.example.designpattern.java8.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.tosunsi.example.tuple.Tuple3;

/**
 * Monad that allows to compose and chain operation in order to validate many field of the given
 * object {@code <T>}.<br>
 * All higher order function are evaluated and executed only in a final method.
 *
 * Created by Mazlum on 30/10/2016.
 */
public class LazyValidator<T> {

  // Fields.
  private final T t;
  private final List<Tuple3<Function<? super T, ?>, Predicate<?>, String>> tuples =
      new ArrayList<>();

  /**
   * Constructor with given object.
   *
   * @param t current object
   */
  private LazyValidator(final T t) {
    this.t = t;
  }

  /**
   * Static factory method that allows to create new {@link LazyValidator} instance, with given
   * object.
   *
   * @param t current object
   * @return {@link LazyValidator} with object
   */
  public static <T> LazyValidator<T> of(final T t) {
    return new LazyValidator<>(t);
  }

  /**
   * Allows to validate a projection that contains current field to validate. Projection is chained
   * with a {@link Predicate} that matches with function return field.
   *
   * @param projection current projection
   * @param filter current predicate
   * @param message current message to add in exception list, if current {@link Predicate} returns
   *        false
   * @return current {@link LazyValidator}
   */
  public <R> LazyValidator<T> validate(Function<? super T, ? extends R> projection,
      final Predicate<? super R> filter, final String message) {

    tuples.add(Tuple3.of(projection, filter, message));

    return this;
  }

  /**
   * Final method that executes all higher order function lazily and gets all errors in
   * {@link IllegalStateException}.<br>
   *
   * @return {@link Throwable} exception if it exists validation errors
   */
  public List<Throwable> get() {

    // Executes all higher order function lazily and gets all errors.
    return tuples.stream().filter(this::hasInvalidField).map(Tuple3::_3)
        .map(IllegalStateException::new).collect(Collectors.toList());
  }

  /**
   * Checks if the given {@link Tuple3} has an invalid field with composition of projection and
   * filter.
   *
   * @param tuple current tuple
   * @return boolean result
   */
  private <R> boolean hasInvalidField(
      final Tuple3<Function<? super T, ?>, Predicate<?>, String> tuple) {

    // Gets function and predicate in tuple and composes them.
    final Function<? super T, ? extends R> projection =
        (Function<? super T, ? extends R>) tuple._1();
    final Predicate<? super R> filter = (Predicate<? super R>) tuple._2();

    return !filter.test(projection.apply(t));
  }
}
