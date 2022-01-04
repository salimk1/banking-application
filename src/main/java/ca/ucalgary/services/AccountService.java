package ca.ucalgary.services;

import ca.ucalgary.datastore.AccountRepository;
import ca.ucalgary.datastore.AccountTransactionRepository;
import ca.ucalgary.domain.Account;
import ca.ucalgary.domain.AccountTransaction;

import java.util.List;

/**
 * AccountService
 * Exposes actions applied onto an account
 * Such as: create, update, delete, withdraw, deposit...
 */
public class AccountService {

    //private static AccountRepository accountRepository = new AccountRepository();

    /**
     * @return an account that has been created and added to the accountRepository
     */
    public Account createAccount() {
        Account account = new Account();
        AccountRepository.addAccount(account);
        AccountTransaction accountTransaction = new AccountTransaction(account.getId(),"CREATE",account.getBalance());
        AccountTransactionRepository.addAccountTransaction(accountTransaction);

        return account;
    }

    /**
     * 
     * @param type the type of account
     * @return an account that has been created and added to the accountRepository
     */
    public Account createAccount(String type) {
        Account account = new Account(type);
        AccountRepository.addAccount(account);
        AccountTransaction accountTransaction = new AccountTransaction(account.getId(),"CREATE",account.getBalance());
        AccountTransactionRepository.addAccountTransaction(accountTransaction);

        return account;
    }

    /**
     * 
     * @param type the type of account
     * @param balance amount in dollars to initialize in account's balance
     * @return an account that has been created and added to the accountRepository
     */
    public Account createAccount(String type, double balance) {
        Account account = new Account(type);
        account.setBalance(balance);
        AccountRepository.addAccount(account);
        AccountTransaction accountTransaction = new AccountTransaction(account.getId(),"CREATE",account.getBalance());
        AccountTransactionRepository.addAccountTransaction(accountTransaction);

        return account;
    }

    /**
     * 
     * @param accountId the account id of a specific account to return
     * @return the account based on it's id, found within the accountRepository map
     */
    public Account getAccount(String accountId) {
        return AccountRepository.getAccount(accountId);
    }

    /**
     * 
     * @param accountId the account id of a specific account to delete
     */
    public void deleteAccount(String accountId) {
        Account a = AccountRepository.getAccount(accountId);
        AccountTransaction accountTransaction = new AccountTransaction(accountId,"DELETE", a.getBalance());
        AccountTransactionRepository.addAccountTransaction(accountTransaction);

        AccountRepository.deleteAccount(accountId);
    }

    /**
     * 
     * @param accountId the account id for the account that is to be updated
     * @param type the type of account
     * @return the updated account, based on it's id and type, found within the accountRepository map
     */
    public Account updateAccount(String accountId, String type) {
        return AccountRepository.updateAccount(accountId, type);
    }

    /**
     * 
     * @param accountId the account id for the account you want to deposit to
     * @param amount the amount of money (in dollars) to deposit
     * @return the updated account + balance
     */
    public Account deposit(String accountId, double amount){
        /* Step 1: Fetch the account POJO from datastore
           Step 2: Create a new account transaction of type deposit
           Step 3: Update the account POJO amount balance
           Step 4: Save the account balance back into the datastore
           Step 5: Update the account transaction status to success or failure
           Step 6: Save the account transaction to the datastore */
        Account account = AccountRepository.getAccount(accountId);
        double finalBalance = account.getBalance() + amount;
        AccountTransaction accountTransaction = new AccountTransaction(account.getId(),"DEPOSIT",amount);
        AccountTransactionRepository.addAccountTransaction(accountTransaction);

        return AccountRepository.updateAccount(accountId, finalBalance);
    }

    /**
     * 
     * @param accountId the account id for the account you want to withdraw from
     * @param amount the amount of money (in dollars) to withdraw (must be less or equal to the current balance of the account)
     * @return the updated account + balance
     */
    public Account withdraw(String accountId, double amount){
        Account account = AccountRepository.getAccount(accountId);
        if(amount <= account.getBalance()) {
            double finalBalance = account.getBalance() - amount;
            AccountTransaction accountTransaction = new AccountTransaction(account.getId(),"WITHDRAW",amount);
            AccountTransactionRepository.addAccountTransaction(accountTransaction);

            return AccountRepository.updateAccount(accountId, finalBalance);
        } else {
            throw new RuntimeException("Not enough funds.");
        }
    }

    /**
     * 
     * @param accountId the account id for the account you want to display the transactions for
     * @return the transactions for the specific account based on id
     */
    public List<AccountTransaction> getAllAccountTransactions(String accountId){
        return AccountTransactionRepository.getAllAccountTransactions(accountId);
    }

    /**
     * 
     * @return the repository list for all accounts
     */
    public List<Account> getAllAccounts(){
        return AccountRepository.getAllAccounts();
    }

    /**
     *
     * @param accountIds list of account ids
     * @return list of accounts, based on the account ids passed
     */
    public List<Account> getAllAccountsByIds(List<String> accountIds){
        return AccountRepository.getAllAccountsByIds(accountIds);
    }
}
