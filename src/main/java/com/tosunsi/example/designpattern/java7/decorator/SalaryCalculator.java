package com.tosunsi.example.designpattern.java7.decorator;

/**
 * Created by Mazlum on 18/08/2016.
 */
public interface SalaryCalculator {

  /**
   * Calculates salary by gross annual.
   * 
   * @param grossAnnual gross annual
   * @return result salary
   */
  double calculate(double grossAnnual);
}
