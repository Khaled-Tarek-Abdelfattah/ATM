import java.util.ArrayList;

public class Account {
    private final String name;
    private final String uuid;
    private final User holder;
    private final ArrayList<Transaction> transactions;


    public Account(String name, User holder, Bank thebank) {
        this.name = name;
        this.holder = holder;

        // get new account UUID
        this.uuid = thebank.getNewAccountUUID();

        this.transactions = new ArrayList<Transaction>();
    }

    public double getBalance() {
        double balance = 0;

        for (Transaction t : this.transactions) {
            balance += t.getAmount();
        }
        return balance;
    }

    public String getAccountUUID() {
        return this.uuid;
    }

    public void addTransaction(double amount, String memo) {
        Transaction newTransaction = new Transaction(amount, this, memo);
        this.transactions.add(newTransaction);
    }

    public String getSummary() {
        //get the account balance
        double balance = this.getBalance();

        // format summary line depending on if the account has overdraft
        return String.format("%s : KSH %.02f : %s \n", this.uuid, balance, this.name);
    }

    public void printTransactionHistory() {
        System.out.printf("\nTransaction history for account %s\n", this.uuid);

        for (int t = this.transactions.size() - 1; t >= 0; t--) {
            System.out.printf(this.transactions.get(t).getSummaryLine());
        }
    }
}
