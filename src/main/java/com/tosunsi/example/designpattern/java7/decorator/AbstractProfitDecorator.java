package com.tosunsi.example.designpattern.java7.decorator;

/**
 * Created by Mazlum on 18/08/2016.
 */
public abstract class AbstractProfitDecorator implements ProfitCalculator {

  private final ProfitCalculator profitCalculator;

  public AbstractProfitDecorator(ProfitCalculator profitCalculator) {
    this.profitCalculator = profitCalculator;
  }

  protected abstract double applyExpense(double turnover);

  @Override
  public double calculate(double turnover) {
    double profit = profitCalculator.calculate(turnover);
    return applyExpense(profit);
  }
}
