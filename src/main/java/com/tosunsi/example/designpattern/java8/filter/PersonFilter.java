package com.tosunsi.example.designpattern.java8.filter;

import java.util.Objects;
import java.util.function.Predicate;

import com.tosunsi.example.pojo.Person;

/**
 * Created by Mazlum on 24/11/2016.
 */
public enum PersonFilter {

  INSTANCE;

  private Predicate<Person> notNull() {
    return Objects::nonNull;
  }

  public boolean applyFilters(final Person person) {
    return notNull().and(p -> p.getAge() > 14).and(p -> p.getFirstName().equals("Ibra"))
        .or(p -> p.getLastName().equals("toto")).test(person);
  }
}
