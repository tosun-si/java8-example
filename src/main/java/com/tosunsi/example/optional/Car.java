package com.tosunsi.example.optional;

import java.util.Optional;

/**
 * Test class in order to create a pojo for car.
 * 
 * Created by Mazlum on 02/08/2016.
 */
public class Car {

  // Field.

  private String name;
  private Airbag airbag;

  // Getter/setter.

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Optional<Airbag> getAirbag() {
    return Optional.ofNullable(airbag);
  }

  public void setAirbag(Airbag airbag) {
    this.airbag = airbag;
  }
}
