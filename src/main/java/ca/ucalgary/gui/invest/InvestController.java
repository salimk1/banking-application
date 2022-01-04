package ca.ucalgary.gui.invest;

// Imports 
import ca.ucalgary.datastore.InvestRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Invest Controller Class
 * controller class for Invest fxml
 */
public class InvestController{
	
	// Declare Menu Scene Variables
	@FXML
	AnchorPane homePane;
	@FXML
	Button btnSS;
	@FXML
	Button btnVAS;
	@FXML
	Button btnVP;
	@FXML
	Button btnI;
	
	// Declare SearchStock Variables
	@FXML
	AnchorPane searchPane;
	@FXML
	TextField getSearch;
	@FXML
	Button btnSearch;
	@FXML
	Label result;
	@FXML
	Button btnBSS;
	
	// Declare ViewAllStocks Variables
	@FXML
	AnchorPane viewPane;
	@FXML
	ScrollPane resultPane;
	@FXML
	Button btnBVAS;
	
	
	// Declare PortfolioMethod Variables
	@FXML
	AnchorPane portfolioPane;
	@FXML
	Label lblMyPortfolio;
	@FXML
	Button btnBP;
	
	// Declare InvestMethod Variables
	@FXML
	AnchorPane buyPane;
	@FXML
	TextField getStock;
	@FXML
	TextField getAmount;
	@FXML
	Button btnPurchase;
	@FXML
	Label txtNotification;
	@FXML
	Button btnBBS;
	
	/**
	 * Back buttons
	 * @param event
	 * @throws IOException
	 */
	@FXML
	private void BackS(ActionEvent event) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/InvestMain.fxml"));
		Parent toMenu = (Parent)fxmlLoader.load();
		searchPane.getChildren().clear();
		searchPane.getChildren().addAll(toMenu );
	}
	@FXML
	private void BackV(ActionEvent event) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/InvestMain.fxml"));
		Parent toMenu = (Parent)fxmlLoader.load();
		viewPane.getChildren().clear();
		viewPane.getChildren().addAll(toMenu);
	}
	@FXML
	private void BackP(ActionEvent event) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/InvestMain.fxml"));
		Parent toMenu = (Parent)fxmlLoader.load();
		portfolioPane.getChildren().clear();
		portfolioPane.getChildren().addAll(toMenu);
	}
	@FXML
	private void BackI(ActionEvent event) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/InvestMain.fxml"));
		Parent toMenu = (Parent)fxmlLoader.load();
		buyPane.getChildren().clear();
		buyPane.getChildren().addAll(toMenu);
	}
	
	/**
	 * SearchStock button
	 * @param event
	 * @throws IOException
	 */
	@FXML
	private void SearchStock(ActionEvent event) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/SearchStock.fxml"));
		Parent addParent = (Parent)fxmlLoader.load();
		homePane.getChildren().clear();
		homePane.getChildren().addAll(addParent);
	}
	
	/**
	 * ViewStocks button
	 * @param event
	 * @throws IOException
	 */
	@FXML
	private void ViewStocks(ActionEvent event) throws IOException{
		
		// declare variables
		VBox verti; 
		ScrollPane resultPane11;
		Label result11; 
		InvestRepository myInvestRepository;
		String[] line;
		String txtString;
		
		// initialize variables
		verti = new VBox();
		resultPane11 = new ScrollPane();
		result11 = new Label();
		myInvestRepository = new InvestRepository();
		line = new String[5];
		txtString = "";
		
		// display all stocks
		for (String stock : myInvestRepository.getStocksList()) {
			line = stock.split(",");
			txtString += line[0] + " - " + line[1] + "\n";
		}
		
		// set text 
		result11.setText(txtString);
		resultPane11.setContent(result11);
		
		// set verti
		verti.getChildren().add(new Label("Available Stocks"));
		verti.getChildren().add(resultPane11);
		verti.setAlignment(Pos.TOP_CENTER);
		verti.setSpacing(15);		
		
		// set stage
		Stage searchWindow = new Stage();
		searchWindow.setScene(new Scene(verti, 300, 400));
		searchWindow.show();
		
	}
	
	/**
	 * Portfolio button
	 * @param event
	 * @throws IOException
	 */
	@FXML
	private void Portfolio(ActionEvent event) throws IOException{
		
		// declare variables
		InvestRepository myInvestRepository;
		String[] line;
		String txtString;
		
		// initialize variables
		myInvestRepository = new InvestRepository();
		line = new String[2];
		txtString = "";
		
		// display all stocks in portfolio
		for (String stock : myInvestRepository.getPortfolioList()) {
			line = stock.split(";");
			txtString += line[0] + ": " + line[1] + " Shares \n";
		}
		
		if (myInvestRepository.getPortfolioList().size() == 0) {
			txtString = "none.";
		}
		
		// set VBox scene
		VBox verti = new VBox();

		verti.getChildren().add(new Label("My Portfolio"));
		verti.getChildren().add(new Label(txtString));
		verti.setAlignment(Pos.TOP_CENTER);
		verti.setSpacing(15);		
		
		// stage
		Stage searchWindow = new Stage();
		searchWindow.setScene(new Scene(verti, 300, 400));
		searchWindow.show();
		
	}
	
	/**
	 * BuyStocks button
	 * @param event
	 * @throws IOException
	 */
	@FXML
	private void BuyStocks(ActionEvent event) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/BuyStocks.fxml"));
		Parent addParent = (Parent)fxmlLoader.load();
		homePane.getChildren().clear();
		homePane.getChildren().addAll(addParent);
	}
	
	/**
	 * Search method
	 * @param event
	 * @throws Exception
	 */
	@FXML
	private void SearchMethod(ActionEvent event) throws Exception{
		
		// declare variables	
		InvestRepository myInvestRepository;
		String[] line;
		String userInput, textViewText;  
		
		// initialize variables
		myInvestRepository = new InvestRepository();
		line = new String[9];
		userInput = "";
		textViewText = "";
		
		// get symbol
		userInput = getSearch.getText();
		
		// check symbol
		if (myInvestRepository.checkStocksExists(String.valueOf(userInput))) {
			
			// display info
			for (String stock : myInvestRepository.getStocksList()) {
				line = stock.split(",");
				
				if (line[0].equals(userInput)) {
					
					textViewText += "\nName: " + line[1];
					textViewText += "\nSymbol: " + line[0];
					textViewText += "\nExchange: LSE";
					textViewText += "\nPrice: " + line[2];
					textViewText += "\nChange: " + line[3];
					textViewText += "\nPercentage Change: " + line[4] + "%";
					
					result.setText(textViewText);
				}
			}
			
		} else {
			result.setText("Stock is Not Available.");
		}
		
	}
	
	/**
	 * Invest method
	 * @param event
	 * @throws Exception
	 */
	@FXML
	private void InvestMethod(ActionEvent event) throws Exception{
		
		// declare variable
		InvestRepository myInvestRepository;
		String userInput; 
		int num;
				
		// initialize variable
		myInvestRepository = new InvestRepository();
		userInput = "";
		num = 0;
		
		// get symbol
		userInput = getStock.getText();
		
		// check symbol
		if (myInvestRepository.checkStocksExists(String.valueOf(userInput))) {
			try {
				num = Integer.parseInt(getAmount.getText());
				myInvestRepository.addStock(String.valueOf(userInput), num);
				txtNotification.setText("You have purchased " + num + " shares of " + String.valueOf(userInput) + ".");
			} catch (Exception e) {
				txtNotification.setText("Must Enter a Valid Number of Shares.");
			}
		} else {
			txtNotification.setText("Stock is Not Available.");
		}
		
	}
	
}
