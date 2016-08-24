package com.tosunsi.example.designpattern.java7.visitor;

/**
 * Created by Mazlum on 24/08/2016.
 */
public class Visitor implements IVisitor {
  @Override
  public String visitMoto(MotoVis moto) {
    return "moto";
  }

  @Override
  public String visitCar(CarVis car) {
    return "car";
  }
}
