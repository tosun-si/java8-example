package com.tosunsi.example.designpattern.java8.decorator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * Allows to test revisited GOF decorator pattern with lambda.
 * 
 * Created by Mazlum on 03/08/2016.
 */
public class DecoratorJava8Test {

  @Test
  public void givenTurnover_whenComposingAllDecoratorsWithAndThen_thenCorrectResult() {

    // Given.
    final double turnover = 100000;

    // When.
    final double profit = new DefaultProfitCalculator().andThen(Expenses::getOperatingExpenses)
        .andThen(Expenses::getDeductibleTaxes).andThen(Expenses::getRemuneration)
        .andThen(Expenses::getExceptionalExpenses).applyAsDouble(turnover);

    // Then.
    assertThat(profit).isNotNull().isEqualTo(32600);
  }

  @Test
  public void givenTurnover_whenNoComposingAllDecoratorsWithAndThen_thenCorrectResult() {

    // Given.
    final double turnover = 100000;

    // When.
    final double profit = new DefaultProfitCalculator().andThen(Expenses::getOperatingExpenses)
        .andThen(Expenses::getDeductibleTaxes).andThen(Expenses::getRemuneration)
        .applyAsDouble(turnover);

    // Then.
    assertThat(profit).isNotNull().isEqualTo(34600);
  }

  @Test
  public void givenTurnover_whenComposingAllDecoratorsWithStream_thenCorrectResult() {

    // Given.
    final double turnover = 100000;

    // When.
    final double profit = StreamDecorator.INSTANCE.calculateProfit(turnover,
        new DefaultProfitCalculator(), Expenses::getOperatingExpenses, Expenses::getDeductibleTaxes,
        Expenses::getRemuneration, Expenses::getExceptionalExpenses);

    // Then.
    assertThat(profit).isNotNull().isEqualTo(32600);
  }

  @Test
  public void givenTurnover_whenNoComposingAllDecoratorsWithStream_thenCorrectResult() {

    // Given.
    final double turnover = 100000;

    // When.
    final double profit = StreamDecorator.INSTANCE.calculateProfit(turnover,
        new DefaultProfitCalculator(), Expenses::getOperatingExpenses, Expenses::getDeductibleTaxes,
        Expenses::getRemuneration);

    // Then.
    assertThat(profit).isNotNull().isEqualTo(34600);
  }

  @Test
  public void givenTurnover_whenComposingAllDecoratorsWithFluentStyle_thenCorrectResult() {

    // Given.
    final double turnover = 100000;

    // When.
    final double profit = FluentDecorator.from(turnover).with(Expenses::getTransportExpenses)
        .with(Expenses::getOperatingExpenses).with(Expenses::getDeductibleTaxes)
        .with(Expenses::getRemuneration).with(Expenses::getExceptionalExpenses).calculate();

    // Then.
    assertThat(profit).isNotNull().isEqualTo(32600);
  }

  @Test
  public void givenTurnover_whenNoComposingAllDecoratorsWithFluentStyle_thenCorrectResult() {

    // Given.
    final double turnover = 100000;

    // When.
    final double profit = FluentDecorator.from(turnover).with(Expenses::getTransportExpenses)
        .with(Expenses::getOperatingExpenses).with(Expenses::getDeductibleTaxes)
        .with(Expenses::getRemuneration).calculate();

    // Then.
    assertThat(profit).isNotNull().isEqualTo(34600);
  }
}
