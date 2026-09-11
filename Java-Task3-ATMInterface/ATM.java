import java.util.ArrayList;
import java.util.Scanner;

public class ATM {

    private Bank bank;
    private Scanner sc;
    private Account currentAccount;

    private ArrayList<Transaction> transactions;

    public ATM(Bank bank) {

        this.bank = bank;
        sc = new Scanner(System.in);
        transactions = new ArrayList<>();
    }

    public void start() {

        System.out.println("======================================");
        System.out.println("             ATM MACHINE");
        System.out.println("======================================");

        if (login()) {
            showMenu();
        }
    }

    private boolean login() {

        int attempts = 0;

        while (attempts < 3) {

            System.out.print("\nEnter User ID: ");
            String userId = sc.next();

            System.out.print("Enter PIN: ");
            int pin = sc.nextInt();

            Account account = bank.findAccount(userId);

            if (account != null && account.checkPin(pin)) {

                currentAccount = account;

                System.out.println("\nLogin Successful!");
                System.out.println(
                        "Welcome, " + currentAccount.getName()
                );

                return true;
            }

            attempts++;

            System.out.println("Incorrect User ID or PIN.");

            if (attempts < 3) {
                System.out.println(
                        "Attempts remaining: " + (3 - attempts)
                );
            }
        }

        System.out.println("\nAccess Denied.");
        System.out.println("You have used all 3 attempts.");

        return false;
    }

    private void showMenu() {

        int choice;

        do {

            System.out.println("\n======================================");
            System.out.println("                ATM MENU");
            System.out.println("======================================");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Check Balance");
            System.out.println("6. Quick Cash");
            System.out.println("7. Change PIN");
            System.out.println("8. Account Details");
            System.out.println("9. Mini Statement");
            System.out.println("10. Quit");
            System.out.println("======================================");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    showTransactionHistory();
                    break;

                case 2:
                    withdraw();
                    break;

                case 3:
                    deposit();
                    break;

                case 4:
                    transfer();
                    break;

                case 5:
                    checkBalance();
                    break;

                case 6:
                    quickCash();
                    break;

                case 7:
                    changePin();
                    break;

                case 8:
                    showAccountDetails();
                    break;

                case 9:
                    miniStatement();
                    break;

                case 10:
                    System.out.println(
                            "\nThank you for using our ATM."
                    );
                    System.out.println(
                            "Please collect your card."
                    );
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }

        } while (choice != 10);
    }

    private void withdraw() {

        System.out.print(
                "\nEnter withdrawal amount: Rs."
        );

        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }

        if (amount > currentAccount.getBalance()) {
            System.out.println("Insufficient Funds.");
            return;
        }

        currentAccount.withdraw(amount);

        transactions.add(
                new Transaction(
                        "WITHDRAW",
                        amount,
                        "Cash withdrawal"
                )
        );

        System.out.println("\nWithdrawal successful.");
        System.out.println("Please collect your cash.");

        System.out.println(
                "Remaining Balance: Rs."
                        + money(currentAccount.getBalance())
        );

        printReceipt("WITHDRAW", amount);
    }

    private void deposit() {

        System.out.print(
                "\nEnter deposit amount: Rs."
        );

        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }

        currentAccount.deposit(amount);

        transactions.add(
                new Transaction(
                        "DEPOSIT",
                        amount,
                        "Cash deposit"
                )
        );

        System.out.println("\nDeposit successful.");

        System.out.println(
                "New Balance: Rs."
                        + money(currentAccount.getBalance())
        );

        printReceipt("DEPOSIT", amount);
    }

    private void transfer() {

        System.out.print(
                "\nEnter recipient account ID: "
        );

        String receiverId = sc.next();

        if (receiverId.equals(
                currentAccount.getAccountId())) {

            System.out.println(
                    "You cannot transfer money to your own account."
            );

            return;
        }

        Account receiver = bank.findAccount(receiverId);

        if (receiver == null) {

            System.out.println(
                    "Recipient account not found."
            );

            return;
        }

        System.out.println(
                "Recipient Name: " + receiver.getName()
        );

        System.out.print(
                "Enter transfer amount: Rs."
        );

        double amount = sc.nextDouble();

        if (amount <= 0) {

            System.out.println(
                    "Amount must be greater than zero."
            );

            return;
        }

        if (amount > currentAccount.getBalance()) {

            System.out.println("Insufficient Funds.");
            return;
        }

        System.out.print(
                "Confirm transfer? (yes/no): "
        );

        String confirm = sc.next();

        if (!confirm.equalsIgnoreCase("yes")) {

            System.out.println(
                    "Transfer cancelled."
            );

            return;
        }

        boolean success = bank.transfer(
                currentAccount,
                receiver,
                amount
        );

        if (success) {

            transactions.add(
                    new Transaction(
                            "TRANSFER",
                            amount,
                            "Transferred to account " + receiverId
                    )
            );

            System.out.println(
                    "\nTransfer successful."
            );

            System.out.println(
                    "Remaining Balance: Rs."
                            + money(
                            currentAccount.getBalance()
                    )
            );

        } else {

            System.out.println(
                    "Transfer failed."
            );
        }
    }

    private void checkBalance() {

        System.out.println(
                "\nCurrent Balance: Rs."
                        + money(
                        currentAccount.getBalance()
                )
        );
    }

    private void quickCash() {

        System.out.println("\n========== QUICK CASH ==========");
        System.out.println("1. Rs.500");
        System.out.println("2. Rs.1000");
        System.out.println("3. Rs.2000");
        System.out.println("4. Rs.5000");
        System.out.println("5. Cancel");

        System.out.print("Choose amount: ");

        int choice = sc.nextInt();

        double amount;

        switch (choice) {

            case 1:
                amount = 500;
                break;

            case 2:
                amount = 1000;
                break;

            case 3:
                amount = 2000;
                break;

            case 4:
                amount = 5000;
                break;

            case 5:
                System.out.println(
                        "Quick Cash cancelled."
                );
                return;

            default:
                System.out.println(
                        "Invalid choice."
                );
                return;
        }

        if (amount > currentAccount.getBalance()) {

            System.out.println("Insufficient Funds.");
            return;
        }

        currentAccount.withdraw(amount);

        transactions.add(
                new Transaction(
                        "QUICK CASH",
                        amount,
                        "Quick cash withdrawal"
                )
        );

        System.out.println(
                "\nPlease collect Rs."
                        + money(amount)
        );

        System.out.println(
                "Remaining Balance: Rs."
                        + money(
                        currentAccount.getBalance()
                )
        );
    }

    private void changePin() {

        System.out.print(
                "\nEnter current PIN: "
        );

        int oldPin = sc.nextInt();

        System.out.print(
                "Enter new PIN: "
        );

        int newPin = sc.nextInt();

        if (newPin < 1000 || newPin > 9999) {

            System.out.println(
                    "PIN must contain exactly 4 digits."
            );

            return;
        }

        System.out.print(
                "Confirm new PIN: "
        );

        int confirmPin = sc.nextInt();

        if (newPin != confirmPin) {

            System.out.println(
                    "New PINs do not match."
            );

            return;
        }

        if (currentAccount.changePin(
                oldPin,
                newPin)) {

            System.out.println(
                    "PIN changed successfully."
            );

        } else {

            System.out.println(
                    "Incorrect current PIN."
            );
        }
    }

    private void showAccountDetails() {

        System.out.println(
                "\n========== ACCOUNT DETAILS =========="
        );

        System.out.println(
                "Account ID : "
                        + currentAccount.getAccountId()
        );

        System.out.println(
                "Name       : "
                        + currentAccount.getName()
        );

        System.out.println(
                "Balance    : Rs."
                        + money(
                        currentAccount.getBalance()
                )
        );
    }

    private void showTransactionHistory() {

        System.out.println(
                "\n========== TRANSACTION HISTORY =========="
        );

        if (transactions.isEmpty()) {

            System.out.println(
                    "No transactions in this session."
            );

            return;
        }

        for (Transaction transaction : transactions) {

            System.out.println(transaction);
        }

        System.out.println(
                "=========================================="
        );
    }

    private void miniStatement() {

        System.out.println(
                "\n========== MINI STATEMENT =========="
        );

        if (transactions.isEmpty()) {

            System.out.println(
                    "No transactions available."
            );

            return;
        }

        int start = Math.max(
                0,
                transactions.size() - 5
        );

        for (int i = start;
             i < transactions.size();
             i++) {

            System.out.println(
                    transactions.get(i)
            );
        }

        System.out.println(
                "-----------------------------------"
        );

        System.out.println(
                "Current Balance: Rs."
                        + money(
                        currentAccount.getBalance()
                )
        );
    }

    private void printReceipt(
            String type,
            double amount) {

        System.out.println(
                "\n---------- RECEIPT ----------"
        );

        System.out.println(
                "Account ID : "
                        + currentAccount.getAccountId()
        );

        System.out.println(
                "Transaction: " + type
        );

        System.out.println(
                "Amount     : Rs."
                        + money(amount)
        );

        System.out.println(
                "Balance    : Rs."
                        + money(
                        currentAccount.getBalance()
                )
        );

        System.out.println(
                "-----------------------------"
        );
    }

    private String money(double amount) {

        return String.format("%.2f", amount);
    }
}
