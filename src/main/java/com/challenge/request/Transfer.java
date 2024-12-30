package com.challenge.request;


import java.math.BigDecimal;


public class Transfer {
    private BigDecimal value;
    private Long payer;
    private Long payee;

    public Transfer(BigDecimal value, Long payer, Long payee) {
        this.value = value;
        this.payer = payer;
        this.payee = payee;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Long getPayer() {
        return payer;
    }

    public void setPayer(Long payer) {
        this.payer = payer;
    }

    public Long getPayee() {
        return payee;
    }

    public void setPayee(Long payee) {
        this.payee = payee;
    }
}
