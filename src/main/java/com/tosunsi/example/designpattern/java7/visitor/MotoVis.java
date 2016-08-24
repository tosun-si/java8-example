package com.tosunsi.example.designpattern.java7.visitor;

/**
 * Created by Mazlum on 24/08/2016.
 */
public class MotoVis implements VehicleVis {
  @Override
  public <R> R accept(IVisitor<? extends R> visitor) {
    return visitor.visitMoto(this);
  }
}
