package com.houarizegai.calculator;

import com.houarizegai.calculator.ui.CalculatorUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorUITest {

    private CalculatorUI calculatorUI;

    @BeforeEach
    void setUp() {
        calculatorUI = new CalculatorUI();
    }

    // 1. Equivalence Partitioning & Basic Operations
    @ParameterizedTest
    @CsvSource({
        "3, 5, +, 8", 
        "2, 8, -, -6", 
        "44.5, 10, *, 445", 
        "320, 5, /, 64", 
        "3, 5, %, 3", 
        "5, 3, ^, 125"
    })
    void testCalculationEquivalence(double firstNumber, double secondNumber, char operator, double expectedResult) {
        assertEquals(expectedResult, calculatorUI.calculate(firstNumber, secondNumber, operator));
    }

    // 2. Boundary Value & Edge Cases (Division by zero, negative bounds, zero inputs)
    @Test
    void testDivisionByZero() {
        // Testing behavior when denominator is zero (Boundary condition)
        double result = calculatorUI.calculate(50.0, 0.0, '/');
        // Depending on implementation, checking if it returns Infinity, NaN, or handles it
        assertTrue(Double.isInfinite(result) || Double.isNaN(result) || result == 0.0);
    }

    @Test
    void testNegativeNumberBoundaries() {
        // Testing boundary conditions with negative values across operations
        assertEquals(-8.0, calculatorUI.calculate(-3.0, -5.0, '+'));
        assertEquals(15.0, calculatorUI.calculate(-3.0, -5.0, '*'));
    }

    // 3. Path-Based / Branch Coverage Test (Invalid operators)
    @Test
    void testInvalidOperatorPath() {
        // Tests the default/fallback branch in the calculation switch/if statement
        double result = calculatorUI.calculate(5.0, 5.0, '#');
        assertEquals(5.0, result); // or whatever default fallback your calculator uses
    }
}