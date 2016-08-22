package com.tosunsi.example.fluentiterable;

import org.junit.Test;

import com.tosunsi.example.pojo.PersonHelper;

/**
 * Allows to test Optionals.
 *
 * Created by Mazlum on 02/08/2016.
 */
public class FluentIterableTest {

  @Test
  public void testFluentIterable() {

    FluentIterable.from(PersonHelper.INSTANCE.getPersons()).filter(p -> p.getAge() > 20).toList();
  }
}
