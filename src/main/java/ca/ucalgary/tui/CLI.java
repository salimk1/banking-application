package ca.ucalgary.tui;

// Imports
import java.util.Scanner;
import ca.ucalgary.domain.Customer;
import ca.ucalgary.services.BankService;
import ca.ucalgary.services.RepositoryService;

/**
 * CLI CLass
 * handles CLI for myBank app
 */
public class CLI {

	// Declare Variables
	private static BankService bankService = new BankService();
	public static Customer contextCustomer = null;
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {

		// setup RepositoryService 
		RepositoryService recoveredService = new RepositoryService();
		recoveredService.restoreAllRepositories();
		
		// declare variables 
		Scanner input;
		String userSelection;
	
		// initialize variables
		input = new Scanner(System.in);  
		userSelection = "";
		
		while (true) {
			
			// welcome message
			System.out.println("--------------------------------------------");
			System.out.println("            Welcome to myMoney!");
			System.out.println("--------------------------------------------");
			System.out.println("[1] Sign-In  [2] Sign-Up  [q] Quit \n");
			
			// selection 
			System.out.print("Enter Selection: ");
			userSelection = input.nextLine();
			
			while (!(userSelection.equals("1")||userSelection.equals("2")||userSelection.equals("q"))) {
				// invalid input
				System.out.println("Invalid Input! Try again... \n");
				System.out.print("Enter Selection: ");
				userSelection = input.nextLine();
			}
			
			switch (userSelection) {
			
			case "1":
				// call SignInSelected method
				contextCustomer = Bank.SignInSelected();
				Menu();
				break;

			case "2":
				// call SignUpSelected method
				contextCustomer = signUp();
				Menu();
				break;
			
			case "q":
				recoveredService.saveAllRepositories();
				System.out.println("\nHave a Nice Day!");
				System.exit(1);

			default:  // will never run 
				break;
			}
			
		}
				
	}

	/**
	 * sign up method
	 * @return Customer
	 */
	private static Customer signUp(){
		Scanner input = new Scanner(System.in);
		System.out.println("Enter your first name:");
		String firstName = input.nextLine();

		System.out.println("Enter your last name:");
		String lastName = input.nextLine();

		System.out.println("Enter your email:");
		String email = input.nextLine();

		System.out.println("Enter a password:");
		String password = input.nextLine();

		return bankService.signUpCustomer(firstName,lastName,email,password);
	}
	
	/**
	 * Menu method
	 */
	public static void Menu() {
		
		// declare variables 
		Scanner input ;
		String userSelection;
		boolean run;
		
		// initialize variables
		input = new Scanner(System.in);  
		userSelection = "";
		run = true;
		
		// menu
		try {
			while (run) {

				// message
				System.out.println("\n--------------------------------------------");
				System.out.println("                   Menu");
				System.out.println("--------------------------------------------");
				System.out.println("[1] Account  [2] Budget  [3] Invest  [4] Save Accounts  [5] Load Accounts  [q] Quit \n");

				// selection
				System.out.print("Enter Selection: ");
				userSelection = input.nextLine();

				while (!(userSelection.equals("1") || userSelection.equals("2") || userSelection.equals("3") || userSelection.equals("4") || userSelection.equals("5") || userSelection.equals("q"))) {
					// invalid input1
					System.out.println("Invalid Input! Try again... \n");
					System.out.print("Enter Selection: ");
					userSelection = input.nextLine();
				}

				switch (userSelection) {
					case "1":
						// call AccountSelected method
						Bank.AccountSelected(contextCustomer);
						break;

					case "2":
						// call BudgetSelected method
						Bank.BudgetSelected();
						break;
						
					case "3":
						// call InvestSelected method
						Bank.InvestSelected();
						break;

					case "4":
						RepositoryService repositoryService = new RepositoryService();
						repositoryService.saveAllRepositories();
						break;

					case "q":
						System.out.println();
						run = false;
						break;

					default:  // will never run
						break;
				}
			}
		} finally {
			// TODO Auto-generated method stub
		}
		
	}
	
}

