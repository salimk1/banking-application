package ca.ucalgary.gui;

// Imports
import ca.ucalgary.domain.Customer;
import ca.ucalgary.services.RepositoryService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Representation of the actual GUI Bank Application
 * Using FXML to work with Controllers and have different "scenes" for a user to access
 */
public class BankApplication extends Application {

	// Declare BankApplication Variables
    private RepositoryService repositoryService = new RepositoryService();
    private static Customer customer;

    /**
     * main
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * start
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        repositoryService.restoreAllRepositories();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/bank.fxml"));
        Parent content = (Parent)fxmlLoader.load();
        Scene bankScene = new Scene(content);
        primaryStage.setScene(bankScene);
        BankApplicationController bankController = fxmlLoader.<BankApplicationController>getController();
        bankController.setPrimaryStage(primaryStage);
        primaryStage.show();
    }

    /**
     * saves all repositories to data-store once user quits application
     * @throws Exception
     */
    @Override
    public void stop() throws Exception {
        repositoryService.saveAllRepositories();
        super.stop();
    }

    /**
     * sets the context customer
     * @param c customer
     */
    public static void setCustomer(Customer c){
        customer = c;
    }

    /**
     * gets the context customer
     * @return customer
     */
    public static Customer getCustomer() {
        return customer;
    }
    
}
