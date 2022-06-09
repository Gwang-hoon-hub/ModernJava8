package com.modern.java.chap3;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PayData {

    private LocalDate billingDate;
    private int payAmount;

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private PayData data = new PayData();

        public Builder billingDate(LocalDate billingDate){
            data.billingDate = billingDate;
            return this;
        }

        public Builder payAmount(int payAmount){
            data.payAmount = payAmount;
            return this;
        }

        public PayData build(){
            return data;
        }
    }
}
