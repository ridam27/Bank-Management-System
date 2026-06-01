import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    static String FILE_NAME = "accounts.txt";

    // SAVE DATA
    public static void saveAccounts(ArrayList<BankAccount> accounts) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));

            for (BankAccount acc : accounts) {
                writer.write(acc.accountNumber + "," +
                        acc.name + "," +
                        acc.balance + "," +
                        acc.pin);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving data!");
        }
    }

    // LOAD DATA
    public static ArrayList<BankAccount> loadAccounts() {
        ArrayList<BankAccount> accounts = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                int accNo = Integer.parseInt(data[0]);
                String name = data[1];
                double balance = Double.parseDouble(data[2]);
                int pin = Integer.parseInt(data[3]);

                accounts.add(new BankAccount(accNo, name, balance, pin));
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("No previous data found.");
        }

        return accounts;
    }

    public static void saveTransaction(int accountNumber, String transaction) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.txt", true));

            writer.write(accountNumber + ":" + transaction);
            writer.newLine();

            writer.close();

        } catch (Exception e) {
            System.out.println("Error saving transaction!");
        }
    }

    public static ArrayList<String> loadTransactions(int accountNumber) {

        ArrayList<String> history = new ArrayList<>();

        try {

            BufferedReader reader = new BufferedReader(new FileReader("transactions.txt"));

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.startsWith(accountNumber + ":")) {

                    history.add(line.substring(
                            line.indexOf(":") + 1));
                }
            }

            reader.close();

        } catch (Exception e) {
            System.out.println("No transaction history found.");
        }

        return history;
    }

}