package ca.ucalgary.tui;

// Imports 
import java.util.Scanner;
import ca.ucalgary.domain.*;
import ca.ucalgary.services.BankService;

/**
 * BankApplication
 * Contains control/menu methods 
 */
public class Bank {
	
	// Declare BankService Variable
	private static BankService bankService = new BankService();
	
	/**
	 * Sign-In Selected Method
	 */
	public static Customer SignInSelected() {
		
		// message
		System.out.println("\n--------------------------------------------");
		System.out.println("                   Sign-In");
		System.out.println("--------------------------------------------");
		
		// get username and password
		Scanner input =  new Scanner(System.in);
		System.out.print("Enter Email: ");
		String email = input.nextLine();
		System.out.print("Enter Password: ");
		String password = input.nextLine();

		// create customer
		Customer customer = bankService.signIn(email,password);

		if (customer == null){
			System.out.println("Invalid Credentials");
			SignInSelected();
		}

		// return customer
		return customer;

	}
		
	/**
	 * Sign-Up Selected Method
	 */
	public static Customer SignUpSelected() {
		
		// message
		System.out.println("\n--------------------------------------------");
		System.out.println("                   Sign-Up");
		System.out.println("--------------------------------------------");
		
		// get user info
		Scanner input =  new Scanner(System.in);
		System.out.print("Enter Email: ");
		String email = input.nextLine();
		System.out.print("Enter Password: ");
		String password = input.nextLine();
		System.out.print("Enter First Name: ");
		String fname = input.nextLine();
		System.out.print("Enter Last Name: ");
		String lname = input.nextLine();
		Customer customer = bankService.signUpCustomer(fname, lname, email, password);
		
		// return customer
		return customer;
		
	}
	
	/**
	 * Account Selected Method
	 */
	public static void AccountSelected(Customer customer) {

		// message
		System.out.println("\n--------------------------------------------");
		System.out.println("                   Account");
		
		// run CLI class
		AccountCLI myCLI = new AccountCLI();
		myCLI.processAccountsFor(customer);
	}

	/**
	 * Budget Selected Method
	 */
	public static void BudgetSelected() {
		
		// message
		System.out.println("\n--------------------------------------------");
		System.out.println("                   Budget");
		
		// run CLI
		BudgetCLI budgetCLI = new BudgetCLI();
		budgetCLI.main(null);
	}

	/**
	 * Invest Selected Method
	 */
	public static void InvestSelected() {
		
		// declare variables 
		Invest myInvest;
		Scanner input ;
		String userSelection;
		boolean run;

		// initialize variables
		myInvest = new Invest();
		input = new Scanner(System.in);  
		userSelection = "";
		run = true;

		while (run) {
			
			// message
			System.out.println("\n--------------------------------------------");
			System.out.println("                   Invest");
			System.out.println("--------------------------------------------");
			System.out.println("[1] Search Stock  [2] View All Stocks  [3] View Portfolio  [4] Invest  [q] Quit \n");

			// selection 
			System.out.print("Enter Selection: ");
			userSelection = input.nextLine();
			
			while (!(userSelection.equals("1")||userSelection.equals("2")||userSelection.equals("3")||userSelection.equals("4")||userSelection.equals("q"))) {
				// invalid input
				System.out.println("Invalid Input! Try again... \n");
				System.out.print("Enter Selection: ");
				userSelection = input.nextLine();
			}
			
			switch (userSelection) {
			
			case "1":
				// call SearchStock method
				myInvest.SearchStock();
				break;
				
			case "2":
				// call ViewAllStocks method
				myInvest.ViewAllStocks();
				break;
				
			case "3":
				// call ViewPortfolio method
				myInvest.ViewPortfolio();
				break;
				
			case "4":
				// call InvestSelected method
				myInvest.InvestSelected();
				break;
				
			case "q":
				run = false;
				break;
				
			default: 
				// will never run
				break;
			}
			
		}

	}

	/**
	 * save data to JSon file
	 */
	public void SaveDataStoreToFile(){
	}

}
