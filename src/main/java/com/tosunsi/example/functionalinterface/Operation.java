package com.tosunsi.example.functionalinterface;

/**
 * Created by Mazlum on 22/08/2016.
 */
@FunctionalInterface
public interface Operation {

  /**
   * Allows to apply an operation.
   * 
   * @param value1 value 1
   * @param value2 value 2
   * @return java.lang.Integer for result
   */
  int apply(final int value1, final int value2);
}
