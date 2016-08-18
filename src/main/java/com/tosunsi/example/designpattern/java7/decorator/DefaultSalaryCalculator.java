package com.tosunsi.example.designpattern.java7.decorator;

import com.tosunsi.example.designpattern.java8.decorator.Taxes;

/**
 * Created by Mazlum on 18/08/2016.
 */
public class DefaultSalaryCalculator implements SalaryCalculator {

  @Override
  public double calculate(double grossAnnual) {
    return Taxes.getDefaultTaxe(grossAnnual);
  }
}
