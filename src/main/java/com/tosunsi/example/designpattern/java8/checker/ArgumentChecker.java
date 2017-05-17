package com.tosunsi.example.designpattern.java8.checker;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;

import com.tosunsi.example.pojo.Airbag;
import com.tosunsi.example.pojo.Car;
import com.tosunsi.example.pojo.Person;
import com.tosunsi.example.pojo.User;

import lombok.AllArgsConstructor;
import lombok.val;

/**
 * Monad that allows to compose and chain operation in order to validate many field of the given
 * object {@code <T>}.
 * 
 * Created by Mazlum on 30/12/2016.
 */
@AllArgsConstructor
public class ArgumentChecker<T> {

  private final T object;
  private final List<IllegalArgumentException> errors;

  /**
   * Static factory method that allows to create new {@link ArgumentChecker} instance, with given
   * object.
   *
   * @param object current object
   * @return this
   */
  public static <T> ArgumentChecker<T> on(final T object) {
    return new ArgumentChecker<T>(object, Lists.newArrayList());
  }

  /**
   * Projects checker on other object.
   * 
   * @param otherObject other object
   * @return this
   */
  public <U> ArgumentChecker<U> thenOn(final U otherObject) {
    return new ArgumentChecker<>(otherObject, errors);
  }

  /**
   * Allows to validate a projection that contains current field to validate. Projection is chained
   * with a {@link Predicate} that matches with function return field.
   *
   * @param projection current projection
   * @param filter current predicate
   * @param message current message to add in exception list, if current {@link Predicate} returns
   *        false
   * @return this
   */
  public <R> ArgumentChecker<T> check(final Function<? super T, ? extends R> projection,
      final Predicate<? super R> filter, final String message) {

    // Checks if current field is invalid. In this case an error message is added in a list that
    // contains all errors.
    final Predicate<T> isNotNull = Objects::nonNull;
    final Predicate<T> filterOnField = projection.andThen(filter::test)::apply;
    final boolean isValidField = isNotNull.and(filterOnField).test(object);

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

  public static void main(String[] args) {

    val person = Person.builder().age(14).firstName("Toto").build();
    val user =  User.builder().lastName("Ronaldo").build();
    val airbag = Airbag.builder().brand("Test").build();
    val car = new Car();

    ArgumentChecker.on(person)
        .check(Person::getAge, Objects::nonNull, "Person age should not be null")
        .check(Person::getAge, inBetween(5, 15)::test, "The age should be between 5 and 15")
        .check(Person::getFirstName, StringUtils::isNotEmpty, "Person first name should not be empty")
        .thenOn(user)
        .check(User::getLastName, Objects::nonNull, "User first name should not be null")
        .thenOn(airbag)
        .check(Airbag::getBrand, Objects::nonNull, "Airbag brand should not be null")
        .thenOn(car)
        .check(Function.identity(), Objects::nonNull, "Car should not be null")
        .execute();
  }

  private static IntPredicate inBetween(int start, int end) {
    return value -> value > start && value < end;
  }
}
