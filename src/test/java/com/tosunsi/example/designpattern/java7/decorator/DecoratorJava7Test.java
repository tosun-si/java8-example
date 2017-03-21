package com.tosunsi.example.designpattern.java7.decorator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * Allows to test GOF decorator pattern.
 * 
 * Created by Mazlum on 03/08/2016.
 */
public class DecoratorJava7Test {

  @Test
  public void givenTurnover_whenComposingAllDecorators_thenCorrectResult() {

    // Given.
    final double turnover = 100000;

    // When.
    final double profit =
        new ExceptionalExpensesDecorator(new RemunerationDecorator(new DeductibleTaxesDecorator(
            new OperatingExpensesDecorator(new DefaultProfitCalculator())))).calculate(turnover);

    // Then.
    assertThat(profit).isNotNull().isEqualTo(32600);
  }

  @Test
  public void givenTurnover_whenNoComposingAllDecorators_thenCorrectResult() {

    // Given.
    final double turnover = 100000;

    // When.
    final double profit = new RemunerationDecorator(
        new DeductibleTaxesDecorator(new OperatingExpensesDecorator(new DefaultProfitCalculator())))
            .calculate(turnover);

    // Then.
    assertThat(profit).isNotNull().isEqualTo(34600);
  }
}
