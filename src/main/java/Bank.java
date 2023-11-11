import java.util.ArrayList;
import java.util.Random;

public class Bank {
    private final String name;
    private final ArrayList<User> users;
    private final ArrayList<Account> accounts;

    public Bank(String name) {
        this.name = name;
        this.users = new ArrayList<>();
        this.accounts = new ArrayList<>();
    }


    public String getName() {
        return this.name;
    }

  
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

  
    public String getNewUserUUID() {
        StringBuilder uuid;

        Random rng = new Random();
        int len = 6;
        boolean nonUnique;

        do {
            // generate user uuid
            uuid = new StringBuilder();
            for (int c = 0; c < len; c++) {
                uuid.append(((Integer) rng.nextInt(10)));
            }

            //check if it's unique
            nonUnique = false;
            for (User u : this.users) {
                if (uuid.toString().compareTo(u.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);

        return uuid.toString();
    }

    public String getNewAccountUUID() {
        StringBuilder accountID;

        Random rng = new Random();
        int len = 12;
        boolean nonUnique;

        do {
            // generate account uuid
            accountID = new StringBuilder();
            for (int c = 0; c < len; c++) {
                accountID.append(((Integer) rng.nextInt(10)));
            }

            //check if it's unique
            nonUnique = false;
            for (Account u : this.accounts) {
                if (accountID.toString().compareTo(u.getAccountUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);

        return accountID.toString();
    }


    public User addUser(String firstName, String lastName, String PIN) {
        //create a new user object and add it to the ArrayList
        User newUser = new User(firstName, lastName, PIN, this);
        this.users.add(newUser);

        //create a saving account
        Account newAccount = new Account("Saving", newUser, this);
        newUser.addAccount(newAccount);
        this.accounts.add(newAccount);
        return newUser;
    }

    public User userLogin(String userUUID, String PIN) {
        for (User u : this.users) {
            if (u.getUUID().compareTo(userUUID) == 0 && u.validatePIN(PIN)) {
                return u;
            }
        }
        return null;
    }
}