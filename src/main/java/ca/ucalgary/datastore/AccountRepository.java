package ca.ucalgary.datastore;

import ca.ucalgary.domain.Account;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class represents the repository of all the accounts
 */
public class AccountRepository {

    /**
     * Represents a HashMap that stores accounts, based on their 1) id and 2) account properties
     * String = key, Account = object
     * String is the UUID (identifier of an account)
     */
    private static Map<String,Account> store = new HashMap<>();

    /**
     * 
     * @param account an account object
     * @return the account, after it is added to the account store
     */
    public static Account addAccount(Account account){
        store.put(account.getId(), account);

        return account;
    }

    /**
     * returns an account, provided it exists in the account store
     * @param accountId the id of an account
     * @return the account based on it's id, provided it exists in the account store
     */
    public static Account getAccount(String accountId) {
        if(store.containsKey(accountId)){
            return store.get(accountId);
        }

        throw new RuntimeException("This account ID does not exist and cannot be returned: " + accountId);
    }

    /**
     * deletes an account from the account store
     * @param accountId the id of an account
     */
    public static void deleteAccount(String accountId) {
        if(store.containsKey(accountId)){
            store.remove(accountId);
            return;
        }

        throw new RuntimeException("This account ID does not exist and cannot be deleted: " + accountId);
    }

    /**
     * 
     * @param accountId the id of an account
     * @param balance the balance to be updated
     * @return the updated account
     */
    public static Account updateAccount(String accountId, double balance) {
        if(store.containsKey(accountId)){
            Account accountToUpdate = store.get(accountId);
            accountToUpdate.setBalance(balance);

            return accountToUpdate;
        }

        throw new RuntimeException("This account ID does not exist and cannot be updated: " + accountId);
    }

    /**
     * 
     * @param accountId the id of an account
     * @param type the type to be updated
     * @return the updated account
     */
    public static Account updateAccount(String accountId, String type) {
        if(store.containsKey(accountId)) {
            Account accountToUpdate = store.get(accountId);
            accountToUpdate.setType(type);

            return accountToUpdate;
        }

        throw new RuntimeException("This account ID does not exist and cannot be updated: " + accountId);
    }

    /**
     * 
     * @param accountId the id of an account
     * @param type the type to be updated
     * @param balance the balance to be updated
     * @return the updated account
     */
    public static Account updateAccount(String accountId, double balance, String type) {
        if(store.containsKey(accountId)) {
            Account accountToUpdate = store.get(accountId);
            accountToUpdate.setBalance(balance);
            accountToUpdate.setType(type);

            return accountToUpdate;
        }

        throw new RuntimeException("This account ID does not exist and cannot be updated: " + accountId);
    }

    /**
     * 
     * @return all accounts in a list
     */
    public static List<Account> getAllAccounts(){
        return store.values().stream().collect(Collectors.toList());
    }

    /**
     * 
     * @param accounts an account object
     */
    public static void setAllAccounts(List<Account> accounts){
        store = accounts.stream().collect(Collectors.toMap(Account::getId,account -> account));
    }

    /**
     * print all accounts
     */
    public static void printAllAccounts(){
        store.entrySet().stream().forEach(entry -> System.out.println(entry.getValue()));
    }

    /**
     * get all accounts by ids
     * @param accountIds list of account ids
     * @return list of accounts
     */
    public static List<Account> getAllAccountsByIds(List<String> accountIds){
        return store.entrySet().stream().filter(entry -> accountIds.contains(entry.getKey())).map(entry -> entry.getValue()).collect(Collectors.toList());
    }


}

