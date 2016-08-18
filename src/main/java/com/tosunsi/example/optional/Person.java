package com.tosunsi.example.optional;

import java.util.Optional;

/**
 * Test class in order to create a pojo for person.
 * 
 * Created by Mazlum on 02/08/2016.
 */
public class Person {

  // Fields.

  private String firstName;
  private String lastName;
  private Car car;

  // Getters/setters.

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Optional<Car> getCar() {
    return Optional.ofNullable(car);
  }

  public void setCar(Car car) {
    this.car = car;
  }
}
