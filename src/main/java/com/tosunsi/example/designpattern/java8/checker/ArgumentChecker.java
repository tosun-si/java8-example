package com.tosunsi.example.designpattern.java8.checker;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

import com.google.common.collect.Lists;

import com.tosunsi.example.pojo.Airbag;
import com.tosunsi.example.pojo.Person;
import com.tosunsi.example.pojo.User;

/**
 * Created by Mazlum on 30/12/2016.
 */
public final class ArgumentChecker<T> {

  private final T object;
  private final List<IllegalArgumentException> exceptions;

  private ArgumentChecker(final T object, final List<IllegalArgumentException> exceptions) {
    this.object = object;
    this.exceptions = exceptions;
  }

  public static <T> ArgumentChecker<T> on(final T object) {
    return new ArgumentChecker<T>(object, Lists.newArrayList());
  }

  public <U> ArgumentChecker<U> thenOn(final U otherObject) {
    return new ArgumentChecker<>(otherObject, exceptions);
  }

  public <R> ArgumentChecker<T> check(final Function<? super T, ? extends R> projection,
      final Predicate<? super R> filter, final String message) {

    if (!filter.test(projection.apply(object))) {
      exceptions.add(new IllegalArgumentException(message));
    }

    return this;
  }

  public T get() {

    // If there is no error, we return current object.
    if (exceptions.isEmpty()) {
      return object;
    }

    // Otherwise an exception is thrown with all error message.
    final IllegalArgumentException exception = new IllegalArgumentException();
    exceptions.forEach(exception::addSuppressed);
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
        .get();

  }
}
