package com.tosunsi.example.designpattern.java8.decorator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * Allows to test revisited GOF decorator pattern with lambda.
 * 
 * Created by Mazlum on 03/08/2016.
 */
public class DecoratorJava8Test {

  @Test
  public void testDecoratorWithAllElements() {

    // Test data.
    final double grossSalary = 30000;

    // Chains all decorators.
    final double netSalary = new DefaultSalaryCalculator().andThen(Taxes::getHealthInsuranceTax)
        .andThen(Taxes::getNationalTax).andThen(Taxes::getRegionalTax).applyAsDouble(grossSalary);

    // Asserts.
    assertThat(netSalary).isNotNull().isEqualTo(1550);
  }

  @Test
  public void testDecoratorWithoutRegionalTaxe() {

    // Test data.
    final double grossSalary = 30000;

    // Chains all decorators.
    final double netSalary = new DefaultSalaryCalculator().andThen(Taxes::getHealthInsuranceTax)
        .andThen(Taxes::getNationalTax).applyAsDouble(grossSalary);

    // Asserts.
    assertThat(netSalary).isNotNull().isEqualTo(1650);
  }

  @Test
  public void testStreamDecoratorWithAllElements() {

    // Test data.
    final double grossSalary = 30000;

    // Chains all decorators.
    final double netSalary =
        StreamDecorator.INSTANCE.calculateSalary(grossSalary, new DefaultSalaryCalculator(),
            Taxes::getHealthInsuranceTax, Taxes::getNationalTax, Taxes::getRegionalTax);

    // Asserts.
    assertThat(netSalary).isNotNull().isEqualTo(1550);
  }

  @Test
  public void testStreamDecoratorWithoutRegionalTaxElements() {

    // Test data.
    final double grossSalary = 30000;

    // Chains all decorators.
    final double netSalary = StreamDecorator.INSTANCE.calculateSalary(grossSalary,
        new DefaultSalaryCalculator(), Taxes::getHealthInsuranceTax, Taxes::getNationalTax);

    // Asserts.
    assertThat(netSalary).isNotNull().isEqualTo(1650);
  }
}
