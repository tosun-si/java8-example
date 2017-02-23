package com.tosunsi.example.designpattern.java8.dispatcher;

import java.util.Map;
import java.util.function.Function;

import com.google.common.collect.Maps;

import com.tosunsi.example.designpattern.java8.action.Direction;
import com.tosunsi.example.pojo.Person;

/**
 * Created by Mazlum on 23/02/2017.
 */
public class ServiceDispatcher {

  private final Map<Direction, Function<Person, Map<String, String>>> functions;

  public ServiceDispatcher() {
    this.functions = Maps.newHashMap();
  }

  public static ServiceDispatcher builder() {
    return new ServiceDispatcher();
  }

  public ServiceDispatcher with(final Direction direction,
      Function<Person, Map<String, String>> function) {
    this.functions.put(direction, function);
    return this;
  }

  public Function<Person, Map<String, String>> invoke(final Direction direction) {
    return functions.getOrDefault(direction, f -> {
      throw new IllegalStateException("");
    });
  }

  public void dispatch(final TestService service) {

    ServiceDispatcher.builder().with(Direction.DOWN, service::getFirstName)
        .with(Direction.LEFT, service::getLastName).invoke(Direction.DOWN);

  }
}
