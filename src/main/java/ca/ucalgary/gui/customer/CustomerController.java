package ca.ucalgary.gui.customer;

import ca.ucalgary.domain.Account;
import ca.ucalgary.domain.Customer;
import ca.ucalgary.gui.accounts.AccountDetailsController;
import ca.ucalgary.services.AccountService;
import ca.ucalgary.services.CustomerService;
import ca.ucalgary.services.RepositoryService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * CustomerController
 * controller class for Customer
 */
public class CustomerController implements Initializable {
	
	// Declare Variables
    private Customer customer;
    private CustomerService customerService = new CustomerService();
    private AccountService accountService = new AccountService();
    private RepositoryService repositoryService = new RepositoryService();
    private String str;
    @FXML private AnchorPane main;
    public Label accountInfo;
    public Label name;
    public TableView accountsTable;
    public TableColumn accountIdColumn;
    public TableColumn accountTypeColumn;
    public TableColumn accountBalanceColumn;
    public TableColumn accountId;
    public ComboBox accountType;

    /** 
     * initialize
     * @param location
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * Adds an account to the customer's account list
     */
    @FXML
    public void addAccount() {
        String accountTypeString;
        if(accountType.getValue() != null){
            accountTypeString = accountType.getValue().toString();
        } else {
            accountTypeString = "Savings";
        }
        Account account = accountService.createAccount(accountTypeString.toUpperCase());
        customer.addAccountId(account.getId());
        repositoryService.saveAllRepositories();
        updateAccountsTable();
        accountInfo.setText(accountTypeString + " Account " + account.getId().substring(0,8).toUpperCase() + " Created");
    }

    /**
     * sets the context customer
     * @param customer
     */
    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    /**
     * fills up the form within the GUI with the user's list of accounts that they own
     * @param customer
     */
    public void populateForm(Customer customer){
        this.customer = customer;
        setName(customer.getFirstName() + " " + customer.getLastName());
        updateAccountsTable();
    }

    /**
     * sets account info within GUI
     * @param str string to be set
     */
    public void setString(String str){
        this.str = str;
        this.accountInfo.setText(str);
    }

    /**
     * sets account name within GUI
     * @param name name of user
     */
    public void setName(String name){
        this.name.setText(name);
    }

    /**
     * Updates the account table within the GUI
     */
    public void updateAccountsTable(){
        accountsTable.getItems().clear();
        accountIdColumn.setCellValueFactory(new PropertyValueFactory<Account,String>("accountNo"));
        accountTypeColumn.setCellValueFactory(new PropertyValueFactory<Account,String>("type"));
        accountBalanceColumn.setCellValueFactory(new PropertyValueFactory<Account,String>("balance"));
        accountId.setCellValueFactory(new PropertyValueFactory<Account,String>("id"));
        List<Account> accountList = accountService.getAllAccountsByIds(customer.getCustomerAccountIds());
        for(Account account : accountList){
            accountsTable.getItems().add(account);
        }
    }

    /**
     * gets the row within the table of the GUI that the user clicks on
     * used for selecting an account
     * @throws Exception
     */
    public void getSelectedRow() throws Exception{
        TablePosition cell = (TablePosition)accountsTable.getSelectionModel().getSelectedCells().get(0);
        System.out.println(((Account)accountsTable.getItems().get(cell.getRow())).getId());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/account-details.fxml"));
        Parent accountDetailsParent = (Parent) loader.load();
        AccountDetailsController controller = loader.<AccountDetailsController>getController();
        controller.setAccount(accountService.getAccount(((Account)accountsTable.getItems().get(cell.getRow())).getId()));
        controller.setCustomer(customer);
        controller.setLabels();
        controller.setAccountTransactions();
        main.getChildren().setAll(accountDetailsParent);
    }

}
