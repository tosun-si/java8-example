package com.tosunsi.example.pojo;

import java.util.List;

import com.google.common.collect.ImmutableList;

/**
 * Created by Mazlum on 23/08/2016.
 */
public enum PersonHelper {

  INSTANCE;

  public List<Person> getPersons() {

    final Person person1 = new Person("Zlatan", "Ibra", "MR", 34);
    final Person person2 = new Person("David", "Beckham", "MR", 36);
    final Person person3 = new Person("Javier", "Pastore", "MR", 36);
    final Person person4 = new Person("Roronoa", "Zorro", "MR", 19);
    final Person person5 = new Person("Jenifer", "Lopez", "MME", 40);

    return new ImmutableList.Builder<Person>().add(person1, person2, person3, person4).build();
  }
}
