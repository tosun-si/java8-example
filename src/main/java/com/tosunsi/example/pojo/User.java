package com.tosunsi.example.pojo;

import com.google.common.base.MoreObjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Test class in order to create a pojo for user.
 * 
 * Created by Mazlum on 02/08/2016.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

  // Fields.

  private String firstName;
  private String lastName;
  private Integer age;

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

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("firstName", firstName).add("lastName", lastName)
        .add("age", age).toString();
  }
}
