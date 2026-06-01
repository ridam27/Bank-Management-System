import java.util.ArrayList;

public class Admin {

    public static void viewAllAccounts(ArrayList<BankAccount> accounts) {

        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        for (BankAccount acc : accounts) {
            System.out.println("------------------");
            System.out.println("Account No: " + acc.accountNumber);
            System.out.println("Name: " + acc.name);
            System.out.println("Balance: " + acc.balance);
        }
    }

    public static void totalAccounts(ArrayList<BankAccount> accounts) {
        System.out.println("Total Accounts: " + accounts.size());
    }

    public static void totalBankBalance(ArrayList<BankAccount> accounts) {
        double total = 0;

        for (BankAccount acc : accounts) {
            total += acc.balance;
        }

        System.out.println("Total Bank Balance: " + total);
    }
}