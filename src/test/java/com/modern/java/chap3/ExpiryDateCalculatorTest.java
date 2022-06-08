package com.modern.java.chap3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ExpiryDateCalculatorTest {
    /*
    * 서비스를 사용하려면 매달 1만 원을 선불로 납부 -> 납부일 기준으로 한 달 뒤가 서비스 만료일이 된다.
    * 2개월 이상 요금을 납부할 수 있다.
    * 10만 원을 납부하면 서비스를 1년 제공한다.
    * */

    // 쉬운것 부터 테스트

    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨(){

        assertExpiryDate(
                LocalDate.of(2019,3,1), 10_000,
                LocalDate.of(2019, 4, 1)
        );
        assertExpiryDate(
                LocalDate.of(2019, 5, 5), 10_000,
                LocalDate.of(2019, 6, 5)
        );

    }

    private void assertExpiryDate(LocalDate billingDate,
                                  int payAmount, LocalDate expectedExpiryDate){
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(billingDate, payAmount);
        assertEquals(expectedExpiryDate, realExpiryDate);
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음(){
        assertExpiryDate(
                LocalDate.of(2019,1,31), 10_000,
                LocalDate.of(2019, 2, 28)
        );
        assertExpiryDate(
                LocalDate.of(2019,5,31), 10_000,
                LocalDate.of(2019, 6, 30)
        );
    }
}
