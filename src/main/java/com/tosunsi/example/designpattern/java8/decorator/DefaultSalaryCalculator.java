package com.tosunsi.example.designpattern.java8.decorator;

import java.util.function.DoubleUnaryOperator;

/**
 * Allows to apply default salary.
 * 
 * Created by Mazlum on 03/08/2016.
 */
  public class DefaultSalaryCalculator implements DoubleUnaryOperator {

  @Override
  public double applyAsDouble(double operand) {
    return Taxes.getDefaultTax(operand);
  }
}
