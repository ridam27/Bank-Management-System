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
        ArrayList<BankAccount> accounts = FileHandler.loadAccounts();

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
            System.out.println("8. Change PIN");
            System.out.println("9. Admin Panel");
            System.out.println("10. Exit");

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
                    FileHandler.saveAccounts(accounts);

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
                            FileHandler.saveAccounts(accounts);
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
                            FileHandler.saveAccounts(accounts);
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

                        String senderTransaction = "Transferred: " + amount + " to Acc " + receiverAccNo;

                        String receiverTransaction = "Received: " + amount + " from Acc " + senderAccNo;

                        sender.transactions.add(senderTransaction);
                        receiver.transactions.add(receiverTransaction);

                        FileHandler.saveTransaction(sender.accountNumber, senderTransaction);
                        FileHandler.saveTransaction(receiver.accountNumber, receiverTransaction);

                        System.out.println("Transfer Successful!");
                        sender.displayBalance();
                    }
                    break;
                }

                case 7: {

                    System.out.print("Enter Account Number: ");
                    int hAcc = sc.nextInt();

                    BankAccount acc = findAccount(accounts, hAcc);

                    if (acc == null) {
                        System.out.println("Account not found!");
                        break;
                    }

                    System.out.print("Enter PIN: ");
                    int p = sc.nextInt();

                    if (!acc.checkPin(p)) {
                        System.out.println("Wrong PIN!");
                        break;
                    }

                    System.out.println("\n--- Transaction History ---");

                    ArrayList<String> history = FileHandler.loadTransactions(acc.accountNumber);

                    if (history.isEmpty()) {
                        System.out.println("No transactions yet.");
                    } else {
                        for (String t : history) {
                            System.out.println(t);
                        }
                    }

                    break;
                }
                case 8: {

                    System.out.print("Enter Account Number: ");
                    int accNo = sc.nextInt();

                    BankAccount account = findAccount(accounts, accNo);

                    if (account == null) {
                        System.out.println("Account not found!");
                        break;
                    }

                    System.out.print("Enter Current PIN: ");
                    int currentPin = sc.nextInt();

                    if (!account.checkPin(currentPin)) {
                        System.out.println("Incorrect PIN!");
                        break;
                    }

                    System.out.print("Enter New PIN: ");
                    int newPin = sc.nextInt();

                    account.changePin(newPin);

                    FileHandler.saveAccounts(accounts);

                    break;
                }

                case 9: {
                    System.out.print("Enter Admin Password: ");
                    String adminPass = sc.next();

                    if (adminPass.equals("1206")) {
                        int adminChoice;

                        do {

                            System.out.println("\n=== ADMIN PANEL ===");
                            System.out.println("1. View All Accounts");
                            System.out.println("2. Total Accounts");
                            System.out.println("3. Total Bank Balance");
                            System.out.println("4. Exit Admin Panel");

                            System.out.print("Enter Choice: ");
                            adminChoice = sc.nextInt();

                            switch (adminChoice) {

                                case 1: {
                                    Admin.viewAllAccounts(accounts);
                                    break;
                                }

                                case 2: {
                                    Admin.totalAccounts(accounts);
                                    break;
                                }

                                case 3: {
                                    Admin.totalBankBalance(accounts);
                                    break;
                                }

                                case 4: {
                                    System.out.println("Thank You!");
                                    break;
                                }

                                default:
                                    System.out.println("Invalid Choice");

                            }
                        } while (adminChoice != 4);

                    } else {
                        System.out.println("Wrong Admin Password!");
                    }

                    break;
                }
                case 10: {
                    System.out.println("Thank You!");
                    break;
                }
                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 10);

        sc.close();
    }
}