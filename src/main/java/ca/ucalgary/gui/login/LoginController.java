package ca.ucalgary.gui.login;

import ca.ucalgary.domain.Customer;
import ca.ucalgary.gui.BankApplication;
import ca.ucalgary.gui.customer.CustomerController;
import ca.ucalgary.services.BankService;
import ca.ucalgary.services.RepositoryService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * LoginController
 * controller class for Login
 */
public class LoginController implements Initializable {
	
	// Declare Variables
    private BankService bankService = new BankService();
    private Customer customer = null;
    private String message;
    private RepositoryService recoveredService = new RepositoryService();
    public TextField firstName;
    public TextField lastName;
    public TextField emailSignUp;
    public PasswordField passwordSignUp;
    public TextField emailSignIn;
    public PasswordField passwordSignIn;
    public Label testLabel;
    public AnchorPane main;

    /**
     * initialize
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstName.setText(message);
    }

    /**
     * processes user once they press "sign up" within the GUI
     * @throws Exception
     */
    @FXML
    private void signUp() throws Exception{
        if(verifySignUpFields()) {
            customer = bankService.signUpCustomer(firstName.getText(), lastName.getText(), emailSignUp.getText().toLowerCase(), passwordSignUp.getText());
            recoveredService.saveAllRepositories();
            System.out.println(customer);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/accounts.fxml"));
            Parent customerParent = (Parent) loader.load();
            CustomerController controller = loader.<CustomerController>getController();
            BankApplication.setCustomer(customer);
            controller.setCustomer(customer);
            controller.populateForm(customer);
            controller.setString("Accounts");
            main.getChildren().setAll(customerParent);
        } else {
            System.out.println("Not all fields entered.");
        }
    }

    /**
     * verifies that sign up fields are valid
     * @return true / false depending on if fields are valid
     */
    private boolean verifySignUpFields(){
        return !(firstName.getText().trim().isEmpty() || lastName.getText().trim().isEmpty() || emailSignUp.getText().trim().isEmpty() || passwordSignUp.getText().trim().isEmpty());
    }

    /**
     * allows user to sign in inside of GUI application
     * @param event
     * @throws Exception
     */
    @FXML
    private void signIn(ActionEvent event) throws Exception{
        if(verifySignInFields()) {
            customer = bankService.signIn(emailSignIn.getText().toLowerCase(), passwordSignIn.getText());
            if (customer != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/accounts.fxml"));
                Parent customerParent = (Parent) loader.load();
                CustomerController controller = loader.<CustomerController>getController();
                BankApplication.setCustomer(customer);
                controller.populateForm(customer);
                controller.setString("Accounts");
                main.getChildren().setAll(customerParent);
            } else {
                System.out.println("Invalid Credentials.");
            }
        }
    }

    /**
     * verifies that sign in credentials exist in local data-store
     * @return true / false depending on if credentials exist
     */
    private boolean verifySignInFields(){
        return !(emailSignIn.getText().trim().isEmpty() || passwordSignIn.getText().trim().isEmpty());
    }

    /**
     * sets a message for user when they first log on
     * @param message
     */
    public void setMessage(String message){
        this.message = message;
        testLabel.setText(message);
        System.out.println(message);
    }
}
