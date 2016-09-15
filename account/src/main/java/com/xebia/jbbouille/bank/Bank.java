package com.xebia.jbbouille.bank;

import com.xebia.jbbouille.account.Account;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static java.math.BigDecimal.ZERO;

public class Bank {
    public final String name;
    private Map<String, Account> accounts;

    public Bank(String name) {
        this.accounts = new HashMap<>();
        this.name = name;
    }

    public Account account(String accountOwner) {
        return accounts.get(accountOwner);
    }

    public Account createAccount(String accountOwner, BigDecimal minimumAccount) {
        Account account = Account.create(accountOwner, minimumAccount);
        accounts.put(accountOwner, account);
        return account;
    }

    public Account createAccount(String accountOwner) {
        return createAccount(accountOwner, ZERO);
    }
}
