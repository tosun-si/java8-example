package com.tosunsi.example.designpattern.java7.decorator;

import com.tosunsi.example.designpattern.java8.decorator.Taxes;

/**
 * Created by Mazlum on 18/08/2016.
 */
public class NationalTaxDecorator extends AbstractTaxDecorator {

  public NationalTaxDecorator(SalaryCalculator salaryCalculator) {
    super(salaryCalculator);
  }

  @Override
  protected double applyTax(double salary) {
    return Taxes.getNationalTax(salary);
  }
}
