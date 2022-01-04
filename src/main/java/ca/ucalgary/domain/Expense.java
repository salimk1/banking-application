package ca.ucalgary.domain;

import ca.ucalgary.domain.Expense;

/**
 * Expense
 */
public class Expense{
	
	// setting instance variables
	private String name;
	private double amountOfIncomeCAD;
	
	// constructors
	/**
	 * Constructor 
	 */
	public Expense() {
		name = "";
		amountOfIncomeCAD = 0;
	}
	/**
	 * Constructor
	 * @param name
	 * @param amount
	 */
	public Expense(String name, double amount){
		this.name = name;
		this.amountOfIncomeCAD = amount;
	}
	/**
	 * Constructor
	 * @param toCopy
	 */
	public Expense(Expense toCopy){
		this.name = toCopy.getName();
		this.amountOfIncomeCAD = toCopy.getAmountPerMonth();
	}
	
	//setters
	/**
	 * Set how much an expense costs per month based on a dollar amount
	 * @param amount
	 */
	public void setAmountPerMonth(double amount){
		this.amountOfIncomeCAD = amount;
	}
	
	//getters
	/**
	 * getName
	 * 
	 * @return expense name as string
	 */
	public String getName(){
		String name = this.name;
		return name;
	}
	
	/**
	 * getDollars
	 * how much an expense is worth in dollars
	 * @return dollars as double
	 */
	public double getAmountPerMonth(){
		double dollars = this.amountOfIncomeCAD;
		return dollars;
	}
	
}
