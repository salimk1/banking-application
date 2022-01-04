package ca.ucalgary.gui.profile;

import ca.ucalgary.domain.Customer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * ProfileController
 * controller class for profile 
 */
public class ProfileController implements Initializable {
	
	// Declare Variables 
    private Customer customer;
    @FXML
    private AnchorPane main;
    public Label name;
    public Label fname;
    public Label lname;
    public Label clientId;

    /**
     * initialize
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) { }

    /**
     * sets the profile stage of the application with the user's information
     * @param c context customer
     */
    @FXML
    public void setProfile(Customer c){
        this.fname.setText(c.getFirstName());
        this.lname.setText(c.getLastName());
        this.clientId.setText(c.getEmail());
    }

    /**
     * setter for customer
     * @param customer
     */
    public void setCustomer(Customer customer){
        this.customer = customer;
    }
    
}