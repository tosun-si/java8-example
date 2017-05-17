package com.tosunsi.example.optional;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;

import com.tosunsi.example.pojo.Airbag;
import com.tosunsi.example.pojo.Car;
import com.tosunsi.example.pojo.Person;

/**
 * Allows to test Optionals.
 * 
 * Created by Mazlum on 02/08/2016.
 */
public class OptionalTest {

  @Test
  public void testFlatMapEmptyOptionalWithLambda() {

    // Test data.
    final Person person = new Person();
    person.setLastName("Scoffield");
    person.setFirstName("Mickael");

    // Chains optionals with flap map and lambdas.
    final String airbagBrand = Optional.of(person).flatMap(p -> p.getCar())
        .flatMap(c -> c.getAirbag()).orElseGet(() -> new Airbag()).getBrand();

    // Asserts.
    assertThat(airbagBrand).isNull();
  }

  @Test
  public void testFlatMapEmptyOptionalWithMethodReference() {

    // Test data.
    final Person person = new Person();
    person.setLastName("Scoffield");
    person.setFirstName("Mickael");

    // Chains optionals with flap map and method reference.
    final String airbagBrand = Optional.of(person).flatMap(Person::getCar).flatMap(Car::getAirbag)
        .orElseGet(Airbag::new).getBrand();

    // Asserts.
    assertThat(airbagBrand).isNull();
  }

  @Test
  public void testFlatMapNotEmptyOptionalWithMethodReference() {

    // Test data.
    final String brand = "AIRBAG PSG";
    final Airbag airbag = Airbag.builder().brand(brand).build();

    final Car car = new Car();
    car.setName("BMW");
    car.setAirbag(airbag);

    final Person person = new Person();
    person.setLastName("Scoffield");
    person.setFirstName("Mickael");
    person.setCar(car);

    // Chains optionals with flap map and method reference.
    final String airbagBrand = Optional.of(person).flatMap(Person::getCar).flatMap(Car::getAirbag)
        .orElseGet(Airbag::new).getBrand();

    // Asserts.
    assertThat(airbagBrand).isNotNull().isNotEmpty().isEqualTo(brand);

    Optional.of(person).flatMap(Person::getCar).flatMap(Car::getAirbag)
        .ifPresent(System.out::println);
  }
}
