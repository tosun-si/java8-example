package com.tosunsi.example.designpattern.java7.decorator;

import com.tosunsi.example.designpattern.java8.decorator.Expenses;

/**
 * Created by Mazlum on 18/08/2016.
 */
public class DefaultProfitCalculator implements ProfitCalculator {

  @Override
  public double calculate(double turnover) {
    return Expenses.getTransportExpenses(turnover);
  }
}
