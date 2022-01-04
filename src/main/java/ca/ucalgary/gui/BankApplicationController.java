package ca.ucalgary.gui;

// Imports
import ca.ucalgary.domain.Customer;
import ca.ucalgary.gui.customer.CustomerController;
import ca.ucalgary.gui.profile.ProfileController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * BankApplicationController Class
 * controller class for bank fxml
 */
public class BankApplicationController implements Initializable {
   
	// Declare BankApplicationController Variables
	private Customer customer;
    private Stage primaryStage;
    @FXML private AnchorPane body;

    /**
     * initialize
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) { }

    /**
     * set primary stage
     * @param stage
     */
    public void setPrimaryStage(Stage stage){
        this.primaryStage = stage;
    }

    /**
     * set customer
     * @param customer
     */
    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    /**
     * bottom bar button event to load accounts stage into body of application
     * @param event
     * @throws Exception
     */
    @FXML
    public void accountButton(ActionEvent event) throws Exception{
    	try {
    		BankApplication.getCustomer().getId();
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/accounts.fxml"));
	        Parent customerParent = (Parent)loader.load();
	        CustomerController controller = loader.<CustomerController>getController();
	        controller.populateForm(BankApplication.getCustomer());
	        controller.setString("Accounts");
	        body.getChildren().setAll(customerParent);
    	} catch (Exception e)  {
	    	// user must first create an account
	    }
    }

    /**
     * bottom bar button event to load budget stage into body of application
     * @param event
     * @throws Exception
     */
    @FXML
    public void budgetButton(ActionEvent event) throws Exception{
	    try {    
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/Budget.fxml"));
	        Parent budgetParent = (Parent)loader.load();
	        body.getChildren().setAll(budgetParent);
    	} catch (Exception e)  {
	    	// user must first create an account
	    }    
    }

    /**
     * bottom bar button event to load invest stage into body of application
     * @param event
     * @throws Exception
     */
    @FXML
    public void investButton(ActionEvent event) throws Exception{
    	try {
    		BankApplication.getCustomer().getId();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/investMain.fxml"));
            Parent budgetParent = (Parent)loader.load();
            body.getChildren().setAll(budgetParent);
    	} catch (Exception e) {
    		// user must first create an account
    	}
    }

    /**
     * bottom bar button event to load profile stage into body of application
     * @param event
     * @throws Exception
     */
    @FXML
    public void profileButton(ActionEvent event) throws Exception{
    	try {
    		BankApplication.getCustomer().getId();
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/profile.fxml"));
	        Parent profileParent = (Parent)loader.load();
	        ProfileController controller = loader.<ProfileController>getController();
	        controller.setProfile(BankApplication.getCustomer());
	        body.getChildren().setAll(profileParent);
    	} catch (Exception e)  {
	    	// user must first create an account
	    }
    }

    /**
     * change the scene (body) in the stage 
     * @param fxmlScene
     * @return
     * @throws Exception
     */
    public FXMLLoader changeBody(String fxmlScene) throws Exception{
        FXMLLoader loader = new FXMLLoader((BankApplicationController.class.getResource(fxmlScene)));
        Parent accountParent = (Parent)loader.load();
        body.getChildren().setAll(accountParent);
        return loader;
    }

}
