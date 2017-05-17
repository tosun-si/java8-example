package com.tosunsi.example.pojo;

import java.util.Optional;

import com.google.common.base.MoreObjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Test class in order to create a pojo for person.
 * 
 * Created by Mazlum on 02/08/2016.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

  // Fields.

  private String firstName;
  private String lastName;
  private String civility;
  private Integer age;
  private Car car;

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


  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("firstName", firstName).add("lastName", lastName)
        .add("civility", civility).add("age", age).add("car", car).toString();
  }
}
