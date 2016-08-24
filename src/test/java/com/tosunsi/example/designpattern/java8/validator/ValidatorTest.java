package com.tosunsi.example.designpattern.java8.validator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.function.IntPredicate;

import org.junit.Test;

import com.tosunsi.example.designpattern.java8.Validator;
import com.tosunsi.example.pojo.Person;

/**
 * Allows to test revisited GOF decorator pattern with lambda.
 * 
 * Created by Mazlum on 03/08/2016.
 */
public class ValidatorTest {

  @Test
  public void testValidatorWithLambdaWithoutError() {

    // Test data.
    final Person person1 = new Person("Zlatan", "Ibra", "MR", 34);

    final List<Throwable> result = Validator.of(person1)
        .validate(Person::getAge, a -> a != null, "The age should not be empty")
        .validate(Person::getFirstName, f -> f != null, "First name should not be empty").get();

    // Asserts.
    assertThat(result).isEmpty();
  }

  @Test
  public void testValidatorWithLambdaWithError() {

    // Test data.
    final Person person1 = new Person("Zlatan", "Ibra", "MR", 10);

    final List<Throwable> result = Validator.of(person1)
        .validate(Person::getAge, a -> a > 10, "The age should be greater than 10")
        .validate(Person::getFirstName, f -> f != null, "First name must not be empty").get();

    // Asserts.
    assertThat(result).isNotEmpty().hasSize(1);
  }

  @Test
  public void testValidatorWithMethodReference() {

    // Test data.
    final Person person1 = new Person("Zlatan", "Ibra", "MR", 10);

    final List<Throwable> result = Validator.of(person1)
        .validate(Person::getAge, inBetween(5, 15)::test, "The age should be greater than 10")
        .validate(Person::getFirstName, f -> f != null, "First name must not be empty").get();

    // Asserts.
    assertThat(result).isEmpty();
  }

  private static IntPredicate inBetween(int start, int end) {
    return value -> value > start && value < end;
  }
}
