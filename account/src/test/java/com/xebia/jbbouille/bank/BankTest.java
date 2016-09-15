package com.xebia.jbbouille.bank;

import com.xebia.jbbouille.account.Account;
import org.junit.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

public class BankTest {

    @Test
    public void should_create_an_account_with_default_overdraft_facilities() throws Exception {
        // Given
        Bank bank = new Bank("XebiBank");

        // When
        Account bob = bank.createAccount("bob", ZERO);

        // Then
        assertThat(bob.accountMinBalance).isEqualTo(ZERO);
    }

    @Test
    public void should_create_an_account_with_specific_overdraft_facilities() throws Exception {
        // Given
        Bank bank = new Bank("XebiBank");

        // When
        Account bob = bank.createAccount("bob", new BigDecimal("-100"));

        // Then
        assertThat(bob.accountMinBalance).isEqualTo(new BigDecimal("-100"));
    }

    @Test
    public void should_retrieve_a_account_owner() throws Exception {
        // Given
        Bank bank = new Bank("XebiBank");
        bank.createAccount("bob");
        bank.createAccount("robert");

        // When
        Account bob = bank.account("bob");
        Account robert = bank.account("robert");

        // Then
        assertThat(bob.owner).isEqualTo("bob");
        assertThat(robert.owner).isEqualTo("robert");
    }

    @Test
    public void should_return_null_if_no_account_found() throws Exception {
        // Given
        Bank bank = new Bank("XebiBank");
        bank.createAccount("bob");
        bank.createAccount("robert");

        // When
        Account ted = bank.account("ted");

        // Then
        assertThat(ted).isNull();
    }
}