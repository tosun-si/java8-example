package com.tosunsi.example.designpattern.java8.Action;

import org.junit.Test;

import com.tosunsi.example.designpattern.java8.action.Action;
import com.tosunsi.example.pojo.Person;

/**
 * Allows to test revisited GOF decorator pattern with lambda.
 *
 * Created by Mazlum on 03/08/2016.
 */
public class ActionTest {

  @Test
  public void testAction() {

    // Test data.
    final Person person1 = new Person("Zlatan", "Ibra", "MR", 10);

    Action.of(person1).add(p -> "Zlatan".equals(p.getFirstName()), this::displayAction1)
        .add(p -> "TOTO".equals(p.getFirstName()), this::displayAction2)
        .add(p -> "LOLO".equals(p.getFirstName()), this::displayAction3).execute();


  }

  private void displayAction1(Person person) {
    System.out.println("action1");
  }

  private void displayAction2(Person person) {
    System.out.println("action2");
  }

  private void displayAction3(Person person) {
    System.out.println("action3");
  }
}
