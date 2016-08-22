package com.tosunsi.example.pojo;

import com.google.common.base.MoreObjects;

/**
 * Test class in order to create a pojo for airbag.
 * 
 * Created by Mazlum on 02/08/2016.
 */
public class Airbag {

  // Field.
  private String brand;

  // Getter/setter.
  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  // To String method.

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("brand", brand).toString();
  }
}
