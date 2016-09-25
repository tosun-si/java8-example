package com.tosunsi.example.designpattern.java8.Stream;

import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
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
    final List<Person> filteredPersons =
        persons.stream().filter(p -> p.getAge() < 20).collect(Collectors.toList());
    System.out.println("Stream filter : " + filteredPersons);

    // Filter + map.
    final List<User> transformedList = persons.stream().filter(p -> p.getAge() < 20)
        .map(this::toUser).collect(Collectors.toList());
    System.out.println("Stream transform : " + transformedList);

    // Filter + map + reduce.
    final int maxWithReduce = persons.stream().filter(p -> p.getAge() > 20).map(this::toUser)
        .map(User::getAge).reduce(0, (u1, u2) -> u1 + u2);
    System.out.println("Stream max age with map filter reduce : " + maxWithReduce);

    // Filter + map + max.
    persons.stream().filter(p -> p.getAge() > 20).map(this::toUser)
        .max(Comparator.comparing(User::getAge)).ifPresent(System.out::println);

    persons.stream().map(this::toUser).max((p1, p2) -> p1.getAge().compareTo(p2.getAge()));

    // Filter + find first.
    persons.stream().filter(p -> p.getAge() > 20).findFirst().ifPresent(System.out::println);

    // Map from list : persons grouped by age.
    final Map<Integer, List<Person>> personsByAge =
        persons.stream().collect(groupingBy(Person::getAge));
    System.out.println("Stream grouped list with collector " + personsByAge);

    // Map of map from list, via downstream collector.
    final Map<String, Map<Integer, List<Person>>> personsByAgeAndCivility =
        persons.stream().collect(groupingBy(Person::getCivility, groupingBy(Person::getAge)));
    System.out.println(
        "Stream grouped list with 2 levels and downstream collector " + personsByAgeAndCivility);

    // Partition by civility.
    final Map<Boolean, List<Person>> personsPartionnedByCivility =
        persons.stream().collect(Collectors.partitioningBy(p -> "MR".equals(p.getCivility())));
    System.out.println("Stream partition by : " + personsPartionnedByCivility);

    // Joining.reduce
    final String personsLastnameStrings =
        persons.stream().map(Person::getFirstName).collect(Collectors.joining(","));
    System.out.println("Stream list to string with separator : " + personsLastnameStrings);

    // Transform map to list.
    final Map<String, Person> personsToName = persons.parallelStream()
        .collect(Collectors.toMap(Person::getLastName, Function.identity()));
    System.out.println("Stream list to map : " + personsToName);

    // Sort.
    final List<Person> sortedList = persons.stream()
        .sorted(Comparator.comparing(Person::getFirstName).reversed().thenComparing(Person::getAge))
        .collect(Collectors.toList());
    System.out.println("Stream sorted list : " + sortedList);
  }

  private User toUser(final Person person) {

    final User user = new User();
    user.setFirstName(person.getFirstName());
    user.setLastName(person.getLastName());
    user.setAge(person.getAge());

    return user;
  }


  private Map<Integer, List<Person>> groupedListJava7(final List<Person> persons) {

    final Map<Integer, List<Person>> groupedList = new HashMap<>();

    for (Person person : persons) {

      List<Person> currentPersons = groupedList.get(person.getAge());

      if (currentPersons == null) {
        currentPersons = new ArrayList<>();
        groupedList.put(person.getAge(), currentPersons);
      }

      currentPersons.add(person);
    }

    return groupedList;
  }
}
