package com.tosunsi.example.designpattern.java8.decorator;

import java.util.function.DoubleUnaryOperator;

/**
 * Allows to apply a default expenses for calculation of profit.
 * 
 * Created by Mazlum on 03/08/2016.
 */
public class DefaultProfitCalculator implements DoubleUnaryOperator {

  @Override
  public double applyAsDouble(final double operand) {
    return Expenses.getTransportExpenses(operand);
  }
}
