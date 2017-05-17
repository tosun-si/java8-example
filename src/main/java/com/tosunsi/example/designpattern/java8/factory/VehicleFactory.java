package com.tosunsi.example.designpattern.java8.factory;

import java.util.Map;
import java.util.function.Supplier;

import com.google.common.collect.Maps;

import com.tosunsi.example.pojo.Vehicle;

/**
 * Revisits GOF factory design pattern.
 * 
 * Created by Mazlum on 24/08/2016.
 */
public final class VehicleFactory {

  private Map<String, Supplier<Vehicle>> vehicles = Maps.newHashMap();

  private VehicleFactory() {}

  public static VehicleFactory builder() {
    return new VehicleFactory();
  }

  public VehicleFactory register(final String type, final Supplier<Vehicle> vehicle) {
    vehicles.put(type, vehicle);
    return this;
  }

  public Vehicle create(final String type) {

    // Java 7 get in map.
    final Supplier<Vehicle> vehicleSupplier = vehicles.get(type);
    if (vehicleSupplier == null) {
      throw new IllegalStateException("Invalid type : " + type);
    }

    // Java 8 get in map.
    final Supplier<Vehicle> vehicleSupplierJava8 = vehicles.getOrDefault(type, () -> {
      throw new IllegalStateException("Invalid type : " + type);
    });

    return vehicleSupplierJava8.get();
  }
}
