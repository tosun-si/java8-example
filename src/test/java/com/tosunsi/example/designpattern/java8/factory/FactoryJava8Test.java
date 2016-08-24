package com.tosunsi.example.designpattern.java8.factory;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.tosunsi.example.pojo.Car;
import com.tosunsi.example.pojo.Moto;
import com.tosunsi.example.pojo.Vehicle;

/**
 * Allows to test revisited GOF decorator pattern with lambda.
 * 
 * Created by Mazlum on 03/08/2016.
 */
public class FactoryJava8Test {

  @Test
  public void testFactoryWithLambda() {

    final Vehicle vehicle = VehicleFactory.builder().register("car", () -> new Car())
        .register("moto", () -> new Moto()).create("car");

    // Assert.
    assertThat(vehicle).isInstanceOf(Car.class);
  }

  @Test
  public void testFactoryWithMethodReference() {

    final Vehicle vehicle = VehicleFactory.builder().register("car", Car::new)
        .register("moto", Moto::new).create("moto");

    // Assert.
    assertThat(vehicle).isInstanceOf(Moto.class);
  }
}
