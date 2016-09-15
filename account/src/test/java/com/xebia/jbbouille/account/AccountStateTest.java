package com.xebia.jbbouille.account;

import org.junit.Test;

import static java.math.BigDecimal.TEN;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountStateTest {
    @Test
    public void should_return_string_representing_account_state() throws Exception {
        // Given / When
        AccountState accountState = new AccountState(TEN, TEN);

        // Then
        assertThat(accountState.toString()).contains("balance=10", "amount=10");
    }

    @Test
    public void should_init_account_state_with_date_of_action() throws Exception {
        // Given / When
        AccountState accountState = new AccountState(TEN, TEN);

        // Then
        assertThat(accountState.date).isToday(); // isToday() ... love you soo much assertJ !
    }
}