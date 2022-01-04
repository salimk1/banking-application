package ca.ucalgary.datastore;

import ca.ucalgary.domain.AccountTransaction;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class represents the repository of all the account transactions
 */
public class AccountTransactionRepository {

    private static Map<String, AccountTransaction> accountTransactions = new HashMap<>();

    /**
     * add an account transaction
     * @param accountTransaction an AccountTransaction object that gets added to the accountTransactions map
     */
    public static void addAccountTransaction(AccountTransaction accountTransaction){
        accountTransactions.put(accountTransaction.getId(), accountTransaction);
    }

    /**
     * get account transaction
     * @param accountTransactionId the id of a transaction
     * @return the account transaction
     */
    public static AccountTransaction getAccountTransaction(String accountTransactionId){
        return accountTransactions.get(accountTransactionId);
    }

    /**
     * get all account transactions
     * @return all transactions
     */
    public static List<AccountTransaction> getAllAccountTransactions() {
        return accountTransactions.values().stream().collect(Collectors.toList());
    }

    /**
     * get all account transactions, provided you have the account id
     * @param accountId an account id
     * @return all transactions for that specific account (based on id)
     */
    public static List<AccountTransaction> getAllAccountTransactions(String accountId) {
        return accountTransactions.values()
                            .stream()
                            .filter(accountTransaction -> accountTransaction.getAccountId().equals(accountId))
                            .collect(Collectors.toList());
    }

    /**
     * get all transactions of all accounts
     * @return all transactions for all accounts within the database
     */
    public static List<AccountTransaction> getAllTransactionsOfAllAccounts(){
        return accountTransactions.values().stream().collect(Collectors.toList());
    }

    /**
     * set all account transactions
     * @param transactions list of transactions
     */
    public static void setAllAccountTransactions(List<AccountTransaction> transactions){
        System.out.println("transactions " + transactions);
        accountTransactions = transactions.stream().collect(Collectors.toMap(AccountTransaction::getId,accountTransaction -> accountTransaction));

    }
}
