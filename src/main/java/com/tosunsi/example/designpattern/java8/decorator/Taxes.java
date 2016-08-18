package com.tosunsi.example.designpattern.java8.decorator;

/**
 * Garbage class that allows to returns useful methods.
 * 
 * Created by Mazlum on 03/08/2016.
 */
public class Taxes {

  public static double getDefaultTax(final double salary) {
    return salary / 12;
  }

  public static double getHealthInsuranceTax(final double salary) {
    return salary - 550;
  }

  public static double getNationalTax(final double salary) {
    return salary - 300;
  }

  public static double getRegionalTax(final double salary) {
    return salary - 100;
  }
}
