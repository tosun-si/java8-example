package com.tosunsi.example.designpattern.java7.visitor;

/**
 * Created by Mazlum on 24/08/2016.
 */
public interface VehicleVis {

  <R> R accept(IVisitor<? extends R> visitor);
}
