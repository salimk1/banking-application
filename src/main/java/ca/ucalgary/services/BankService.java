package ca.ucalgary.services;

import ca.ucalgary.domain.Customer;
import ca.ucalgary.domain.CustomerAccess;

/**
 * BankService
 * service class for Bank
 */
public class BankService {

	// Declare variables 
    private CustomerService customerService = new CustomerService();
    private CustomerAccessService customerAccessService = new CustomerAccessService();

    /**
     * signs up a customer
     * @param firstName first name of user
     * @param lastName last name of user
     * @param email email of user
     * @param password password of user
     * @return customer that has been signed up
     */
    public Customer signUpCustomer(String firstName, String lastName, String email, String password){
        Customer customer = customerService.createCustomer(firstName,lastName,email);
        customerAccessService.createCustomerAccess(customer.getId(),email,password);

        return customer;
    }

    /**
     * signs in a customer
     * @param email email of user
     * @param password password of user
     * @return customer that has been signed in
     */
    public Customer signIn(String email, String password){
        CustomerAccess customerAccess = null;
        Customer customer = null;
        try {
            customerAccess = customerAccessService.authenticate(email, password);
            customer = customerService.getCustomerById(customerAccess.getCustomerId());
        } catch(Exception e){
            return customer;
        }
        return customer;
    }

}
