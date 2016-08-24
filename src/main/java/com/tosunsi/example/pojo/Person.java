package com.tosunsi.example.pojo;

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
  private String civility;
  private Integer age;
  private Car car;

  public Person() {}

  public Person(String firstName, String lastName, final String civility, final Integer age) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.civility = civility;
    this.age = age;
  }

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

  public String getCivility() {
    return civility;
  }

  public void setCivility(String civility) {
    this.civility = civility;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Optional<Car> getCar() {
    return Optional.ofNullable(car);
  }

  public void setCar(Car car) {
    this.car = car;
  }
}
