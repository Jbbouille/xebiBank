package com.xebia.jbbouille;

import com.xebia.jbbouille.account.Account;
import com.xebia.jbbouille.account.CannotWithdrawalException;
import com.xebia.jbbouille.bank.Bank;

import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Scanner;

public class Cli {
    private Scanner sc;
    private Bank bank;
    private ScanUtils scanUtils;
    private PrintStream out;

    public Cli(Bank bank, ScanUtils scanUtils, PrintStream out, InputStream in) {
        this.sc = new Scanner(in);
        this.bank = bank;
        this.scanUtils = scanUtils;
        this.out = out;
    }

    public void start() {
        out.printf("Welcome to %s !!%n", bank.name);
    }

    public String name () {
        out.printf("Please enter your name.%n");
        return sc.nextLine();
    }

    public Account account(String accountOwner) {
        Account account = bank.account(accountOwner);

        if (account == null) {
            out.println("You have no account !");
            out.printf("Creation of account for %s done ! %n", accountOwner);
            account = bank.createAccount(accountOwner);
        }

        out.println(account.actualState());
        return account;
    }

    public int choice() {
        return scanUtils.wantInteger("What do you want to do ? (1) deposit ? (2) withdrawal ? (3) print balance ? (4) disconnect ?");
    }

    public void printBalance(Account account) {
        account.allState().forEach(out::println);
    }

    public void deposit(Account account) {
        BigDecimal amount = scanUtils.wantBigDecimal("Amount of deposit ?");
        account.deposit(amount);
        out.println(account.actualState());
    }

    public void withdrawal(Account account) {
        BigDecimal amount = scanUtils.wantBigDecimal("Amount of withdrawal ?");
        try {
            account.withdrawal(amount);
            out.println(account.actualState());
        } catch (CannotWithdrawalException e) {
            out.println(e.getMessage());
        }
    }
}
