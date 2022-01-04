package ca.ucalgary.gui.budget;

import java.net.URL;
import java.util.ResourceBundle;
import ca.ucalgary.datastore.BudgetRepository;
import ca.ucalgary.domain.Budget;
import ca.ucalgary.domain.Expense;
import ca.ucalgary.gui.BankApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * BudgetController
 * contrller class for Budget feature 
 */
public class BudgetController implements Initializable{
	
	// Setting all the variables that will be used
	
	// BudgetController Variables
	private BudgetRepository br = new BudgetRepository();
	private Budget budget = br.getBudget(BankApplication.getCustomer().getId());
	
	// Main scene
	@FXML
	AnchorPane budgetPane;
	@FXML
	Button editBudgetButton;
	@FXML
	Label expenseLabel;
	
	// Menu scene 
	@FXML
	AnchorPane menuPane;
	@FXML
	Button removeExpenseButton;
	@FXML
	Button addExpenseButton;
	@FXML
	Button editIncomeButton;
	@FXML
	Button backButton;
	
	// Add Expense scene
	@FXML
	AnchorPane expensePane;
	@FXML
	TextField nameField;
	@FXML
	TextField costField;
	@FXML
	Button addExpenseToBudgetButton;
	@FXML
	Button cancelAddExpenseButton;
	@FXML
	Label expenseErrorLabel;
	@FXML
	Label expenseSuccessLabel;
	
	// Remove expense scene
	@FXML
	AnchorPane removePane;
	@FXML
	ChoiceBox<String> expenseRemoveChoice;
	@FXML
	Button removeButton;
	@FXML
	Button returnRemoveButton;
	
	//edit income scene
	@FXML
	AnchorPane incomePane;
	@FXML
	TextField incomeField;
	@FXML
	Label incomeErrorLabel;
	@FXML
	Label incomeSuccessLabel;
	@FXML
	Button enterNewIncomeButton;
	@FXML
	Button returnFromIncomeButton;
	
	// controllers
	/**
	 * Opens the menu from the budget scene
	 * @param event
	 * @throws Exception
	 */
	@FXML
	private void openMenu(ActionEvent event) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/BudgetMenu.fxml"));
		Parent menuParent = (Parent)fxmlLoader.load();
		budgetPane.getChildren().clear();
		budgetPane.getChildren().addAll(menuParent);	
	}
	
	// Menu Buttons
	/**
	 * Moves into the expense option
	 * @param event
	 * @throws Exception
	 */
	@FXML
	private void addExpense(ActionEvent event) throws Exception{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AddBudget.fxml"));
		Parent addParent = (Parent)fxmlLoader.load();
		menuPane.getChildren().clear();
		menuPane.getChildren().addAll(addParent);
	}
	/**
	 * Moves into the remove option
	 * @param event
	 * @throws Exception
	 */
	@FXML
	private void removeExpense(ActionEvent event) throws Exception{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/RemoveExpense.fxml"));
		Parent toRemove = (Parent)fxmlLoader.load();
		menuPane.getChildren().clear();
		menuPane.getChildren().addAll(toRemove);
	}
	/**
	 * moves into the edit income 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	private void editIncome(ActionEvent event) throws Exception{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/EditIncome.fxml"));
		Parent toIncome = (Parent)fxmlLoader.load();
		menuPane.getChildren().clear();
		menuPane.getChildren().addAll(toIncome);
	}
	/**
	 * closes the menu back to the budget
	 * @param event
	 * @throws Exception
	 */
	@FXML
	private void closeMenu(ActionEvent event) throws Exception{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Budget.fxml"));
		Parent closeParent = (Parent)fxmlLoader.load();
		menuPane.getChildren().clear();
		menuPane.getChildren().addAll(closeParent);
	}
	
	// Add expense buttons
	/**
	 * Adds an expense to the budget
	 * @param event
	 * @throws Exception
	 */
	@FXML
	private void addExpenseToBudget(ActionEvent event) throws Exception{
		try{double expenseAmount = Double.parseDouble(costField.getText());
			if (expenseAmount > 0 && budget.hasEnoughMoneyToAdd(expenseAmount)) {
				budget.addExpense(nameField.getText(),Double.parseDouble(costField.getText()));
				expenseSuccessLabel.setText("Expense Added Successfully");
				br.saveBudget();
			}else if (expenseAmount > 0) 
				expenseErrorLabel.setText("Not enough income to add expense");
			else expenseErrorLabel.setText("Expense cost cannot be negative");
		} catch (Exception e) {
			expenseErrorLabel.setText("Cost must be in dollars");
		}
	}
	/**
	 * returns to the menu from the expense option
	 * @param event
	 * @throws Exception
	 */
	@FXML
	private void returnToMenuFromExpense(ActionEvent event) throws Exception{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/BudgetMenu.fxml"));
		Parent closeParent = (Parent)fxmlLoader.load();
		expensePane.getChildren().clear();
		expensePane.getChildren().addAll(closeParent);
	}
	
	// remove expense buttons
	/**
	 * Removes the expense from the budget
	 * @param event
	 * @throws Exception
	 */
	@FXML
	private void removeExpenseFromBudget(ActionEvent event) throws Exception{
		budget.removeExpense(expenseRemoveChoice.getValue());
		expenseRemoveChoice.getItems().clear();
		for(Expense x:budget.getExpenses())
			expenseRemoveChoice.getItems().add(x.getName());
		br.saveBudget();
	}
	/**
	 * returns to the menu from the remove option
	 * @param event
	 * @throws Exception
	 */
	@FXML
	private void returnToMenuFromRemove(ActionEvent event) throws Exception{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/BudgetMenu.fxml"));
		Parent toMenu = (Parent)fxmlLoader.load();
		removePane.getChildren().clear();
		removePane.getChildren().addAll(toMenu);
	}
	
	// edit income buttons
	/**
	 * allows the user to set a new income
	 * @param event
	 * @throws Exception
	 */
	@FXML
	private void enterNewIncome(ActionEvent event) throws Exception{
		try{double incomeToAdd = Double.parseDouble(incomeField.getText());
			if (incomeToAdd > budget.totalCostInDollars()) {
				budget.setIncome(incomeToAdd);
				incomeSuccessLabel.setText("Income Successfully Added");
				br.saveBudget();
			} else if (incomeToAdd < 0){
				incomeErrorLabel.setText("Income cannot be negative");
			}else incomeErrorLabel.setText("Expenses too high to change income, remove some expenses");
			
		} catch(Exception e) {//only runs if the income is not entered numerically
			incomeErrorLabel.setText("Income must be entered numerically");
		}
	}
	/**
	 * returns to the menu from the income option
	 * @param event
	 * @throws Exception
	 */
	@FXML
	private void returnToMenuFromIncome(ActionEvent event) throws Exception{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/BudgetMenu.fxml"));
		Parent toMenu = (Parent)fxmlLoader.load();
		incomePane.getChildren().clear();
		incomePane.getChildren().addAll(toMenu);
	}
	
	/**
	 * initialize
	 * @param location
	 * @param resources 
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try{expenseLabel.setText(budget.toString());} catch(Exception e) {}
		try {
			for (Expense x:budget.getExpenses())
			expenseRemoveChoice.getItems().add(x.getName());
		}catch(Exception e) {}
	}
	
	
}
