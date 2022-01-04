package ca.ucalgary.datastore;

import ca.ucalgary.domain.Budget;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Used to save the budget information
 */
public class BudgetRepository {
	
	/**
	 * instance variables
	 */
	private static Map<String,Budget> store = new HashMap<>();
	
	/**
	 * Default constructor that calls load budget to load the budget
	 */
	public BudgetRepository() {
		loadBudget();
	}
	
	/**
	 * saves the budget to a json file
	 * @param none
	 */
	public void saveBudget() {
		try {
			List<Budget> toSave = setAsList();
			ObjectMapper objectMapper = new ObjectMapper();
			String JSON = objectMapper.writeValueAsString(toSave);
			System.out.println(JSON);
		try {
			File budgetStore = getFile("data-stores/budget-repository.json");
			objectMapper.writeValue(budgetStore,toSave);
		} catch(IOException e){
			e.printStackTrace();
		}
		catch (URISyntaxException e) {
			e.printStackTrace();
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * loads the budget from the json file
	 * @param none
	 */
	public void loadBudget() {
		try {
			ObjectMapper objectMapper = new ObjectMapper().enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
			Scanner scanner = new Scanner(getFile(""));
			String json = scanner.useDelimiter("\\A").next();
			List<Budget> list = objectMapper.readValue(json, new TypeReference<List<Budget>>(){});
			restoreBudgets(list);
		}catch(Exception e) {
		}
		
	}
	
	/**
	 * gets the file to load
	 * @param path
	 * @return File
	 * @throws URISyntaxException
	 */
	public File getFile(String path) throws URISyntaxException {
		URL url = Budget.class.getClassLoader().getResource("data-stores/budget-repository.json");
		
		Path location = Paths.get(url.toURI());
		return location.toFile();
	}
	
	/**
	 * Takes a list and converts it to a map
	 * @param list
	 */
	public void restoreBudgets(List<Budget> list) {
		for (Budget x:list) {
			try{
				store.put(x.getCustomerID(),x);
			} catch (Exception e) {
				System.out.println("cannot add");
			}
		}
	}
	
	/**
	 * gets the budget to return from the list
	 * @param id
	 * @return budget
	 */
	public Budget getBudget(String id) {
		try {
			if (store.get(id) == null) {
				store.put(id,new Budget(id));
			}
			return store.get(id);
		} catch(Exception e) {
			System.out.print("Budget does not exist for id");
			return store.get(id);
		}
	}
	
	/**
	 * saves the map as a list
	 * @return list
	 */
	public List<Budget> setAsList(){
		List<Budget> toReturn = new ArrayList<>(store.values());
		return toReturn;
	}
}
