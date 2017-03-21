package com.tosunsi.example.designpattern.java7.decorator;

import com.tosunsi.example.designpattern.java8.decorator.Expenses;

/**
 * Created by Mazlum on 18/08/2016.
 */
public class ExceptionalExpensesDecorator extends AbstractProfitDecorator {

  public ExceptionalExpensesDecorator(ProfitCalculator profitCalculator) {
    super(profitCalculator);
  }

  @Override
  protected double applyExpense(double turnover) {
    return Expenses.getExceptionalExpenses(turnover);
  }
}
