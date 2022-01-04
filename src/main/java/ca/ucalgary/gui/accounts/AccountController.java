package ca.ucalgary.gui.accounts;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * AccountController
 * controller class for Account feature
 */
public class AccountController implements Initializable {

	// Declare Variables 
    @FXML private TextField initialDeposit;
    @FXML private ChoiceBox<String> accountType;

    /**
     * initialize
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /** 
     * creatAccount
     * @param event
     * @throws Exception
     */
    @FXML
    private void createAccount(ActionEvent event) throws Exception{
        String type = accountType.getValue();
        double balance = Double.parseDouble(initialDeposit.getText());
    }

}
