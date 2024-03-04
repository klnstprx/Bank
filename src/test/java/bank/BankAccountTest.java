package bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    BankAccount account;
    int initialBalance = 100;

    @BeforeEach
    public void setup() {
        account = new BankAccount(initialBalance);
    }

    @Test
    @DisplayName("Initial balance correct.")
    public void testInitialBalance() {
        int expectedValue = initialBalance;
        int obtainedValue = account.getBalance();
        assertEquals(expectedValue, obtainedValue);
    }

    @Test
    @DisplayName("Withdraw correct.")
    public void testWithdraw() {
        int amount = 50;
        boolean correct = account.withdraw(amount);
        int expectedValue = initialBalance - amount;
        int obtainedValue = account.getBalance();
        assertTrue(correct);
        assertEquals(expectedValue, obtainedValue);
    }

    @Test
    @DisplayName("Withdraw incorrect.")
    public void testWithdrawIncorrect() {
        int amount = 150;
        boolean correct = account.withdraw(amount);
        int expectedValue = initialBalance;
        int obtainedValue = account.getBalance();
        assertFalse(correct);
        assertEquals(expectedValue, obtainedValue);
    }

    @Test
    @DisplayName("Deposit correct.")
    public void testDeposit() {
        int amount = 50;
        int expectedValue = initialBalance + amount;
        int obtainedValue = account.deposit(amount);
        assertEquals(expectedValue, obtainedValue);
    }

    @Test
    @DisplayName("Deposit incorrect, when non-positive deposit amount.")
    public void testDepositIncorrect() {
        int amount = -50;
        assertThrows(IllegalArgumentException.class, () -> account.deposit(amount));
    }

    @Test
    @DisplayName("Payment value correct.")
    public void testPayment() {
        double total_amount = 1000;
        double interest = 0.1;
        int npayments = 12;
        double expectedValue = 146.763;
        double obtainedValue = account.payment(total_amount, interest, npayments);
        assertEquals(expectedValue, obtainedValue, 0.001);
    }

    @Test
    @DisplayName("Pending base case correct.")
    public void testPendingBaseCase() {
        double total_amount = 1000;
        double interest = 0.1;
        int npayments = 12;
        int month = 0;
        double expectedValue = total_amount;
        double obtainedValue = account.pending(total_amount, interest, npayments, month);
        assertEquals(expectedValue, obtainedValue, 0.001);
    }

    @Test
    @DisplayName("Pending amount correct.")
    public void testPending() {
        double total_amount = 1000;
        double interest = 0.1;
        int npayments = 12;
        int month = 2;
        double expectedValue = 901.797;
        double obtainedValue = account.pending(total_amount, interest, npayments, month);
        assertEquals(expectedValue, obtainedValue, 0.001);
    }

}
