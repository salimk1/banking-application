package ca.ucalgary.services;

import ca.ucalgary.datastore.CustomerRepository;
import ca.ucalgary.domain.Customer;
import java.util.List;

/**
 * CustomerService
 * service class for Customer
 */
public class CustomerService {

    /**
     * @return an customer that has been created and added to the CustomerRepository
     */
    public Customer createCustomer(String firstName, String lastName, String email) {
        Customer customer = new Customer(firstName,lastName,email);
        CustomerRepository.addCustomer(customer);

        return customer;
    }

    /**
     * @param customerId customer id
     * @return customer if customer exists in repository
     */
    public Customer getCustomerById(String customerId){
        return CustomerRepository.getCustomer(customerId);
    }

    /**
     * @return list of all customers in repository
     */
    public List<Customer> getAllCustomers(){
        return CustomerRepository.getAllCustomers();
    }


}
