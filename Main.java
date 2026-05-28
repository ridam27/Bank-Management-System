import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static BankAccount findAccount(ArrayList<BankAccount> accounts, int accNo) {
        for (BankAccount acc : accounts) {
            if (acc.accountNumber == accNo) {
                return acc;
            }
        }
        return null;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<BankAccount> accounts = new ArrayList<>();

        int choice;

        do {
            System.out.println("\n===== BANK MENU =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Account Details");
            System.out.println("6. Transfer Money");
            System.out.println("7. Transaction History");
            System.out.println("8. Exit");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1: {
                    System.out.print("Enter Account Number: ");
                    int accNo = sc.nextInt();

                    // ✅ Duplicate check
                    if (findAccount(accounts, accNo) != null) {
                        System.out.println("Account number already exists!");
                        break;
                    }

                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Initial Balance: ");
                    double bal = sc.nextDouble();

                    System.out.print("Set PIN: ");
                    int pin = sc.nextInt();

                    BankAccount newAcc = new BankAccount(accNo, name, bal, pin);
                    accounts.add(newAcc);

                    System.out.println("Account Created Successfully!");
                    break;
                }

                case 2: {
                    System.out.print("Enter Account Number: ");
                    int dAcc = sc.nextInt();

                    BankAccount acc1 = findAccount(accounts, dAcc);

                    if (acc1 != null) {
                        System.out.print("Enter PIN: ");
                        int p = sc.nextInt();

                        if (acc1.checkPin(p)) {
                            System.out.print("Enter Amount: ");
                            double amt = sc.nextDouble();
                            acc1.deposit(amt);
                            acc1.displayBalance();
                        } else {
                            System.out.println("Wrong PIN!");
                        }
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;
                }

                case 3: {
                    System.out.print("Enter Account Number: ");
                    int wAcc = sc.nextInt();

                    BankAccount acc2 = findAccount(accounts, wAcc);

                    if (acc2 != null) {
                        System.out.print("Enter PIN: ");
                        int p = sc.nextInt();

                        if (acc2.checkPin(p)) {
                            System.out.print("Enter Amount: ");
                            double amt = sc.nextDouble();
                            acc2.withdraw(amt);
                            acc2.displayBalance();
                        } else {
                            System.out.println("Wrong PIN!");
                        }
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;
                }

                case 4: {
                    System.out.print("Enter Account Number: ");
                    int bAcc = sc.nextInt();

                    BankAccount acc3 = findAccount(accounts, bAcc);

                    if (acc3 != null) {
                        System.out.print("Enter PIN: ");
                        int p = sc.nextInt();

                        if (acc3.checkPin(p)) {
                            acc3.displayBalance();
                        } else {
                            System.out.println("Wrong PIN!");
                        }
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;
                }

                case 5: {
                    System.out.print("Enter Account Number: ");
                    int aAcc = sc.nextInt();

                    BankAccount acc4 = findAccount(accounts, aAcc);

                    if (acc4 != null) {
                        System.out.print("Enter PIN: ");
                        int p = sc.nextInt();

                        if (acc4.checkPin(p)) {
                            acc4.displayDetails();
                        } else {
                            System.out.println("Wrong PIN!");
                        }
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;
                }

                case 6: {
                    System.out.print("Enter Sender Account Number: ");
                    int senderAccNo = sc.nextInt();

                    BankAccount sender = findAccount(accounts, senderAccNo);

                    if (sender == null) {
                        System.out.println("Sender account not found!");
                        break;
                    }

                    System.out.print("Enter PIN: ");
                    int p = sc.nextInt();

                    if (!sender.checkPin(p)) {
                        System.out.println("Wrong PIN!");
                        break;
                    }

                    System.out.print("Enter Receiver Account Number: ");
                    int receiverAccNo = sc.nextInt();

                    BankAccount receiver = findAccount(accounts, receiverAccNo);

                    if (receiver == null) {
                        System.out.println("Receiver account not found!");
                        break;
                    }

                    System.out.print("Enter Amount: ");
                    double amount = sc.nextDouble();

                    if (amount > sender.balance) {
                        System.out.println("Insufficient Balance!");
                    } else {
                        sender.balance -= amount;
                        receiver.balance += amount;

                        sender.transactions.add("Transferred: " + amount + " to Acc " + receiverAccNo);
                        receiver.transactions.add("Received: " + amount + " from Acc " + senderAccNo);

                        System.out.println("Transfer Successful!");
                        sender.displayBalance();
                    }
                    break;
                }

                case 7: {
                    System.out.print("Enter Account Number: ");
                    int hAcc = sc.nextInt();

                    BankAccount acc = findAccount(accounts, hAcc);

                    if (acc != null) {
                        System.out.print("Enter PIN: ");
                        int p = sc.nextInt();

                        if (acc.checkPin(p)) {
                            System.out.println("\n--- Transaction History ---");

                            if (acc.transactions.isEmpty()) {
                                System.out.println("No transactions yet.");
                            } else {
                                for (String t : acc.transactions) {
                                    System.out.println(t);
                                }
                            }
                        } else {
                            System.out.println("Wrong PIN!");
                        }
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;
                }
                case 8: {
                    System.out.println("Thank You!");
                    break;
                }
                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 8);

        sc.close();
    }
}