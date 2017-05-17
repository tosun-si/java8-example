package com.tosunsi.example.designpattern.java8.checker;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.commons.lang.BooleanUtils;

import com.google.common.collect.Lists;

import com.tosunsi.example.pojo.Airbag;
import com.tosunsi.example.pojo.Person;
import com.tosunsi.example.pojo.User;

import lombok.AllArgsConstructor;

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

  public static void main(String[] args) {

    // final Person person = PersonHelper.INSTANCE.getPersons().get(0);
    final Person person = new Person();
    person.setAge(20);
    person.setFirstName("Toto");

    final User user = new User();
    // user.setFirstName("Cristiano");
    user.setLastName("Ronaldo");

    final Airbag airbag = new Airbag();
    airbag.setBrand("Test");

    ArgumentChecker.on(person)
        .check(Person::getAge, Objects::nonNull, "Person age should not be null")
        .check(Person::getFirstName, Objects::nonNull, "Person first name should not be null")
        .thenOn(user)
        .check(User::getLastName, Objects::nonNull, "User first name should not be null")
        .thenOn(airbag).check(Airbag::getBrand, Objects::nonNull, "Airbag brand should not be null")
        .execute();
  }
}
