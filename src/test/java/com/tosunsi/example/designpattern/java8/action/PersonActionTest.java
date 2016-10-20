package com.tosunsi.example.designpattern.java8.action;

import java.util.function.Consumer;

import org.junit.Test;

import com.tosunsi.example.pojo.Person;

/**
 * Allows to test a design that are based on action (consumer by types).
 *
 * Created by Mazlum on 03/08/2016.
 */
public class PersonActionTest {

  @Test
  public void testAction() {

    // Creates a new person object for test.
    final Person person = new Person("Zlatan", "Ibrahimovic", "MR", 34);

    // Example with lambdas.
    System.out.println("Example with multiple lambdas : ");
    PersonAction.of(person).add(Direction.UP, p -> System.out.println(getMessage(p, "up")))
        .add(Direction.DOWN, p -> System.out.println(getMessage(p, "down")))
        .add(Direction.LEFT, p -> System.out.println(getMessage(p, "left")))
        .add(Direction.RIGHT, p -> System.out.println(getMessage(p, "right")))
        .execute(Direction.UP);

    // Example with common lambda.
    System.out.println("Example with a common lambda : ");
    PersonAction.of(person).add(Direction.UP, getAction("up"))
        .add(Direction.DOWN, getAction("down")).add(Direction.LEFT, getAction("left"))
        .add(Direction.RIGHT, getAction("right")).execute(Direction.RIGHT);

    // Example with method reference.
    System.out.println("Example with methods reference : ");
    PersonAction.of(person).add(Direction.UP, this::displayUpAction)
        .add(Direction.DOWN, this::displayDownAction).add(Direction.LEFT, this::displayLeftAction)
        .add(Direction.RIGHT, this::displayRightAction).execute(Direction.LEFT);
  }

  /**
   * Allows to get a common lambda represented by a consumer, from the given direction.
   * 
   * @param direction current direction
   * @return Consumer that represent the action
   */
  private Consumer<Person> getAction(final String direction) {
    return p -> System.out.println(getMessage(p, direction));
  }

  /**
   * Display up action of the given {@link Person}.
   * 
   * @param person person
   */
  private void displayUpAction(final Person person) {
    System.out.println(getMessage(person, "up"));
  }

  /**
   * Display down action of the given {@link Person}.
   *
   * @param person person
   */
  private void displayDownAction(final Person person) {
    System.out.println(getMessage(person, "down"));
  }

  /**
   * Display left action of the given {@link Person}.
   *
   * @param person person
   */
  private void displayLeftAction(final Person person) {
    System.out.println(getMessage(person, "left"));
  }

  /**
   * Display right action of the given {@link Person}.
   *
   * @param person person
   */
  private void displayRightAction(final Person person) {
    System.out.println(getMessage(person, "right"));
  }

  /**
   * Factory method that allows to build and get an info message for the person direction.
   * 
   * @param person person
   * @param direction direction
   * @return String an info message for the person direction
   */
  private String getMessage(final Person person, final String direction) {
    return new StringBuilder(person.getFirstName()).append(" ").append(person.getLastName())
        .append(" go to ").append(direction).append("\n").toString();
  }
}
