import java.util.ArrayList;
import java.util.Random;

public class Bank {
    
    
    private String name;


    private ArrayList<User> users;


    private ArrayList<Account> accounts; 

    /**
     * Create a new Bank object with empty lists of users and accounts
     * @param name          the name of the bank 
     */

    public Bank(String name) {
        
        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();

    }
    
    /*
     * Generate a new universally unique ID for a user.
     * @return  the uuid
     */

    public String getNewUserUUID() {

        // inits
        String uuid;
        Random rng = new Random();
        int len = 6;
        boolean nonUnique;

        // loops until we get a unique identification number
        do {

            // generate the number
            uuid = "";
            for (int c = 0; c < len; c++) {
                uuid += ((Integer)rng.nextInt(10)).toString();
            }

            // check to make sure it's unique 
            nonUnique = false;
            for (User u : this.users) {
                if (uuid.compareTo(u.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }

        } while (nonUnique);


        return uuid;

    }

    /*
     * Generate a new universally unique ID for an account
     * @return      the uuid
     */
    public String getNewAccountUUID() {

        // inits
        String uuid;
        Random rng = new Random();
        int len = 10;
        boolean nonUnique;

        // loops until we get a unique identification number
        do {

            // generate the number
            uuid = "";
            for (int c = 0; c < len; c++) {
                uuid += ((Integer)rng.nextInt(10)).toString();
            }

            // check to make sure it's unique 
            nonUnique = false;
            for (Account a : this.accounts) {
                if (uuid.compareTo(a.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }

        } while (nonUnique);


        return uuid;

    }

    /**
     * Add an account 
     * @param anAcct        the account to add
     */

     public void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
     }

    /**
     * Create a new user of the bank 
     * @param firstName     the user's first name
     * @param lastName      the user's last name 
     * @param pin           the user's pin 
     * @return              the new User object 
     */

    public User addUser(String firstName, String lastName, String pin) {

        // creates a new User object and add it to our list 
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);


        // creates a savings account for the user and add to User & Bank accounts lists
        Account newAccount = new Account("Savings", newUser, this);
        newUser.addAccount(newAccount);
        this.accounts.add(newAccount);

        return newUser;

    }

    /**
     * Get the User object associated with a particular userID and pin, if they are valid 
     * @param userID        the UUID of the user to log in 
     * @param pin           the pin of the user 
     * @return              the User object, if the login is successful, or null, if it is not
     */

    public User userLogin(String userID, String pin) {

        // search through list of users 
        for (User u : this.users) {
            if(u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
                return u;
            } 
        }

        // if we haven't found the user or have incorrect pin 
        return null;
    }

    public String getName() {
        return this.name;
    }

}
