package com.modern.java.chap2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test
    void _더하기테스트(){
        int result = Calculator.plus(1, 2);
        assertEquals(3, result);
        assertEquals(6, Calculator.plus(3,3));
    }
}
