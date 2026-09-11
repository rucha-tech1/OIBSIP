import java.util.ArrayList;

public class Bank {

    private ArrayList<Account> accounts;

    public Bank() {

        accounts = new ArrayList<>();

        accounts.add(
                new Account("1001", "Rucha", 1234, 10000)
        );

        accounts.add(
                new Account("1002", "Rahul", 5678, 8000)
        );

        accounts.add(
                new Account("1003", "Priya", 4321, 12000)
        );

        accounts.add(
                new Account("1004", "Amit", 2468, 15000)
        );
    }

    public Account findAccount(String accountId) {

        for (Account account : accounts) {

            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }

        return null;
    }

    public boolean transfer(Account sender,
                            Account receiver,
                            double amount) {

        if (amount <= 0) {
            return false;
        }

        if (sender.getBalance() < amount) {
            return false;
        }

        sender.withdraw(amount);
        receiver.deposit(amount);

        return true;
    }
}
