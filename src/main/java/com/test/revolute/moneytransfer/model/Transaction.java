package com.test.revolute.moneytransfer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Transaction {

    @JsonProperty(required = true)
    private BigDecimal amount;

    @JsonProperty(required = true)
    private Long fromAccountId;

    @JsonProperty(required = true)
    private Long toAccountId;

    public Transaction() {
    }

    public Transaction(BigDecimal amount, Long fromAccountId, Long toAccountId) {
        this.amount = amount;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Long getFromAccountId() {
        return fromAccountId;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Transaction that = (Transaction) o;

        if (!amount.equals(that.amount))
            return false;
        if (!fromAccountId.equals(that.fromAccountId))
            return false;
        return toAccountId.equals(that.toAccountId);

    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + amount.hashCode();
        result = 31 * result + fromAccountId.hashCode();
        result = 31 * result + toAccountId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Transaction{" +  '\'' + ", amount=" + amount + ", fromAccountId="
                + fromAccountId + ", toAccountId=" + toAccountId + '}';
    }

}

