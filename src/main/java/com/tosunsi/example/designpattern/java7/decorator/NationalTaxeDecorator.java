package com.tosunsi.example.designpattern.java7.decorator;

import com.tosunsi.example.designpattern.java8.decorator.Taxes;

/**
 * Created by Mazlum on 18/08/2016.
 */
public class NationalTaxeDecorator extends AbstractTaxDecorator {

  public NationalTaxeDecorator(SalaryCalculator salaryCalculator) {
    super(salaryCalculator);
  }

  @Override
  protected double applyTax(double salary) {
    return Taxes.getNationalTaxe(salary);
  }
}
