import java.util.ArrayList;

public class BankAccount {

    int accountNumber;
    String name;
    double balance;
    int pin;

    ArrayList<String> transactions = new ArrayList<>();

    // Constructor
    BankAccount(int accountNumber, String name, double balance, int pin) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
        this.pin = pin;
    }

    void deposit(double amount) {
        balance += amount;

        String transaction = "Deposited: " + amount;

        transactions.add(transaction);

        FileHandler.saveTransaction(accountNumber, transaction);

        System.out.println("Amount Deposited Successfully");
    }

    void withdraw(double amount) {

        if (amount > balance) {
            System.out.println("Insufficient Balance");
        } else {

            balance -= amount;

            String transaction = "Withdrawn: " + amount;

            transactions.add(transaction);

            FileHandler.saveTransaction(accountNumber, transaction);

            System.out.println("Withdrawal Successful");
        }
    }

    void displayBalance() {
        System.out.println("Current Balance: " + balance);
    }

    void displayDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Name: " + name);
        System.out.println("Balance: " + balance);
    }

    boolean checkPin(int inputPin) {
        return this.pin == inputPin;
    }

    void changePin(int newPin) {
        this.pin = newPin;
        System.out.println("PIN changed successfully!");
    }
}