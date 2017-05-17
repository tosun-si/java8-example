package com.tosunsi.example.designpattern.java8.decorator;

import java.util.function.DoubleUnaryOperator;
import java.util.stream.Stream;

/**
 * Created by Mazlum on 18/08/2016.
 */
public enum StreamDecorator {

  // Single instance.
  INSTANCE;

  public double calculateProfit(final double turnover, final DoubleUnaryOperator... operators) {

    Stream.of(operators).reduce((ope1, ope2) -> ope1.andThen(ope2)).get()
            .applyAsDouble(turnover);

    return Stream.of(operators).reduce(DoubleUnaryOperator.identity(), DoubleUnaryOperator::andThen)
        .applyAsDouble(turnover);
  }
}
