package ca.ucalgary.domain;

// Imports 
import java.util.*;
import ca.ucalgary.datastore.InvestRepository;

/**
 * Invest class
 * allows users to invest in stocks
 * @author Salim
 */
public class Invest {
	
	/**
	 *  Search Stock Method
	 */
	public void SearchStock() {
		
		// declare variables
		InvestRepository myInvestRepository;
		Scanner input;
		String[] line;
		String userInput;  

		// initialize variables
		myInvestRepository = new InvestRepository();
		input = new Scanner(System.in);
		line = new String[5];
		userInput = "";
		
		// get symbol
		System.out.print("\nEnter Stock Symbol: ");
		userInput = input.nextLine();
		
		// check symbol
		if (myInvestRepository.checkStocksExists(String.valueOf(userInput))) {
			
			// display info
			for (String stock : myInvestRepository.getStocksList()) {
	        	line = stock.split(",");
	        	if (line[0].equals(userInput)) {
					System.out.println("\nName: " + line[1]);
					System.out.println("Symbol: " + line[0]);
					System.out.println("Exchange: LSE");
					System.out.println("Price: " + line[2]);
					System.out.println("Change: " + line[3]);
					System.out.println("Percentage Change: " + line[4] + "%");
	        	}
	        } 
			
		} else {
			System.out.println("Stock Symbol is Not Available.");
		}
		
	}
	
	/**
	 *  View All Stocks Method
	 */
	public void ViewAllStocks() {

		// declare variables
		InvestRepository myInvestRepository;
		String[] line;
		
		// initialize variables
		myInvestRepository = new InvestRepository();
		line = new String[5];
		
        // display all stocks
		System.out.println("\n--------------Available Stocks--------------");
		
        for (String stock : myInvestRepository.getStocksList()) {
        	line = stock.split(",");
			System.out.println(line[0] + " - " + line[1]);
        }

	}
	
	/**
	 *  View Portfolio Method
	 */
	public void ViewPortfolio() {
		
		// declare variables
		InvestRepository myInvestRepository;
		String[] line;
		
		// initialize variables
		myInvestRepository = new InvestRepository();
		line = new String[2];	
		
        // display all stocks in portfolio
		System.out.println("\n------------------Portfolio-----------------");
		
        for (String stock : myInvestRepository.getPortfolioList()) {
        	line = stock.split(";");
			System.out.println(line[0] + ": " + line[1] + " Shares");
        } 	
        
        if (myInvestRepository.getPortfolioList().size() == 0) {
        	System.out.println("none.");
        }
        
	}
	
	/**
	 *  Invest Selected Method
	 */
	public void InvestSelected() {
		
		// declare variables
		InvestRepository myInvestRepository;
		Scanner input;
		String userInput; 
		int num;
		
		// initialize variables
		myInvestRepository = new InvestRepository();
		input = new Scanner(System.in);
		userInput = "";
		num = 0;
		
		// get symbol
		System.out.print("\nEnter Stock Symbol: ");
		userInput = input.nextLine();
		
		// check symbol
		if (myInvestRepository.checkStocksExists(String.valueOf(userInput))) {
			System.out.print("Enter Number of Shares: ");
			while (num == 0) {
				try {
					num = Integer.parseInt(input.nextLine());
				} catch (Exception e) {
					System.out.println("Error! Must enter an integer number.");
					System.out.println("Try again...\n");
					System.out.print("Enter Number of Shares: ");
				}
			}	
			myInvestRepository.addStock(userInput, num);
			System.out.println("You have purchased " + num + " shares of " + String.valueOf(userInput) + ".");
		} else {
			System.out.println("Stock Symbol is Not Available.");
		}
		
	}
	
}
