package com.modern.java.chap2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordStrengthMeterTest {
    private PasswordStrengthMeter meter = new PasswordStrengthMeter();
    private void assertStrength(String password, PasswordStrength expStr){
        PasswordStrength result = meter.meter(password);
        assertEquals(expStr, result);
    }


    @Test
    void meetsAllCriteria_Then_Strong(){
        PasswordStrength result = meter.meter("asdASD1@#");
        assertEquals(PasswordStrength.STRONG, result);
        PasswordStrength result2 = meter.meter("asd1@#ASD");
        assertEquals(PasswordStrength.STRONG, result2);
    }

    @Test
    void refactoring_MeetsAllCriteria_Then_String(){
        assertStrength("asdASD123!@#", PasswordStrength.STRONG);
        assertStrength("asd1@#ASD", PasswordStrength.STRONG);
    }

    @Test
    void meetsOtherCriteria_excect_for_Length_Then_Normal(){
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("as!@12AS");
        assertEquals(PasswordStrength.NORMAL, result);
    }

    @Test
    void meetsOtherCriteria_except_for_number_Then_Normal(){
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("ab!@#AS!@#");
        assertEquals(PasswordStrength.NORMAL, result);
    }

    @Test
    void nullInput_Then_Invalid(){
        assertStrength(null, PasswordStrength.INVALID);
    }


}
