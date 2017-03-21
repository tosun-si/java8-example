package com.tosunsi.example.designpattern.java7.decorator;

/**
 * Created by Mazlum on 18/08/2016.
 */
public interface ProfitCalculator {

  /**
   * Calculates profit by turnover.
   * 
   * @param turnover profit
   * @return result profit
   */
  double calculate(double turnover);
}
