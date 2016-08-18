package com.tosunsi.example.designpattern.java7.decorator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * Allows to test revisited GOF decorator pattern with lambda.
 * 
 * Created by Mazlum on 03/08/2016.
 */
public class DecoratorJava7Test {

  @Test
  public void testDecoratorWithAllElements() {

    // Test data.
    final double grossSalary = 30000;

    // Chains all decorators.
    final double netSalary = new HealthInsuranceTaxeDecorator(
        new RegionalTaxDecorator(new NationalTaxDecorator(new DefaultSalaryCalculator())))
            .calculate(grossSalary);

    // Asserts.
    assertThat(netSalary).isNotNull().isEqualTo(1550);
  }

  @Test
  public void testDecoratorWithoutRegionalTaxe() {

    // Test data.
    final double grossSalary = 30000;

    // Chains all decorators.
    // Chains all decorators.
    final double netSalary =
        new HealthInsuranceTaxeDecorator(new NationalTaxDecorator(new DefaultSalaryCalculator()))
            .calculate(grossSalary);

    // Asserts.
    assertThat(netSalary).isNotNull().isEqualTo(1650);
  }
}
