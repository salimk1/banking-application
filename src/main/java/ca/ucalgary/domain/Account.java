package ca.ucalgary.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import java.util.UUID;

/**
 * This Account class represents the amount of money for this account type.
 */
public class Account {
    private String id; // Represents a Unique ID to identify an account
    private double balance; // Represents the current funds on this account
    private String type; // What kind of type of account it is (Saving, Chequing)

    /**
     * default constructor
     */
    public Account() {
        this.balance = 0.0;
        this.id = UUID.randomUUID().toString();
        this.type = "CHEQUING";
    }

    /**
     * @param type	the type of account (SAVINGS, CHEQUING)
     */
    public Account(String type) {
        this.balance = 0.0;
        this.id = UUID.randomUUID().toString();
        this.type = type;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                ", type='" + type + '\'' +
                '}';
    }

    /**
     * verifies that two accounts are equal
     * @param o an Account object that is passed
     * @return true / false depending on if accounts are equal
     */
    @Override
    public boolean equals(Object o) {
    	/* compares balance, id, and type */
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Double.compare(account.balance, balance) == 0 &&
                id.equals(account.id) &&
                type.equals(account.type);
    }
    
    /**
     * @return hash for an account
     */
    @Override
    public int hashCode() {
    	/* used to speed up Java collections management
         * e.g. List<Account> */
        return Objects.hash(id, balance, type);
    }

    /**
     * @return id of an account
     */
    public String getId() {
        return id;
    }

    /**
     * @return balance of an account
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance balance to set (in dollars)
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * @return	account type (SAVINGS, CHEQUING)
     */
    public String getType() {
        return type;
    }

    /**
     * @param type	account type (SAVINGS, CHEQUING)
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return substring of an account id
     */
    @JsonIgnore
    public String getAccountNo(){
        return this.id.substring(0,8).toUpperCase();
    }
}
