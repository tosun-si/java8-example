package com.tosunsi.example.designpattern.java8.dispatcher;

import java.util.Map;

import javax.annotation.PostConstruct;

import com.google.common.collect.ImmutableMap;

import com.tosunsi.example.designpattern.java8.action.Direction;
import com.tosunsi.example.pojo.Person;

/**
 * Created by Mazlum on 23/02/2017.
 */
public class TestService {

  private ServiceDispatcher serviceDispatcher;

  @PostConstruct
  private void init() {
    this.serviceDispatcher = ServiceDispatcher.builder()
        .with(Direction.DOWN, this::getFirstName)
        .with(Direction.LEFT, this::getLastName);
  }

  public Map<String, String> getFirstName(final Person person) {
    return new ImmutableMap.Builder<String, String>().put("firstName", person.getFirstName())
        .build();
  }

  public Map<String, String> getLastName(final Person person) {
    return new ImmutableMap.Builder<String, String>().put("lastName", person.getFirstName())
        .build();
  }
}
