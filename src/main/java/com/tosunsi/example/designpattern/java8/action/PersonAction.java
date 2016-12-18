package com.tosunsi.example.designpattern.java8.action;

import java.util.Map;
import java.util.function.Consumer;

import com.google.common.collect.Maps;

import com.tosunsi.example.pojo.Person;

/**
 * Allows to compose some actions (represented by consumers) and associate them with direction
 * types.
 * 
 * Created by Mazlum on 20/10/2016.
 */
public class PersonAction {

  // Fields.
  private Person person;
  private Map<Direction, Consumer<Person>> actions;

  /**
   * Private constructor for prevent instantiation of class.
   */
  private PersonAction(final Person person) {

    // Initializes actions map and person object.
    this.person = person;
    this.actions = Maps.newHashMap();
  }

  /**
   * Static factory method that allows to initializes action process from the given {@link Person}.
   * 
   * @param person person object that actions will be executed
   * @return PersonAction instance of current object
   */
  public static PersonAction of(final Person person) {
    return new PersonAction(person);
  }

  /**
   * Allows to associate a direction with an action and adds it in global actions map.
   * 
   * @param direction current direction
   * @param action current action
   * @return PersonAction instance of current object
   */
  public PersonAction add(final Direction direction, final Consumer<Person> action) {
    actions.put(direction, action);
    return this;
  }

  /**
   * Final method that allows to execute the good action by the given {@link Direction}.<br>
   * A consumer that represents the action is executed with lazy evaluation.
   * 
   * @param direction direction
   */
  public void execute(final Direction direction) {
    actions.getOrDefault(direction, p -> {
      throw new IllegalArgumentException("Unknown action for direction : " + direction);
    }).accept(person);
  }
}
