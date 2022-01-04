package ca.ucalgary.datastore;

// Imports 
import ca.ucalgary.datastore.InvestRepository;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * InvestRepositoryTest
 * JUnit testing for the InvestRepository class 
 * @author Salim
 */
public class InvestRepositoryTest {
	
	/**
	 * Test constructor creates correct stocks list 
	 */
	@Test 
	public void test_constructor_stocksList() {
		
		// setup
		InvestRepository myInvestRepository;
		
		// executing
		myInvestRepository = new InvestRepository(); 
		
		// verification
		assertTrue("Stocks list must contain FTSE 250 index stocks.", myInvestRepository.getStocksList().contains(getFirstStock()));
	
	}
	
	/**
	 * Test constructor creates correct symbols list 
	 */
	@Test 
	public void test_constructor_symbolsList() {
		
		// setup
		InvestRepository myInvestRepository;
		
		// executing
		myInvestRepository = new InvestRepository(); 
		
		// verification
		assertTrue("Symbols list must contain symbols of FTSE 250 index stocks.", myInvestRepository.getSymbolsList().contains("3IN"));
	
	}
	
	/**
	 * Test add stock with valid symbol, valid amount
	 */
	@Test 
	public void test_addStock_validSymbolValidAmount() {
		
		// setup
		InvestRepository myInvestRepository = new InvestRepository(); 
		
		// executing
		myInvestRepository.addStock("3IN", 100);
		
		// verification
		assertTrue("addStock() should add valid stock and valid amount to portfolio", myInvestRepository.getPortfolioList().contains("3IN;100"));
	
	}
	
	/**
	 * Test add stock with valid symbol, invalid amount
	 */
	@Test 
	public void test_addStock_validSymbolInvalidAmount() {
		
		// setup
		InvestRepository myInvestRepository = new InvestRepository(); 
		
		// executing
		myInvestRepository.addStock("3IN", 0);
		
		// verification
		assertTrue("stock invest amount should be a positive integer", !(myInvestRepository.getPortfolioList().contains("3IN;0")));
	
	}
	
	/**
	 * Test add stock with invalid symbol, valid amount
	 */
	@Test 
	public void test_addStock_invalidSymbolValidAmount() {
		
		// setup
		InvestRepository myInvestRepository = new InvestRepository(); 
		
		// executing
		myInvestRepository.addStock("ZZZ", 100);
		
		// verification
		assertTrue("stock should be in the FTSE 250 index", !(myInvestRepository.getPortfolioList().contains("ZZZ;100")));
	
	}	
	
	/**
	 * Test add stock with invalid symbol, invalid amount
	 */
	@Test 
	public void test_addStock_invalidSymbolInvalidAmount() {
		
		// setup
		InvestRepository myInvestRepository = new InvestRepository(); 
		
		// executing
		myInvestRepository.addStock("ZZZ", 0);
		
		// verification
		assertTrue("stock invest amount should be a positive integer, and stock should be in the FTSE 250 index", !(myInvestRepository.getPortfolioList().contains("ZZZ;0")));
	
	}	
	
	/**
	 * Test check stock exists with valid symbol
	 */
	@Test 
	public void test_checkStocksExists_validSymbol() {
		
		// setup
		InvestRepository myInvestRepository = new InvestRepository(); 
		
		// executing
		boolean correctResult = true;
		
		// verification
		assertEquals("stock should be in the FTSE 250 index", correctResult, myInvestRepository.checkStocksExists("3IN"));
	
	}
	
	/**
	 * Test check stock exists with invalid symbol
	 */
	@Test 
	public void test_checkStocksExists_InvalidSymbol() {
		
		// setup
		InvestRepository myInvestRepository = new InvestRepository(); 
		
		// executing
		boolean correctResult = false;
		
		// verification
		assertEquals("stock should be in the FTSE 250 index", correctResult, myInvestRepository.checkStocksExists("ZZZ"));
	
	}
	
	/**
	 * get's first stock from FTSE 250 index (3IN)
	 * @return string
	 */
	public String getFirstStock() {
		
		// declare variables
		String stock, url, symbol, name;
		double price, change, percentChange; 
		
		// initialize variables
		stock = "";
		url = "https://shares.telegraph.co.uk/indices/summary/index/MCX";
		symbol = "";
		name = "";
        price = 0.0;
        change = 0.0;
        percentChange = 0.0;
		
		// get stock info from website 
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
                 
                    // set stock
                    stock = symbol + "," + name + "," + price + "," + change + "," + percentChange;
                    
                    // only need first stock
                    break;
                }
            
            } 
            
        } catch (Exception ex) {
        	// display error message
            System.out.println("Error! Obtaining stocks from website failed.");  //ex.printStackTrace();
        }
        
        // return stock 
        return stock;
                        
	}
	
}
