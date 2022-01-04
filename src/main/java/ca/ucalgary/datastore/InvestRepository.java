package ca.ucalgary.datastore;

// Imports
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/** 
 * InvestRepository CLass
 * contains functionally for the invest feature 
 * @author Salim
 */
public class InvestRepository {
	
	// Declare Instance Variables 
	private ArrayList<String> stocksList, symbolsList;
	private File portfolioFile;
	
	/** 
	 * Default Constructor
	 */
	public InvestRepository() {
		
		// initialize instance variables
		stocksList = new ArrayList<String>();
		symbolsList = new ArrayList<String>();
		portfolioFile = null;
		
		// declare variables 
		String url, symbol, name;
	    double price, change, percentChange;    
		
		// initialize variables 
	    url = "https://shares.telegraph.co.uk/indices/summary/index/MCX";
	    symbol = "";
        name = "";
        price = 0.0;
        change = 0.0;
        percentChange = 0.0;
        
        // obtain information from the website 
        try {
            
            // declare & initialize document 
            Document document;
            document = Jsoup.connect(url).get();
            
            // iterate through table 
            for (Element row : document.select("table.tablesorter.full tr")) {
                
                // the first row is empty
                if (row.select("td:nth-of-type(1)").text().equals("")) {
                    continue;  // continue
                } 
                
                // after the first row 
                else {
                	
                	// get live info from website
                	symbol = row.select("td:nth-of-type(1)").text();
                    name = row.select("td:nth-of-type(2)").text();
                    price = Double.parseDouble(row.select("td.right:nth-of-type(3)").text().replace(",", ""));
                    change = Double.parseDouble(row.select("td:nth-of-type(4)").text());
                    percentChange = Double.parseDouble(row.select("td:nth-of-type(5)").text());
                 
                    // add to stocksList
                    stocksList.add(symbol + "," + name + "," + price + "," + change + "," + percentChange);
                    
                    // add to symbolsList
                    symbolsList.add(symbol);
                }
            }
            
            // open portfolio file
            try {
				portfolioFile = new File("src/main/resources/data-stores/invest-repository.txt");
			} catch (Exception e) {
				System.out.println("invest-repository.txt doesn't exist");
			}
            
        } 
        
        // catch error
        catch (Exception ex) {
            System.out.println("Error! Obtaining stocks from website failed.");  //ex.printStackTrace();
        }
		
	}
	
	/**
	 * returns the stocks list
	 * @return stocksList
	 */
	public ArrayList<String> getStocksList() {      
        return stocksList;      
	}

	/**
	 * returns the symbols list
	 * @return symbolsList
	 */
	public ArrayList<String> getSymbolsList() {
        return symbolsList;      	
	}
	
	/**
	 * returns the portfolio list
	 * @return portfolioList
	 */
	public ArrayList<String> getPortfolioList() {
		
		// declare variables
		ArrayList<String> portfolioList;
		Scanner StocksScanner;
		String line;
		
		// initialize variables
		portfolioList = new ArrayList<String>();
		StocksScanner = null;
		line = "";
		
		// open txt file
		try {
			StocksScanner = new Scanner(portfolioFile);
		} catch (Exception e) {
			System.out.println("Error with StocksScanner!");
		}
		
		// add to stocks list
		while(StocksScanner.hasNext()) {
			line = StocksScanner.nextLine();
			portfolioList.add(line);
		}
		
		// return portfolio list 
		return portfolioList;
	}

	/**
	 * add stock to the portfolio
	 * @param symbol, the stock symbol
	 * @param amount, the amount of shares to purchase 
	 */
	public void addStock(String symbol, int amount) {
		
		// check if stocks exists and valid amount entered 
		if (checkStocksExists(symbol) && amount >= 1) {
		
			// declare variables
			FileWriter PortfolioWriter;
			
			// initialize variables	
			PortfolioWriter = null;
			
			// get portfolio file
			try {
				// open pofolio text file
				PortfolioWriter = new FileWriter(portfolioFile, true);
			} catch (Exception e) {
				// error message
				System.out.println("Portfolio.txt doesn't exist");
			}		
			
			// add stock to portfolio
			try {
				
				// if portfolio is not empty, go to next line
				if(portfolioFile.length() != 0) {
					PortfolioWriter.write("\n");
				}
				
				// add stock purchase to portfolio
				PortfolioWriter.write(symbol + ";" + String.valueOf(amount));
				PortfolioWriter.close();
				
			} catch (IOException e) {
				// display error message
				System.out.println("Error! Could not add stock to portfolio.");
			}
		
		}
	
	}

	/**
	 * checks if the stock is available
	 * @param symbol, the stock symbol
	 * @return boolean, true if the stocks exists, false otherwise 
	 */
	public boolean checkStocksExists(String symbol) {
		return getSymbolsList().contains(symbol);
	}
	
}
