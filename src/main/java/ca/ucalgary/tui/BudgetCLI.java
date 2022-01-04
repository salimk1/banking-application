package ca.ucalgary.tui;

import ca.ucalgary.datastore.BudgetRepository;
import ca.ucalgary.domain.Budget;
import ca.ucalgary.tui.CLI;
import java.util.Scanner;

/**
 * BudgetCLI
 * CLI class for the Budget feature
 */
public class BudgetCLI {
	
	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {
		
		// declare variables 
		Budget myBudget;
		Scanner input ;
		String userSelection, userBudget, expenseName, expenseAmount,cont;
		boolean run;
		BudgetRepository saveLoad = new BudgetRepository();

		// initialize variables
		try {
			System.out.println(CLI.contextCustomer.getId());
			myBudget = saveLoad.getBudget(CLI.contextCustomer.getId());
		} catch (Exception e) {
			myBudget = new Budget(0);
		}
		input = new Scanner(System.in);  
		userSelection = "";
		run = true;

		while (run) {
			
			// message
			System.out.println("--------------------------------------------");
			System.out.println("[1] Set Income  [2] Add Expense  [3] View Budget  [q] Quit \n");

			// selection 
			System.out.print("Enter Selection: ");
			userSelection = input.nextLine();
			
			while (!(userSelection.equals("1")||userSelection.equals("2")||userSelection.equals("3")||userSelection.equals("q"))) {
				// invalid input
				System.out.println("Invalid Input! Try again... \n");
				System.out.print("Enter Selection: ");
				userSelection = input.nextLine();
			}
			
			switch (userSelection) {
			
			case "1":
				// set income;
				System.out.print("\nEnter Income: ");
				userBudget = input.nextLine();
				if (Double.parseDouble(userBudget) <= 0.0) {
					System.out.print("\n Income Cannot Be Negative");
				} else myBudget.setIncome(Double.valueOf(userBudget));
				break;
				
			case "2":
				// add expense
				System.out.print("\nEnter Expense Name: ");
				expenseName = input.nextLine();
				while (myBudget.nameUsed(expenseName)) {
					System.out.print("\nName Already Taken\nEnter New Name:");
					expenseName = input.nextLine();
				}
				System.out.print("Enter Expense Amount: ");
				expenseAmount = input.nextLine();
				if (!myBudget.hasEnoughMoneyToAdd(Double.parseDouble(expenseAmount))) {
					System.out.println("No income left for new expense. Continue? [y] or [n]");
					cont = input.nextLine();
					while (!cont.equals("y") && !cont.equals("n")) {
						System.out.println("Please enter valid command\n");
						cont = input.nextLine();
					}
					if (cont.equals("y")) 
						myBudget.addExpense(expenseName,Double.parseDouble(expenseAmount));
				}else myBudget.addExpense(expenseName,Double.parseDouble(expenseAmount));
				
				break;
				
			case "3":
				// view budget
				System.out.println("\n" + myBudget.toString());
				break;
				
			case "q":
				saveLoad.saveBudget();
				run = false;
				break;
				
			default:  // will never run
				break;
			}
			
		}

	}
	
}
