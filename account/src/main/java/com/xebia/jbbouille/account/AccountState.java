package com.xebia.jbbouille.account;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class AccountState {
    public final BigDecimal amount;
    public final BigDecimal balance;
    public final Date date;

    public AccountState(BigDecimal amount, BigDecimal balance) {
        this.amount = amount;
        this.balance = balance;

        date = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountState that = (AccountState) o;
        return Objects.equals(amount, that.amount) &&
                Objects.equals(balance, that.balance) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, balance, date);
    }

    @Override
    public String toString() {
        return "AccountState{" +
                "amount=" + amount +
                ", balance=" + balance +
                ", date=" + date +
                '}';
    }
}
