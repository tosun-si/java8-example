package com.tosunsi.example.designpattern.java7.visitor;

/**
 * Created by Mazlum on 24/08/2016.
 */
public interface IVisitor<R> {

  R visitMoto(MotoVis moto);

  R visitCar(CarVis car);
}
