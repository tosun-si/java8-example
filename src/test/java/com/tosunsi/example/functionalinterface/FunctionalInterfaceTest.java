package com.tosunsi.example.functionalinterface;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * Allows to test Optionals.
 *
 * Created by Mazlum on 02/08/2016.
 */
public class FunctionalInterfaceTest {

    @Test
    public void testWithAnonymousClass() {

        // Sum operation.
        final Operation sum = new Operation() {
            @Override
            public int apply(int value1, int value2) {
                return value1 + value2;
            }
        };

        final int resultSum = sum.apply(5, 6);

        // Asserts.
        assertThat(resultSum).isNotNull().isEqualTo(11);

        // Sum operation.
        final Operation mul = new Operation() {
            @Override
            public int apply(int value1, int value2) {
                return value1 * value2;
            }
        };

        final int resultMul = mul.apply(3, 4);

        // Asserts.
        assertThat(resultMul).isNotNull().isEqualTo(12);
    }

    @Test
    public void testWithStaticClass() {

        // Sum with static class.
        final int sum = new Sum().apply(8, 2);

        // Asserts.
        assertThat(sum).isNotNull().isEqualTo(10);
    }

    @Test
    public void testWithLambda() {

        // Sum with lambda.
        final Operation sum = (x, y) -> x + y;
        final int resultSum = sum.apply(3, 2);

        assertThat(resultSum).isEqualTo(5);

        // Mul operation.
        final Operation mul = (x, y) -> x * y;
        final int resultMul = mul.apply(2, 5);

        assertThat(resultMul).isEqualTo(10);
    }

    @Test
    public void testWithMethodReference() {

        // Sum with method reference.
        final Operation sum = Integer::sum;
        final int resultSum = sum.apply(7, 4);

        assertThat(resultSum).isEqualTo(11);

        // Multiplication with method reference.
        final Operation mul = this::multiply;
        final int resultMul = mul.apply(4, 5);

        assertThat(resultMul).isEqualTo(20);
    }

    /**
     * Nested static class for sum.
     */
    private static class Sum implements Operation {

        @Override
        public int apply(int value1, int value2) {
            return value1 + value2;
        }
    }

    private int multiply(final int value1, final int value2) {
        return value1 * value2;
    }
}
