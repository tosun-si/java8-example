package com.tosunsi.example.designpattern.java7.decorator;

/**
 * Created by Mazlum on 18/08/2016.
 */
public abstract class AbstractTaxDecorator implements SalaryCalculator {

  private final SalaryCalculator salaryCalculator;

  public AbstractTaxDecorator(SalaryCalculator salaryCalculator) {
    this.salaryCalculator = salaryCalculator;
  }

  protected abstract double applyTax(double salary);

  @Override
  public double calculate(double grossAnnual) {
    double salary = salaryCalculator.calculate(grossAnnual);
    return applyTax(salary);
  }
}
