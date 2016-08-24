package com.tosunsi.example.designpattern.java7.factory;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import com.tosunsi.example.pojo.Car;
import com.tosunsi.example.pojo.Moto;
import com.tosunsi.example.pojo.Vehicle;

/**
 * Created by Mazlum on 24/08/2016.
 */
public enum VehicleFactory {

  INSTANCE;

  private static final String CAR_TYPE = "car";
  private static final String MOTO_TYPE = "moto";

  public Vehicle getVehicleWithIfElse(final String type) {

    Vehicle vehicle = null;
    if (CAR_TYPE.equals(type)) {
      vehicle = new Car();
    } else if (MOTO_TYPE.equals(type)) {
      vehicle = new Moto();
    }

    return vehicle;
  }

  public Vehicle getVehicleWithSwitchCase(final String type) {

    final Vehicle vehicle;
    switch (type) {
      case CAR_TYPE:
        vehicle = new Car();
        break;
      case MOTO_TYPE:
        vehicle = new Moto();
        break;
      default:
        vehicle = null;
        break;
    }

    return vehicle;
  }

  public Vehicle getVehicleWithHashMap(final String type) {

    final Map<String, Vehicle> vehicles = new HashMap<>();
    vehicles.put(CAR_TYPE, new Car());
    vehicles.put(MOTO_TYPE, new Moto());

    return vehicles.get(type);
  }

  public Vehicle getVehicleWithFluentHashMap(final String type) {

    return new ImmutableMap.Builder<String, Vehicle>().put(CAR_TYPE, new Car())
        .put(MOTO_TYPE, new Moto()).build().get(type);
  }
}
