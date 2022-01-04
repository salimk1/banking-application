package ca.ucalgary.services;

import ca.ucalgary.datastore.AccountTransactionRepository;
import ca.ucalgary.domain.AccountTransaction;
import java.util.List;

/**
 * AccountTransactionService
 * service class for AccountTransaction
 */
public class AccountTransactionService {
	
	/**
	 * getAllTransactionsOfAllAccounts
	 * @return every transaction for every account within the database
	 */
    public List<AccountTransaction> getAllTransactionsOfAllAccounts(){
        return AccountTransactionRepository.getAllTransactionsOfAllAccounts();
    }

	/**
	 * getAllTransactions
	 * @param accountId account id
	 * @return all transactions of account based on id
	 */
	public List<AccountTransaction> getAllTransactions(String accountId){
    	return AccountTransactionRepository.getAllAccountTransactions(accountId);
	}
}
