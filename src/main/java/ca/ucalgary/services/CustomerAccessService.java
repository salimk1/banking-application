package ca.ucalgary.services;

import ca.ucalgary.datastore.CustomerAccessRepository;
import ca.ucalgary.domain.CustomerAccess;

/**
 * CustomerAccessService
 * service class for CustomerAccess
 */
public class CustomerAccessService {

    /**
     * @return an customerAccess that has been created and added to the CustomerAccessRepository
     */
    public CustomerAccess createCustomerAccess(String customerId, String email, String password) {
        CustomerAccess customerAccess = new CustomerAccess(customerId,email,password);
        CustomerAccessRepository.addCustomerAccess(customerAccess);

        return customerAccess;
    }

    /**
     * @param customerId customer id
     * @return customer access by customer id
     */
    public CustomerAccess getCustomerAccessById(String customerId){
        return CustomerAccessRepository.getCustomerAccess(customerId);
    }

    /**
     * @param email user's email
     * @param password user's password
     * @return customer access for user's email / password
     */
    public CustomerAccess authenticate(String email, String password){
        return CustomerAccessRepository.getAccessFor(email, password);
    }

}
