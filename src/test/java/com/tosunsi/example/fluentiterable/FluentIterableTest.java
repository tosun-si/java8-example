package com.tosunsi.example.fluentiterable;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import com.tosunsi.example.pojo.Person;
import com.tosunsi.example.pojo.PersonHelper;
import com.tosunsi.example.pojo.User;

/**
 * Allows to test Optionals.
 *
 * Created by Mazlum on 02/08/2016.
 */
public class FluentIterableTest {

  @Test
  public void testFluentIterable() {

    final List<User> users = FluentIterable.from(PersonHelper.INSTANCE.getPersons())
        .filter(p -> p.getAge() < 20).map(this::toUser).toList();

    // Asserts.
    assertThat(users).isNotNull().isNotEmpty().hasSize(1);
  }

  /**
   * Allows to transform person object to user object.
   * 
   * @param person current person
   * @return User user
   */
  private User toUser(final Person person) {

    final User user = new User();
    user.setFirstName(person.getFirstName());
    user.setLastName(person.getLastName());
    user.setAge(person.getAge());

    return user;
  }
}
