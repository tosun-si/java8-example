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
    final double netSalary = new DefaultSalaryCalculator().andThen(Taxes::getHealthInsuranceTaxe)
        .andThen(Taxes::getNationalTaxe).andThen(Taxes::getRegionalTaxe).applyAsDouble(grossSalary);

    // Asserts.
    assertThat(netSalary).isNotNull().isEqualTo(1550);
  }

  @Test
  public void testDecoratorWithoutRegionalTaxe() {

    // Test data.
    final double grossSalary = 30000;

    // Chains all decorators.
    final double netSalary = new DefaultSalaryCalculator().andThen(Taxes::getHealthInsuranceTaxe)
        .andThen(Taxes::getNationalTaxe).applyAsDouble(grossSalary);

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
            Taxes::getHealthInsuranceTaxe, Taxes::getNationalTaxe, Taxes::getRegionalTaxe);

    // Asserts.
    assertThat(netSalary).isNotNull().isEqualTo(1550);
  }

  @Test
  public void testStreamDecoratorWithoutRegionalTaxElements() {

    // Test data.
    final double grossSalary = 30000;

    // Chains all decorators.
    final double netSalary = StreamDecorator.INSTANCE.calculateSalary(grossSalary,
        new DefaultSalaryCalculator(), Taxes::getHealthInsuranceTaxe, Taxes::getNationalTaxe);

    // Asserts.
    assertThat(netSalary).isNotNull().isEqualTo(1650);
  }
}
