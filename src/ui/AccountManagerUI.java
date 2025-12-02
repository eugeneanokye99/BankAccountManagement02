package ui;

import account.Account;
import account.AccountManager;
import account.CheckingAccount;
import account.SavingsAccount;
import utils.CustomUtils;
import utils.InputValidator;

import java.time.LocalDate;
import java.util.Scanner;

public class AccountManagerUI {
    private AccountManager accountManager;
    private Scanner scanner;

    // Constructor to inject dependencies
    public AccountManagerUI(AccountManager accountManager, Scanner scanner) {
        this.accountManager = accountManager;
        this.scanner = scanner;
    }

    // Main entry point
    public void manageAccounts() {
        int manageChoice;

        do {
            displayManageAccountsMenu();
            CustomUtils.printInline("Enter choice: ");

            try {
                manageChoice = scanner.nextInt();
                scanner.nextLine();

                switch (manageChoice) {
                    case 1: updateAccountStatus(); break;
                    case 2: updateAccountInformation(); break;
                    case 3: return; // Go back
                    default: CustomUtils.printError("Invalid choice! Please enter 1-6.");
                }

                if (manageChoice != 3) {
                    CustomUtils.printInline("\nPress Enter to continue...");
                    scanner.nextLine();
                }

            } catch (Exception e) {
                CustomUtils.printError("Invalid input! Please enter a number.");
                scanner.nextLine();
                manageChoice = 0;
            }

        } while (manageChoice != 3);
    }

    private void displayManageAccountsMenu() {
        CustomUtils.printHeader("MANAGE ACCOUNTS");
        CustomUtils.print("1. Update Account Status");
        CustomUtils.print("2. Update Account Information");
        CustomUtils.print("3. Back to Account Menu");
        CustomUtils.print();
    }

    // 1. Update Account Status
    private void updateAccountStatus() {
        CustomUtils.printSection("UPDATE ACCOUNT STATUS");

        CustomUtils.printInline("Enter Account Number: ");
        String accountNumber = scanner.nextLine();

        Account account = accountManager.findAccount(accountNumber);
        if (account == null) {
            CustomUtils.printError("Account not found!");
            return;
        }

        CustomUtils.print("\nCurrent Status: " + account.getStatus());
        CustomUtils.print("\nSelect new status:");
        CustomUtils.print("1. Active");
        CustomUtils.print("2. Inactive");
        CustomUtils.print("3. Closed");

        int statusChoice = InputValidator.getValidInt(scanner,
                "Select status (1-3): ",
                1, 3);

        String newStatus;
        switch (statusChoice) {
            case 1: newStatus = "Active"; break;
            case 2: newStatus = "Inactive"; break;
            case 3: newStatus = "Closed"; break;
            default: newStatus = "Active";
        }

        // Check if account can be closed (balance should be zero)
        if (newStatus.equals("Closed") && account.getBalance() > 0) {
            CustomUtils.printError("Cannot close account with balance. Withdraw all funds first.");
            return;
        }

        String confirm = InputValidator.getValidInput(scanner,
                "Change status from '" + account.getStatus() + "' to '" + newStatus + "'? (Y/N): ",
                InputValidator::isValidConfirmation,
                "Please enter Y or N");

        if (confirm.equalsIgnoreCase("Y")) {
            account.setStatus(newStatus);
            CustomUtils.printSuccess("Account status updated successfully!");
            CustomUtils.print("New Status: " + account.getStatus());
        } else {
            CustomUtils.print("Status update cancelled.");
        }
    }

    // 2. Update Account Information
    private void updateAccountInformation() {
        CustomUtils.printSection("UPDATE ACCOUNT INFORMATION");

        CustomUtils.printInline("Enter Account Number: ");
        String accountNumber = scanner.nextLine();

        Account account = accountManager.findAccount(accountNumber);
        if (account == null) {
            CustomUtils.printError("Account not found!");
            return;
        }

        CustomUtils.print("\nWhat would you like to update?");
        CustomUtils.print("1. Customer Contact Information");
        if (account instanceof CheckingAccount) {
            CustomUtils.print("2. Overdraft Limit");
        }
        CustomUtils.print("3. Cancel");

        int updateChoice = InputValidator.getValidInt(scanner,
                "Select option (1-3): ",
                1, 3);

        switch (updateChoice) {
            case 1:
                updateContactInformation(account);
                break;
            case 2:
                if (account instanceof CheckingAccount) {
                    updateOverdraftLimit((CheckingAccount) account);
                } else {
                    CustomUtils.printError("Invalid option for this account type.");
                }
                break;
            case 3:
                CustomUtils.print("Update cancelled.");
                break;
        }
    }

    private void updateContactInformation(Account account) {
        CustomUtils.print("\nCurrent Contact: " + account.getCustomer().getContact());

        String newContact = InputValidator.getValidInput(scanner,
                "Enter new contact information: ",
                InputValidator.ValidationRules.CONTACT_RULE,
                "Please enter a valid contact number");

        // Note: This would require adding a setter for contact in Customer class
        CustomUtils.printSuccess("Contact information update functionality requires Customer class modification.");
        CustomUtils.print("New contact would be: " + newContact);
    }

    private void updateOverdraftLimit(CheckingAccount checkingAccount) {
        CustomUtils.print("\nCurrent Overdraft Limit: $" +
                String.format("%.2f", checkingAccount.getOverdraftLimit()));

        double newLimit = InputValidator.getValidDouble(scanner,
                "Enter new overdraft limit ($): ",
                0.01);

        // Note: This would require adding a setter for overdraftLimit in CheckingAccount
        CustomUtils.printSuccess("Overdraft limit update functionality requires CheckingAccount class modification.");
        CustomUtils.print("New limit would be: $" + String.format("%.2f", newLimit));
    }


}