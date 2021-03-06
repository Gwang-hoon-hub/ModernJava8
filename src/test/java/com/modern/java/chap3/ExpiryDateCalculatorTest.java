package com.modern.java.chap3;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ExpiryDateCalculatorTest {
    /*
    * 서비스를 사용하려면 매달 1만 원을 선불로 납부 -> 납부일 기준으로 한 달 뒤가 서비스 만료일이 된다.
    * 2개월 이상 요금을 납부할 수 있다.
    * 10만 원을 납부하면 서비스를 1년 제공한다.
    * */

    // 쉬운것 부터 테스트

    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate){
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(payData);
        assertEquals(expectedExpiryDate, realExpiryDate);
    }

    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨(){

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019,3,1))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019,4,1)
        );
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음(){
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019,4,1)
        );

        assertExpiryDate(
                // 1월 31일 구독을 시작하면, 한 달 뒤인 2월 28일이 된다.
                // 2월은 31일이 없기 때문에 그에 맞는 예외처리를 하는 프로그램을 작성해야 한다.
                PayData.builder()
                        .billingDate(LocalDate.of(2019,1,31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019,2,28)
        );
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를때_만원_납부(){
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2019,1,31))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payAmount(10_000)
                .build();
        assertExpiryDate(payData, LocalDate.of(2019,3,31));

        PayData payData2 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019,1,30))
                .billingDate(LocalDate.of(2019,2,28))
                .payAmount(10_000)
                .build();
        assertExpiryDate(payData2, LocalDate.of(2019, 3, 30));

        PayData payData3 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019,5,31))
                .billingDate(LocalDate.of(2019,6,30))
                .payAmount(10_000)
                .build();
        assertExpiryDate(payData3, LocalDate.of(2019,7,31));
    }

    @Test
    void 이만원_이상_납부하면_비례해서_만료일_계산(){
        assertExpiryDate(PayData.builder()
                .billingDate(LocalDate.of(2019,3,1))
                .payAmount(20_000)
                .build(),
                LocalDate.of(2019,5,1));
    }


    @Test
    void 삼만원_이상_납부하면_비례해서_만료일_계산(){
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019,3,1))
                        .payAmount(30_000)
                        .build(),
                LocalDate.of(2019,6,1)
        );

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019,1,1))
                        .payAmount(50_000)
                        .build(),
                LocalDate.of(2019,6,1)
        );
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를때_이만원_이상_납부(){
        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2019,1,31))
                        .billingDate(LocalDate.of(2019,2,28))
                        .payAmount(20_000)
                        .build(),
                LocalDate.of(2019,4,30)
        );

        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2019,3,31))
                        .billingDate(LocalDate.of(2019,4,30))
                        .payAmount(30_000)
                        .build(),
                LocalDate.of(2019,7,31)

        );
    }

    @Test
    void 십만원을_납부하면_1년_제공(){
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019,1,28))
                        .payAmount(100_000)
                        .build(),
                LocalDate.of(2020,1,28)
        );
    }

    @Test
    void 새로운_테스트_진행(){

    }

}
