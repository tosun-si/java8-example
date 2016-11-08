package com.tosunsi.example.tuple;

import java.util.Objects;

/**
 * Tuple that contains 3 attributes.
 * 
 * Created by Mazlum on 30/10/2016.
 */
public final class Tuple3<T1, T2, T3> {

  // Fields.
  private final T1 t1;
  private final T2 t2;
  private final T3 t3;

  /**
   * Constructor with 3 values.
   * 
   * @param t1 value 1
   * @param t2 value 2
   * @param t3 value 3
   */
  private Tuple3(T1 t1, T2 t2, T3 t3) {
    this.t1 = t1;
    this.t2 = t2;
    this.t3 = t3;
  }

  /**
   * Static factory method that allows to create a new {@link Tuple3} by the given values.
   * 
   * @param t1 value 1
   * @param t2 value 2
   * @param t3 value 3
   * @param <T1> value 1 type
   * @param <T2> value 2 type
   * @param <T3> value 3 type
   * @return a new {@link Tuple3}
   */
  public static <T1, T2, T3> Tuple3<T1, T2, T3> of(final T1 t1, final T2 t2, final T3 t3) {
    Objects.nonNull(t1);
    Objects.nonNull(t2);
    Objects.nonNull(t3);
    return new Tuple3<>(t1, t2, t3);
  }

  public T1 _1() {
    return t1;
  }

  public T2 _2() {
    return t2;
  }

  public T3 _3() {
    return t3;
  }
}
