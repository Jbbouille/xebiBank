package com.xebia.jbbouille.account;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AccountTest {

    @Test
    public void should_receive_money() throws Exception {
        // Given
        Account bob = Account.create("bob", ZERO);

        // When
        bob.deposit(TEN);

        // Then
        assertThat(bob.actualState().balance).isEqualTo(TEN);
    }

    @Test
    public void should_remove_money() throws Exception {
        // Given
        Account bob = Account.create("bob", ZERO);
        bob.deposit(TEN);

        // When
        bob.withdrawal(ONE);

        // Then
        assertThat(bob.actualState().balance).isEqualTo(new BigDecimal("9"));
    }

    @Test
    public void should_error_when_try_to_remove_more_money_than_available() throws Exception {
        // Given
        Account bob = Account.create("bob", ZERO);
        bob.deposit(TEN);

        // When
        ThrowingCallable willError = () -> bob.withdrawal(new BigDecimal("11"));

        // Then
        assertThatThrownBy(willError).isInstanceOf(CannotWithdrawalException.class);
    }

    @Test
    public void should_return_balance() throws Exception {
        // Given
        Account bob = Account.create("bob", ZERO);
        bob.deposit(TEN);

        // When
        AccountState actualState = bob.actualState();


        // Then
        assertThat(actualState.balance).isEqualTo(TEN);
    }

    @Test
    public void should_return_all_state() throws Exception {
        // Given
        Account bob = Account.create("bob", ZERO);
        bob.deposit(TEN);
        bob.deposit(new BigDecimal("11"));

        // When
        List<AccountState> allState = bob.allState();


        // Then
        assertThat(allState).extracting(s -> s.amount).containsExactly(ZERO, TEN, new BigDecimal("11"));
        assertThat(allState).extracting(s -> s.balance).containsExactly(ZERO, TEN, new BigDecimal("21"));
    }

    @Test
    public void should_init_account_with_zero() throws Exception {
        // Given / When
        Account bob = Account.create("bob", ZERO);

        // Then
        assertThat(bob.actualState().balance).isEqualTo(ZERO);
    }
}