import java.util.Date;

public class Transaction {
    private final double amount;
    private final Date timestamp;
    private final Account account;
    private String memo;


    public Transaction(double amount, Account inAccount) {
        this.amount = amount;
        this.account = inAccount;
        this.timestamp = new Date();
        this.memo = "";
    }

    public Transaction(double amount, Account inAccount, String memo) {
        // call the two-arg constructor first
        this(amount, inAccount);
        this.memo = memo;
    }


    public double getAmount() {
        return this.amount;
    }

    public String getSummaryLine() {
        if (this.amount >= 0) {
            return String.format("%s : KSH %.02f : %s \n", this.timestamp.toString(), this.amount, this.memo);
        } else {
            return String.format("%s : KSH (%.02f) : %s \n", this.timestamp.toString(), this.amount, this.memo);
        }
    }
}
