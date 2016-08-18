package com.tosunsi.example.designpattern.java7.decorator;

import com.tosunsi.example.designpattern.java8.decorator.Taxes;

/**
 * Created by Mazlum on 18/08/2016.
 */
public class HealthInsuranceTaxeDecorator extends AbstractTaxDecorator {

  public HealthInsuranceTaxeDecorator(SalaryCalculator salaryCalculator) {
    super(salaryCalculator);
  }

  @Override
  protected double applyTax(double salary) {
    return Taxes.getHealthInsuranceTaxe(salary);
  }
}
