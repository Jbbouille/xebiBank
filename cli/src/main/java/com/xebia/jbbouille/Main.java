package com.xebia.jbbouille;


import com.xebia.jbbouille.account.Account;
import com.xebia.jbbouille.bank.Bank;

public class Main {
    public static void main(String[] args) {
        ScanUtils scanUtils = new ScanUtils(System.out, System.in);
        Bank bank = new Bank("XebiBank");

        Cli cli = new Cli(bank, scanUtils, System.out, System.in);

        while (true) {
            cli.start();
            String name = cli.name();
            Account account = cli.account(name);
            boolean connected = true;
            while (connected) {
                int choice = cli.choice();
                switch (choice) {
                    case 1:
                        cli.deposit(account);
                        break;
                    case 2:
                        cli.withdrawal(account);
                        break;
                    case 3:
                        cli.printBalance(account);
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
}
