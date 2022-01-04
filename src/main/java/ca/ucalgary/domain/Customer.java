package ca.ucalgary.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Representation of a user for the application
 */
public class Customer {

	// Declare Variables 
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    @JsonProperty("accountIds")
    private List<String> accountIds = new ArrayList<>();

    /**
     * For JSON Serialization; never used
     * JSON library needs to create an empty Customer
     */
    public Customer(){

    }

    /**
     *
     * @param firstName first name of user
     * @param lastName last name of user
     * @param email email of user
     */
    public Customer(String firstName, String lastName, String email){
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * @return id of a user
     */
    public String getId() {
        return id;
    }

    /**
     * @return first name of a user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName first name to set for a user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return last name of a user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName last name to set for a user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return email of a user
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email email to set for a user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param accountId account id of a user to add
     */
    public void addAccountId(String accountId){
        accountIds.add(accountId);
    }

    /**
     * @param accountId account id of a user to remove
     */
    public void removeAccountId(String accountId){
        accountIds.remove(accountId);
    }

    /**
     * @return a list of account ids
     */
    public List<String> getCustomerAccountIds(){
        // make a copy of list
        return accountIds;
    }

    /**
     * @param accountId the id of an account
     * @return true / false depending on if account exists
     */
    public boolean accountIdExists(String accountId){
        return accountIds.contains(accountId);
    }

    /**
     * compares if two users are equal
     * @param o user to pass
     * @return true / false depending on if users are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id) &&
                firstName.equals(customer.firstName) &&
                lastName.equals(customer.lastName) &&
                email.equals(customer.email);
    }

    /**
     * @return hash for a specific id, first name, last name, email
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }

    /** 
     * toString
     * @return String 
     */
    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", accountIds=" + accountIds +
                '}';
    }
}
