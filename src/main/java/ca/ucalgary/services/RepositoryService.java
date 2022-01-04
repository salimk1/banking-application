package ca.ucalgary.services;

import ca.ucalgary.datastore.AccountRepository;
import ca.ucalgary.datastore.AccountTransactionRepository;
import ca.ucalgary.datastore.CustomerAccessRepository;
import ca.ucalgary.datastore.CustomerRepository;
import ca.ucalgary.domain.Account;
import ca.ucalgary.domain.AccountTransaction;
import ca.ucalgary.domain.Customer;
import ca.ucalgary.domain.CustomerAccess;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.List;

/**
 * RepositoryService
 * service class for Repository
 */
public class RepositoryService {

    /**
     * saves all repositories to local json files in data-stores
     */
    public void saveAllRepositories() {

        createDataStoresIfNotExist();

        try {
            ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            List<Account> accounts = AccountRepository.getAllAccounts();
            String JSON = mapper.writeValueAsString(accounts);
            System.out.println(JSON);

            /* write string to file */

            try {
                Files.write(Paths.get("data-stores/account-repository.json"), mapper.writeValueAsBytes(accounts), new OpenOption[]{StandardOpenOption.TRUNCATE_EXISTING});

            } catch (IOException e) {
                e.printStackTrace();
            }

            List<AccountTransaction> accountTransactions = AccountTransactionRepository.getAllAccountTransactions();
            JSON = mapper.writeValueAsString(accountTransactions);
            System.out.println(JSON);

            try {
                Files.write(Paths.get("data-stores/accounttransactions-repository.json"), mapper.writeValueAsBytes(accountTransactions), new OpenOption[]{StandardOpenOption.TRUNCATE_EXISTING});

            } catch (IOException e) {
                e.printStackTrace();
            }

            List<Customer> customers = CustomerRepository.getAllCustomers();
            JSON = mapper.writeValueAsString(customers);
            System.out.println(JSON);

            try {
                Files.write(Paths.get("data-stores/customer-repository.json"), mapper.writeValueAsBytes(customers), new OpenOption[]{StandardOpenOption.TRUNCATE_EXISTING});

            } catch (IOException e) {
                e.printStackTrace();
            }

            List<CustomerAccess> customerAccess = CustomerAccessRepository.getAllCustomerAccess();
            JSON = mapper.writeValueAsString(customerAccess);
            System.out.println(JSON);

            try {
                Files.write(Paths.get("data-stores/customeraccess-repository.json"), mapper.writeValueAsBytes(customerAccess), new OpenOption[]{StandardOpenOption.TRUNCATE_EXISTING});

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * restores repositories from local files
     */
    public void restoreAllRepositories() {

        createDataStoresIfNotExist();

        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        try {
            List<Account> accounts = mapper.readValue(Paths.get("data-stores/account-repository.json").toFile(), new TypeReference<List<Account>>(){});

            accounts.forEach(account -> System.out.println(account));
            AccountRepository.setAllAccounts(accounts);
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            List<AccountTransaction> accountTransactions = mapper.readValue(Paths.get("data-stores/accounttransactions-repository.json").toFile(), new TypeReference<List<AccountTransaction>>(){});

            accountTransactions.forEach(accountTransaction -> System.out.println(accountTransaction));
            AccountTransactionRepository.setAllAccountTransactions(accountTransactions);
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            List<Customer> customers = mapper.readValue(Paths.get("data-stores/customer-repository.json").toFile(), new TypeReference<List<Customer>>(){});

            customers.forEach(customer -> System.out.println(customer));
            CustomerRepository.setAllCustomers(customers);
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            List<CustomerAccess> customerAccesses = mapper.readValue(Paths.get("data-stores/customeraccess-repository.json").toFile(), new TypeReference<List<CustomerAccess>>(){});

            customerAccesses.forEach(customerAccess -> System.out.println(customerAccess));
            CustomerAccessRepository.setAllCustomerAccess(customerAccesses);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param path the path of the file location
     * @return the location of file in File format
     * @throws URISyntaxException
     */
    public File getFile(String path) throws URISyntaxException {
        URL url = AccountRepository.class.getClassLoader().getResource(path);
        Path location = Paths.get(url.toURI());

        return location.toFile();

    }

    /**
     * Creates data stores if they do not already exist
     * allows code to compile without errors
     */
    public void createDataStoresIfNotExist() {
        Path path = Paths.get("data-stores");
        /* if directory exists? */
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {

            if (Files.notExists(Paths.get("data-stores/account-repository.json"))) {
                Files.write(Paths.get("data-stores/account-repository.json"), "[]".getBytes());
            }
            if (Files.notExists(Paths.get("data-stores/accounttransactions-repository.json"))) {
                Files.write(Paths.get("data-stores/accounttransactions-repository.json"), "[]".getBytes());
            }
            if (Files.notExists(Paths.get("data-stores/customer-repository.json"))) {
                Files.write(Paths.get("data-stores/customer-repository.json"), "[]".getBytes());
            }
            if (Files.notExists(Paths.get("data-stores/customeraccess-repository.json"))) {
                Files.write(Paths.get("data-stores/customeraccess-repository.json"), "[]".getBytes());
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }

}
