package com.tosunsi.example.designpattern.java7.visitor;

/**
 * Created by Mazlum on 24/08/2016.
 */
/**
 * Created by Mazlum on 24/08/2016.
 */
public class CarVis implements VehicleVis {
  @Override
  public <R> R accept(IVisitor<? extends R> visitor) {
    return visitor.visitCar(this);
  }
}
