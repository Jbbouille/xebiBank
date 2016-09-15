package com.xebia.jbbouille.account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.math.BigDecimal.ZERO;

public class Account {
    public final String owner;
    public final BigDecimal accountMinBalance;
    private List<AccountState> states;

    Account(String owner, BigDecimal accountMinBalance) {
        this.owner = owner;
        this.accountMinBalance = accountMinBalance;

        states = new ArrayList<>();
        states.add(new AccountState(ZERO, ZERO));
    }

    public static Account create(String accountOwner, BigDecimal accountMinBalance) {
        return new Account(accountOwner, accountMinBalance);
    }

    public AccountState actualState() {
        return states.get(states.size() - 1);
    }

    public List<AccountState> allState() {
        return states;
    }

    public AccountState deposit(BigDecimal deposit) {
        AccountState lastState = new AccountState(deposit, actualState().balance.add(deposit));
        states.add(lastState);
        return lastState;
    }

    public AccountState withdrawal(BigDecimal withdrawal) throws CannotWithdrawalException {
        if (actualState().balance.subtract(withdrawal).compareTo(accountMinBalance) == -1) {
            throw new CannotWithdrawalException(String.format("You cannot remove %s to your account. Min possible to remove is %s", withdrawal, accountMinBalance.subtract(actualState().balance).abs()));
        }

        AccountState lastState = new AccountState(withdrawal.negate(), actualState().balance.subtract(withdrawal));
        states.add(lastState);
        return lastState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(owner, account.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner);
    }
}
