package com.tosunsi.example.pojo;

import com.google.common.base.MoreObjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Test class in order to create a pojo for airbag.
 * 
 * Created by Mazlum on 02/08/2016.
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Airbag {

  // Field.
  private String brand;

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("brand", brand).toString();
  }
}
