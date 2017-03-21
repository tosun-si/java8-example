package com.tosunsi.example.designpattern.java8.decorator;

/**
 * Garbage class that allows to returns useful methods.
 * 
 * Created by Mazlum on 03/08/2016.
 */
public class Expenses {

  public static double getTransportExpenses(final double turnover) {
    return turnover - 2400;
  }

  public static double getOperatingExpenses(final double turnover) {
    return turnover - 15000;
  }

  public static double getDeductibleTaxes(final double turnover) {
    return turnover - 3000;
  }

  public static double getRemuneration(final double turnover) {
    return turnover - 45000;
  }

  public static double getExceptionalExpenses(final double turnover) {
    return turnover - 2000;
  }
}
