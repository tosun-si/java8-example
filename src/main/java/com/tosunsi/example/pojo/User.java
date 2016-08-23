package com.tosunsi.example.pojo;

/**
 * Test class in order to create a pojo for user.
 * 
 * Created by Mazlum on 02/08/2016.
 */
public class User {

  // Fields.

  private String firstName;
  private String lastName;
  private Integer age;

  public User() {}

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
}
