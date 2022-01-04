package ca.ucalgary.datastore;

import ca.ucalgary.domain.Customer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * CustomerRepository Class
 * contains the Customer Repository 
 */
public class CustomerRepository {

	// Declare Variables 
    private static Map<String,Customer> store = new HashMap<>();

    /**
     *
     * @param customer
     * @return customer that has been added
     */
    public static Customer addCustomer(Customer customer){
        store.put(customer.getId(),customer);

        return customer;
    }

    /**
     *
     * @param customerId customer's id
     * @return customer id if it exists in store
     */
    public static Customer getCustomer(String customerId){
        if(store.containsKey(customerId)){
            return store.get(customerId);
        }

        throw new RuntimeException("This customer ID does not exist: " + customerId);
    }

    /**
     * delete's a customer from repo
     * @param customerId customer's id
     */
    public static void deleteCustomer(String customerId) {
        if(store.containsKey(customerId)){
            store.remove(customerId);
            return;
        }

        throw new RuntimeException("This customer ID does not exist and cannot be deleted: " + customerId);
    }

    /**
     * returns list of all customers
     * @return list of customers
     */
    public static List<Customer> getAllCustomers(){
        return store.values().stream().collect(Collectors.toList());
    }

    /**
     * sets all customers
     * @param customers list of customers
     */
    public static void setAllCustomers(List<Customer> customers){
        store = customers.stream().collect(Collectors.toMap(Customer::getId,customer -> customer));
    }

    /**
     * used to print all accounts
     */
    public static void printAllCustomers(){
        store.entrySet().stream().forEach(entry -> System.out.println(entry.getValue()));
    }
}
