package com.modern.java.chap3;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {

    public LocalDate calculateExpiryDate(PayData payData) {
        int addedMonths = payData.getPayAmount()/10_000;
        if(payData.getFirstBillingDate() != null){
            LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
            if(payData.getFirstBillingDate().getDayOfMonth() !=
            candidateExp.getDayOfMonth()){
                final int dayLenOfCandiMon =
                        YearMonth.from(candidateExp).lengthOfMonth();
            // 첫 납부일과 이후 후보 납부 일자가 맞지 않는 경우
                if(dayLenOfCandiMon <
                payData.getFirstBillingDate().getDayOfMonth()){
            // 2만원 이상의 금액을 납부했을 경우 첫 납부일과 만료일자가 다른 경우
                    return candidateExp.withDayOfMonth(dayLenOfCandiMon);
                }
                return candidateExp.withDayOfMonth(
                        payData.getFirstBillingDate().getDayOfMonth());
            }
        }
        return payData.getBillingDate().plusMonths(addedMonths);
    }

}
