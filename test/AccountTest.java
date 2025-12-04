import account.Account;
import account.CheckingAccount;
import account.SavingsAccount;
import customer.Customer;
import customer.PremiumCustomer;
import customer.RegularCustomer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    private Customer regularCustomer;
    private CheckingAccount checkingAccount;
    private SavingsAccount savingsAccount;

    @BeforeEach
    void setUp() {
        regularCustomer = new RegularCustomer("John Doe", 30, "0551234567", "Accra");
        checkingAccount = new CheckingAccount(regularCustomer, 1000.0);
        savingsAccount = new SavingsAccount(regularCustomer, 1500.0);
    }

    @Test
    void depositUpdatesBalance() {
        // Arrange
        double initialBalance = checkingAccount.getBalance();
        double depositAmount = 500.0;

        // Act
        checkingAccount.deposit(depositAmount);

        // Assert
        double expectedBalance = initialBalance + depositAmount;
        assertEquals(expectedBalance, checkingAccount.getBalance(), 0.01,
                "Deposit should increase balance by the deposited amount");
    }

    @Test
    void withdrawBelowMinimumThrowsException() {
        // Arrange
        double withdrawalAmount = 1200.0; // Would leave less than $500 minimum
        double initialBalance = savingsAccount.getBalance(); // $1500

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> savingsAccount.withdraw(withdrawalAmount),
                "Withdrawing below minimum balance should throw IllegalArgumentException"
        );

        // Verify balance didn't change
        assertEquals(initialBalance, savingsAccount.getBalance(), 0.01,
                "Balance should remain unchanged after failed withdrawal");

        // Optional: Verify exception message
        assertTrue(exception.getMessage().contains("Withdrawal denied") ||
                        exception.getMessage().contains("Insufficient funds"),
                "Exception message should mention Withdrawal denied or insufficient funds");
    }

    @Test
    void overdraftWithinLimitAllowed() {
        // Arrange
        double withdrawalAmount = 1500.0; // $1000 balance + $1000 overdraft = $2000 max
        double initialBalance = checkingAccount.getBalance(); // $1000

        // Act
        boolean success = checkingAccount.withdraw(withdrawalAmount);

        // Assert
        assertTrue(success, "Withdrawal within overdraft limit should succeed");
        assertEquals(initialBalance - withdrawalAmount, checkingAccount.getBalance(), 0.01,
                "Balance should be negative but within overdraft limit");
        assertTrue(checkingAccount.getBalance() < 0,
                "Balance should be negative when using overdraft");
        assertTrue(checkingAccount.getBalance() >= -1000.0,
                "Negative balance should not exceed overdraft limit of $1000");
    }

    @Test
    void overdraftExceedThrowsException() {
        // Arrange
        double withdrawalAmount = 2500.0; // $1000 balance + $1000 overdraft = $2000 max
        double initialBalance = checkingAccount.getBalance(); // $1000

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> checkingAccount.withdraw(withdrawalAmount),
                "Withdrawing beyond overdraft limit should throw IllegalArgumentException"
        );

        // Verify balance didn't change
        assertEquals(initialBalance, checkingAccount.getBalance(), 0.01,
                "Balance should remain unchanged after failed overdraft withdrawal");

        // Optional: Verify exception message
        assertTrue(exception.getMessage().contains("Insufficient funds"),
                "Exception message should mention insufficient funds");
    }

    // Optional: Add a transfer test if needed
    @Test
    void transferBetweenAccountsSucceeds() {
        // Arrange
        Account source = new CheckingAccount(regularCustomer, 1000.0);
        Account target = new SavingsAccount(regularCustomer, 500.0);
        double transferAmount = 300.0;

        // Act
        boolean success = source.transfer(target, transferAmount);

        // Assert
        assertTrue(success, "Transfer should succeed with sufficient funds");
        assertEquals(700.0, source.getBalance(), 0.01,
                "Source balance should decrease by transfer amount");
        assertEquals(800.0, target.getBalance(), 0.01,
                "Target balance should increase by transfer amount");
    }
}