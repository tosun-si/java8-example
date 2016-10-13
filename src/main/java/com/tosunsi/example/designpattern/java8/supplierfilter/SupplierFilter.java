package com.tosunsi.example.designpattern.java8.supplierfilter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by Mazlum on 25/08/2016.
 */
public class SupplierFilter<T> {

  private T t;

  private SupplierFilter(T t) {
    this.t = t;
  }

  private Map<Predicate<T>, Supplier<?>> map = new HashMap<>();

  public static <T> SupplierFilter of(T t) {
    return new SupplierFilter<T>(t);
  }

  public <R> SupplierFilter<T> add(final Predicate<T> filter, final Supplier<R> supplier) {
    map.put(filter, supplier);
    return this;
  }

  public <R> R build() {
    return (R) map.entrySet().stream().filter(p -> p.getKey().test(t)).map(Map.Entry::getValue)
        .findFirst().orElseGet(null);
  }
}
