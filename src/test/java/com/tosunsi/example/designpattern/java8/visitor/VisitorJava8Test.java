package com.tosunsi.example.designpattern.java8.visitor;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.tosunsi.example.pojo.Car;
import com.tosunsi.example.pojo.Moto;

/**
 * Allows to test revisited GOF decorator pattern with lambda.
 * 
 * Created by Mazlum on 03/08/2016.
 */
public class VisitorJava8Test {

  @Test
  public void testVisitorWithLambda() {

    final Car car = new Car();
    final String resultVisitor1 = Visitor.<String>builder().when(Car.class, c -> "car")
        .when(Moto.class, m -> "moto").call(car);

    // Assert.
    assertThat(resultVisitor1).isNotNull().isEqualTo("car");

    final Moto moto = new Moto();
    final String resultVisitor2 = Visitor.<String>builder().when(Car.class, c -> "car")
        .when(Moto.class, m -> "moto").call(moto);

    // Assert.
    assertThat(resultVisitor2).isNotNull().isEqualTo("moto");
  }
}
