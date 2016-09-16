package com.xebia.jbbouille;

import com.xebia.jbbouille.account.Account;
import com.xebia.jbbouille.bank.Bank;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

public class CliTest {
    private final ByteArrayOutputStream outTest = new ByteArrayOutputStream();
    private final PrintStream out = new PrintStream(outTest);
    private final InputStream in = new ByteArrayInputStream("bob".getBytes());

    @Test
    public void should_print_start_message() throws Exception {
        // Given
        ScanUtils scanUtils = new ScanUtils(out, in);
        Bank bank = new Bank("Bank");

        Cli cli = new Cli(bank, scanUtils, out, in);

        // When
        cli.start();

        // Then
        assertThat(outTest.toString()).startsWith("Welcome to Bank !!");
    }

    @Test
    public void should_print_name_message() throws Exception {
        // Given
        ScanUtils scanUtils = new ScanUtils(out, in);
        Bank bank = new Bank("Bank");

        Cli cli = new Cli(bank, scanUtils, out, in);

        // When
        cli.name();

        // Then
        assertThat(outTest.toString()).startsWith("Please enter your name.");
    }

    @Test
    public void should_ask_for_name() throws Exception {
        // Given
        ScanUtils scanUtils = new ScanUtils(out, in);
        Bank bank = new Bank("Bank");

        Cli cli = new Cli(bank, scanUtils, out, in);

        // When
        String name = cli.name();

        // Then
        assertThat(name).isEqualTo("bob");
    }

    @Test
    public void should_create_an_account_if_no_account() throws Exception {
        // Given
        ScanUtils scanUtils = new ScanUtils(out, in);
        Bank bank = new Bank("Bank");

        Cli cli = new Cli(bank, scanUtils, out, in);

        // When
        Account bob = cli.account("bob");

        // Then
        assertThat(bob.actualState().balance).isEqualTo(ZERO);
    }

    @Test
    public void should_retrieve_an_account_if_existing() throws Exception {
        // Given
        ScanUtils scanUtils = new ScanUtils(out, in);
        Bank bank = new Bank("Bank");

        Cli cli = new Cli(bank, scanUtils, out, in);
        Account previouslyCreatedBobAccount = cli.account("bob");
        Account previouslyCreatedTedAccount = cli.account("Ted");

        // When
        Account bob = cli.account("bob");

        // Then
        assertThat(previouslyCreatedBobAccount).isEqualTo(bob);
        assertThat(previouslyCreatedTedAccount).isNotEqualTo(bob);
    }

    @Test
    public void should_ask_for_an_action() throws Exception {
        // Given
        InputStream in = new ByteArrayInputStream("1".getBytes());
        ScanUtils scanUtils = new ScanUtils(out, in);
        Bank bank = new Bank("Bank");

        Cli cli = new Cli(bank, scanUtils, out, in);

        // When
        int choice = cli.choice();

        // Then
        assertThat(choice).isEqualTo(1);
        assertThat(outTest.toString()).startsWith("What do you want to do ? (1) deposit ? (2) withdrawal ? (3) print balance ? (4) disconnect ?");
    }

    @Test
    public void should_print_all_balance() throws Exception {
        // Given
        ScanUtils scanUtils = new ScanUtils(out, in);
        Bank bank = new Bank("Bank");

        Cli cli = new Cli(bank, scanUtils, out, in);
        Account previouslyCreatedBobAccount = cli.account("bob");
        previouslyCreatedBobAccount.deposit(TEN);

        // When
        cli.printBalance(previouslyCreatedBobAccount);

        // Then
        assertThat(outTest.toString()).contains("AccountState{amount=0, balance=0, date=", "AccountState{amount=10, balance=10, date=");
    }

    @Test
    public void should_print_deposit() throws Exception {
        // Given
        InputStream in = new ByteArrayInputStream("1".getBytes());

        ScanUtils scanUtils = new ScanUtils(out, in);
        Bank bank = new Bank("Bank");

        Cli cli = new Cli(bank, scanUtils, out, in);
        Account previouslyCreatedBobAccount = cli.account("bob");

        // When
        cli.deposit(previouslyCreatedBobAccount);

        // Then
        assertThat(outTest.toString()).contains("Amount of deposit ?");
    }

    @Test
    public void should_print_withdrawal() throws Exception {
        // Given
        InputStream in = new ByteArrayInputStream("1".getBytes());

        ScanUtils scanUtils = new ScanUtils(out, in);
        Bank bank = new Bank("Bank");

        Cli cli = new Cli(bank, scanUtils, out, in);
        Account previouslyCreatedBobAccount = cli.account("bob");

        // When
        cli.withdrawal(previouslyCreatedBobAccount);

        // Then
        assertThat(outTest.toString()).contains("Amount of withdrawal ?");
    }
}