package edu.ncsu.csc216.hloj.model.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc216.hloj.model.Customer;
import edu.ncsu.csc216.hloj.model.MenuItem;
import edu.ncsu.csc216.hloj.model.ModelException;
import edu.ncsu.csc216.hloj.model.Order;
import edu.ncsu.csc216.hloj.model.lists.UniqueList;
import edu.ncsu.csc216.hloj.model.manager.CustomerManager;
import edu.ncsu.csc216.hloj.model.manager.MenuManager;
import edu.ncsu.csc216.hloj.model.manager.OrderManager;

/**
 * Reads in a file and collects the id's of all objects in the file
 * @author colinscanlon
 *
 */
public class HLOJDataReader {

	/**
	 * Constructor for the HLOJ reader
	 */
	public HLOJDataReader() {
		// Empty Constructor for HLOJDataReader
	}
	
	/**
	 * Reads in a file to collect the unique ids of the menu items, customers and orders
	 * @param fileName The file to read information from
	 */
	public static void readData(String fileName) {
		UniqueList<String> menuItemIDs = new UniqueList<String>();
		UniqueList<MenuItem> menuItems = new UniqueList<MenuItem>();
		
		try {
			Scanner fileReader = new Scanner(new FileInputStream(fileName));  //Create a file scanner to read the file
	        OrderManager orderMan = OrderManager.getInstance();
	        orderMan.removeAllOrders();
	        MenuManager menuMan = MenuManager.getInstance();
	        menuMan.removeAllMenuItems();
	        CustomerManager customerMan = CustomerManager.getInstance();
	        customerMan.removeAllCustomers();
	        String start = fileReader.nextLine().trim();
	        int lastOrder;
	        try {
	        	lastOrder = Integer.parseInt(start);
	        	 orderMan.setLastOrderNumber(lastOrder);
	        } catch (NumberFormatException e) {
	        	throw new IllegalArgumentException("Unable to load file");
	        }
	        boolean doneM = false;
	        boolean doneO = false;
			while (fileReader.hasNextLine()) { //While we have more lines in the file
	            try { //Attempt to do the following
	            	Scanner lineScanner = new Scanner(fileReader.nextLine());
	            	String first = "";
	            	if (lineScanner.hasNext()) {
	            		first = lineScanner.next();
	            	} else {
	            		throw new ModelException();
	            	}
	            	if ("#".equals(first)) {
	            		if(doneM || doneO) {
	            			throw new ModelException();
	            		}
	            		lineScanner.useDelimiter(",");
	            		String firstName = "";
	            		String lastName = "";
	            		String id = "";
	            		
	            		if(lineScanner.hasNext()) {
	            			firstName = lineScanner.next().trim();
	            		}
	            		
	            		if(lineScanner.hasNext()) {
	            			lastName = lineScanner.next();
	            		}
	            		
	            		if(lineScanner.hasNext()) {
	            			id = lineScanner.next();
	            		}
	            		
	            		Customer c = new Customer(firstName, lastName, id);
	            		customerMan.addCustomer(c);
	            		
	            	} else if ("*".equals(first)) {
	            		doneM = true;
	            		if(doneO) {
	            			throw new ModelException();
	            		}
	            		lineScanner.useDelimiter(",");
	            		String id = "";
	            		String type = "";
	            		String name = "";
	            		double price = 0;
	            		if (lineScanner.hasNext()) {
	            			id = lineScanner.next().trim();
	            		}
	            		if (lineScanner.hasNext()) {
	            			type = lineScanner.next();
	            		}
	            		if (lineScanner.hasNext()) {
	            			name = lineScanner.next();
	            		}
	            		if (lineScanner.hasNext()) {
	            			try {
	            				price = Double.parseDouble(lineScanner.next());
	            			} catch (NumberFormatException e) {
	            				throw new ModelException();
	            			}
	            		}
	            		
	            		MenuItem m = new MenuItem(type, name, price);
	            		try {
	            			menuItemIDs.add(id);
	            			menuItems.add(m);
	            		} catch (IllegalArgumentException e) {
	            			throw new ModelException();
	            		}
	            		menuMan.addMenuItem(m);
	            	} else if ("-".equals(first)) {
	            		doneO = true;
	            		if (menuMan.getMenuItems().length == 0 || customerMan.getCustomers().length == 0) {
	            			return;
	            		}
	            		lineScanner.useDelimiter(",");
	            		int id = 0;
	            		String customerId = "";
	            		if (lineScanner.hasNext()) {
	            			try {
	            				id = Integer.parseInt(lineScanner.next().trim());
	            			
	            			} catch (NumberFormatException e) {
	            				throw new ModelException();
	            			}
	            		}
	            		if (lineScanner.hasNext()) {
	            			customerId = lineScanner.next();
	            		}
	            		
	            		Customer c = customerMan.getCustomer(customerId);
	            		if (c == null) {
	            			throw new ModelException();
	            		}
	            		Order o = new Order(id, c);
	            		while(lineScanner.hasNext()) {
	            			String menuItem = lineScanner.next();
	            			MenuItem m = null;
	            			for(int i = 0; i < menuItemIDs.size(); i++) {
	            				if(menuItemIDs.get(i).equals(menuItem)) {
	            					m = menuItems.get(i);
	            				}
	            			}
	            			if (m == null) {
	            				throw new ModelException();
	            			}
	            			o.addMenuItem(m);
	            		}
	            		orderMan.placeOrder(o);
	            	} else {
	            		throw new ModelException();
	            	}
	                
	            } catch (ModelException e) {
	                //The line is invalid b/c we couldn't create a object, skip it!
	            }
	        }

	        //Close the Scanner b/c we're responsible with our file handles
	        fileReader.close();
		}
		catch (FileNotFoundException e){
			throw new IllegalArgumentException("File " + fileName + " does not exist");
		}
	}
}
