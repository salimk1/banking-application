package ca.ucalgary.tui;

// Imports
//import ca.ucalgary.domain.Account;
//import ca.ucalgary.domain.AccountTransaction;
import ca.ucalgary.domain.Account;
import ca.ucalgary.domain.AccountTransaction;
import ca.ucalgary.domain.Customer;
import ca.ucalgary.services.AccountService;
import ca.ucalgary.services.AccountTransactionService;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * AccountCLI 
 * Contains Account CLI 
 */
public class AccountCLI {

	// Declare Variables
    private AccountService accountService = new AccountService();
    private AccountTransactionService accountTransactionService = new AccountTransactionService();

    /**
     * processAccountsFor
     * @param customer
     */
    public void processAccountsFor(Customer customer){
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        Account account = null;
        
        boolean run = true;
        while (run) {
            System.out.println("--------------------------------------------\n" +
                               "[1] create an account    [4] display all transactions    [7] display every transaction ever     |     amount of accounts:        " + this.getNumberOfAccounts() + "\n" +
                               "[2] deposit              [5] display all accounts        [8]                                    |     current account id:        " + this.getAccountIdIfExists(account) + "\n" +
                               "[3] withdraw             [6] switch account              [q] quit                               |     current account balance:   " + this.getAccountBalIfExists(account) +"\n");
            
			// selection 
            System.out.print("Enter Selection: ");
            String operation = scanner.nextLine();
           
			while (!(operation.equals("1")||operation.equals("2")||operation.equals("3") ||operation.equals("4") 
					||operation.equals("5")||operation.equals("6")||operation.equals("7")||operation.equals("8") 
					||operation.equals("q"))) {
				// invalid input
				System.out.println("Invalid Input! Try again... \n");
				System.out.print("Enter Selection: ");
				operation = scanner.nextLine();
			}

            switch (operation) {
                case "1":
                    System.out.println("Enter type of account: (CHEQUING/SAVINGS)");
                    String accountType = scanner.nextLine().toUpperCase();
                    if (validateAccountType(accountType)) {
                        account = this.create(customer, accountType);
                        System.out.println("Account created: " + account.getId());
                    } else {
                        System.out.println("Not a valid account type");
                    }
                    break;
                case "2":
                    System.out.println("How much do you want to deposit?");
                    String depositAmount = scanner.nextLine();
                    this.deposit(account, Double.parseDouble(depositAmount));
                    System.out.println("Deposited " + depositAmount + ". New balance: " + account.getBalance());
                    break;
                case "3":
                    System.out.println("How much do you want to withdraw?");
                    String withdrawAmount = scanner.nextLine();
                    this.withdraw(account, Double.parseDouble(withdrawAmount));
                    System.out.println("Withdrew " + withdrawAmount + ". New balance: " + account.getBalance());
                    break;
                case "4":
                    this.displayAllTransactions(account);
                    break;
                case "5":
                    this.showAccounts(customer);
                    break;
                case "6":
                    this.showAccounts(customer);
                    System.out.println("Enter the first n characters of the account's ID:");
                    String accountIdPart = scanner.nextLine();
                    account = this.findAccount(this.accountService.getAllAccounts(), accountIdPart);
                    break;
                case "7":
                    this.displayAllTransactionsOfAllAccounts();
                    break;
                case "q":
                    run = false;
                    break;
                default: // will never run
                    break;
            }
        }
    }

    /**
     * validateAccountType
     * @param accountType
     * @return
     */
    public boolean validateAccountType(String accountType){
        return accountType.equals("CHEQUING") || accountType.equals("SAVINGS");
    }

    /**
     * create
     * @param customer
     * @param accountType
     * @return
     */
    public Account create(Customer customer, String accountType){
        Account account = accountService.createAccount(accountType);
        customer.addAccountId(account.getId());
        return account;
    }

    /**
     * deposit
     * @param account
     * @param amount
     */
    public void deposit(Account account, double amount){
        accountService.deposit(account.getId(), amount);
    }

    /**
     * withdraw
     * @param account
     * @param amount
     */
    public void withdraw(Account account, double amount){
        accountService.withdraw(account.getId(), amount);
    }

    /**
     * displayAllTransactions
     * @param account
     */
    public void displayAllTransactions(Account account){
        accountService.getAllAccountTransactions(account.getId())
                    .stream()
                    .sorted(Comparator.comparing(AccountTransaction::getTxDate))
                    .forEach(accountTransaction -> System.out.println(accountTransaction));
    }

    /**
     * showAccounts
     * @param customer
     */
    public void showAccounts(Customer customer){
        accountService.getAllAccountsByIds(customer.getCustomerAccountIds()).forEach(account -> System.out.println(account));
    }

    /**
     * getNumberOfAccounts
     * @return
     */
    public int getNumberOfAccounts(){
        return accountService.getAllAccounts().size();
    }

    /**
     * findAccount
     * @param accounts
     * @param accountIdPart
     * @return
     */
    private Account findAccount(List<Account> accounts, String accountIdPart) {
        return accounts.stream().filter(account -> account.getId().startsWith(accountIdPart))
                .findFirst().orElseThrow(() -> new RuntimeException("Account not found."));
    }

    /**
     * getAccountIdIfExists
     * @param account
     * @return
     */
    private String getAccountIdIfExists(Account account){
        if (account != null) return account.getId();
        else return null;
    }

    /**
     * getAccountBalIfExists
     * @param account
     * @return
     */
    private double getAccountBalIfExists(Account account){
        if (account != null) return account.getBalance();
        else return 0.0;
    }

    /**
     * displayAllTransactionsOfAllAccounts
     */
    public void displayAllTransactionsOfAllAccounts(){
        accountTransactionService.getAllTransactionsOfAllAccounts()
                                .stream().sorted(Comparator.comparing(AccountTransaction::getTxDate))
                                .forEach(accountTransaction -> System.out.println(accountTransaction));
    }
}
