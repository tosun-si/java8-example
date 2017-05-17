package com.tosunsi.example.designpattern.java8.validator;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.commons.lang.BooleanUtils;

import com.google.common.collect.Lists;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Monad that allows to compose and chain operation in order to validate many field of the given
 * object {@code <T>}.
 *
 * Created by Mazlum on 25/08/2016.
 */
@RequiredArgsConstructor
public class Validator<T> {

  @NonNull
  private T object;
  private List<IllegalArgumentException> errors = Lists.newArrayList();

  /**
   * Static factory method that allows to create new {@link Validator} instance, with given object.
   *
   * @param object current object
   * @return {@link Validator} with object
   */
  public static <T> Validator<T> of(final T object) {
    return new Validator<>(object);
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
  public <R> Validator<T> validate(final Function<? super T, ? extends R> projection,
      final Predicate<? super R> filter, final String message) {

    // Checks if current field is invalid. In this case an error message is added in a list that
    // contains all errors.
    final Predicate<T> filterOnField = projection.andThen(filter::test)::apply;
    final boolean isValidField = filterOnField.test(object);
    Optional.of(isValidField).filter(BooleanUtils::isFalse)
        .ifPresent(e -> this.errors.add(new IllegalArgumentException(message)));

    return this;
  }

  /**
   * Gets error messages, if it exists validation errors.<br>
   * If there are no error, current object in validator is returned, otherwise an
   * {@link IllegalArgumentException} is thrown with all error messages.*
   */
  public void execute() {

    // If there is no error, we return current object.
    if (errors.isEmpty()) {
      return;
    }

    // Otherwise an exception is thrown with all error message.
    final IllegalArgumentException exception = new IllegalArgumentException();
    errors.forEach(exception::addSuppressed);
    throw exception;
  }

  /**
   * Gets all errors.
   * 
   * @return errors represented by a list of {@link IllegalArgumentException}
   */
  public List<IllegalArgumentException> get() {
    return errors;
  }
}
