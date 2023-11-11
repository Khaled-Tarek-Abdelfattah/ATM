import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {
    private final String firstName;
    private final String lastName;
    private final String uuid;
    private final ArrayList<Account> accounts;
    private byte[] pinHash;


    public User(String firstName, String lastName, String pin, Bank theBank) {
        this.firstName = firstName;
        this.lastName = lastName;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        this.uuid = theBank.getNewUserUUID();
        this.accounts = new ArrayList<Account>();

        System.out.printf("New user %s, %s with ID %s created.\n", lastName, firstName, this.uuid);
    }


    public String getFirstName() {
        return this.firstName;
    }


    public double getAccountBalance(int fromAcctIdx) {
        return this.accounts.get(fromAcctIdx).getBalance();
    }

    public Integer numAccounts() {
        return this.accounts.size();
    }


    public void addAccount(Account account) {
        this.accounts.add(account);
    }


    public String getAccountUUID(int toAcctIdx) {
        return this.accounts.get(toAcctIdx).getAccountUUID();
    }

    public String getUUID() {
        return this.uuid;
    }


    public boolean validatePIN(String PIN) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(PIN.getBytes()), this.pinHash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        return false;
    }


    public void addAccountTransaction(int acctIdx, double amount, String memo) {
        this.accounts.get(acctIdx).addTransaction(amount, memo);
    }

  
    public void printAccountsSummary() {
        System.out.printf("\n\n%s's accounts summary \n", this.firstName);
        for (int a = 0; a < this.accounts.size(); a++) {
            System.out.printf("%d) %s\n", a + 1, this.accounts.get(a).getSummary());
        }
    }


    public void printAccountTransactionHistory(int theAcctIdx) {
        this.accounts.get(theAcctIdx).printTransactionHistory();
    }
}