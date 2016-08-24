package com.tosunsi.example.designpattern.java8.Stream;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Test;

import com.tosunsi.example.pojo.Person;
import com.tosunsi.example.pojo.PersonHelper;
import com.tosunsi.example.pojo.User;

/**
 * Allows to test revisited GOF decorator pattern with lambda.
 * 
 * Created by Mazlum on 03/08/2016.
 */
public class StreamTest {

  @Test
  public void testStream() {

    final List<Person> persons = PersonHelper.INSTANCE.getPersons();

    // Filter.
    persons.stream().filter(p -> p.getAge() < 20).collect(Collectors.toList());

    // Filter + map.
    persons.stream().filter(p -> p.getAge() < 20).map(this::toUser).collect(Collectors.toList());

    // Filter + map + reduce.
    persons.stream().filter(p -> p.getAge() > 20).map(this::toUser).map(User::getAge).reduce(0,
        (u1, u2) -> u1 + u2);

    // Filter + map + max.
    persons.stream().filter(p -> p.getAge() > 20).map(this::toUser)
        .max(Comparator.comparing(User::getAge)).ifPresent(System.out::println);

    // Filter + find first.
    persons.stream().filter(p -> p.getAge() > 20).findFirst().ifPresent(System.out::println);

    // Map from list : persons grouped by age.
    final Map<Integer, List<Person>> personsByAge =
        persons.stream().collect(Collectors.groupingBy(Person::getAge));

    // Partition by civility.
    final Map<Boolean, List<Person>> personsPartionnedByCivility =
        persons.stream().collect(Collectors.partitioningBy(p -> "MR".equals(p.getCivility())));

    // Joining.
    final String personsLastnameStrings =
        persons.stream().map(Person::getFirstName).collect(Collectors.joining(","));

    // Transform map to list.
    final Map<String, Person> personsToName =
        persons.stream().collect(Collectors.toMap(Person::getLastName, Function.identity()));

    // Sort.
    persons.stream().sorted(
        Comparator.comparing(Person::getFirstName).reversed().thenComparing(Person::getAge));

  }

  private User toUser(final Person person) {

    final User user = new User();
    user.setFirstName(person.getFirstName());
    user.setLastName(person.getLastName());
    user.setAge(person.getAge());

    return user;
  }
}
