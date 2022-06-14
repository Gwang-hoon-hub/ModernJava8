package com.modern.java.chap3;

import java.time.LocalDate;
public class ExpiryDateCalculator {

    public LocalDate calculateExpiryDate(PayData payData) {
        int addedMonths = payData.getPayAmount() == 100_000 ? 12 : payData.getPayAmount() / 10_000;
        if(payData.getFirstBillingDate() != null){
            return expiryDateUsingFirstBillingDate(payData, addedMonths);
        } else{
            return payData.getBillingDate().plusMonths(addedMonths);
        }
    }

    private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonths){
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
        if(!isSameDayMonth(payData.getFirstBillingDate(), candidateExp)){
            final int dayLenOfCandiMon = lastDayOfMonth(candidateExp);
            final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
            // 첫 납부일과 이후 후보 납부 일자가 맞지 않는 경우
            if(dayLenOfCandiMon < dayOfFirstBilling){
                // 2만원 이상의 금액을 납부했을 경우 첫 납부일과 만료일자가 다른 경우
                return candidateExp.withDayOfMonth(dayLenOfCandiMon);
            }
            return candidateExp.withDayOfMonth(dayOfFirstBilling);
        } else {
            return candidateExp;
        }
    }

    private boolean isSameDayMonth(LocalDate date1, LocalDate date2){
        if(date1.getDayOfMonth() != date2.getDayOfMonth()) return false;
        else return true;
    }

    private int lastDayOfMonth(LocalDate date){
        return date.lengthOfMonth();
    }

}