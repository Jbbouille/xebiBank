package com.xebia.jbbouille;


import com.xebia.jbbouille.account.Account;
import com.xebia.jbbouille.account.CannotWithdrawalException;
import com.xebia.jbbouille.bank.Bank;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank("XebiBank");

        while (true) {
            System.out.printf("Welcome to %s !!%n", bank.name);

            System.out.println("Please enter your name.");
            String userName = sc.next();

            Account account = bank.account(userName);

            if (account == null) {
                System.out.println("You have no account !");
                System.out.printf("Creation of account for %s done ! %n", userName);
                account = bank.createAccount(userName);
            }

            System.out.println(account.actualState());

            boolean connected = true;
            while (connected) {
                int choice = ScanUtils.wantInteger("What do you want to do ? (1) deposit ? (2) withdrawal ? (3) print balance ? (4) disconnect ?");
                switch (choice) {
                    case 1:
                        deposit(account);
                        break;
                    case 2:
                        withdrawal(account);
                        break;
                    case 3:
                        printBalance(account);
                        break;
                    case 4:
                        connected = false;
                        break;
                    default:
                        System.out.printf("%d is not a valid number. %n", choice);
                        break;
                }

            }
        }
    }

    private static void printBalance(Account account) {
        account.allState()
                .forEach(System.out::println);
    }

    private static void deposit(Account account) {
        BigDecimal amount = ScanUtils.wantBigDecimal("Amount of deposit ?");
        account.deposit(amount);
        System.out.println(account.actualState());
    }

    private static void withdrawal(Account account) {
        BigDecimal amount = ScanUtils.wantBigDecimal("Amount of withdrawal ?");
        try {
            account.withdrawal(amount);
            System.out.println(account.actualState());
        } catch (CannotWithdrawalException e) {
            System.out.println(e.getMessage());
        }
    }
}
