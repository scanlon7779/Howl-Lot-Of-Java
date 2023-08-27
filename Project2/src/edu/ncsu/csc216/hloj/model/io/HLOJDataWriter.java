package edu.ncsu.csc216.hloj.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import edu.ncsu.csc216.hloj.model.MenuItem;
import edu.ncsu.csc216.hloj.model.lists.UniqueList;
import edu.ncsu.csc216.hloj.model.manager.CustomerManager;
import edu.ncsu.csc216.hloj.model.manager.MenuManager;
import edu.ncsu.csc216.hloj.model.manager.OrderManager;

/**
 * Writes the HLOJ infromation to a file
 * @author colinscanlon
 *
 */
public class HLOJDataWriter {

	/**
	 * The constructor for HLOJ Data Writer
	 */
	public HLOJDataWriter() {
		// Empty Constructor
	}
	
	/**
	 * Writes the information from HLOJ to the given file
	 * @param fileName the file to write to 
	 */
	public static void writeData(String fileName) { 
		try {
			PrintStream fileWriter = new PrintStream(new File(fileName));
			OrderManager orderMan = OrderManager.getInstance();
	        MenuManager menuMan = MenuManager.getInstance();
	        CustomerManager customerMan = CustomerManager.getInstance();
	        UniqueList<String> menuItemIDs = new UniqueList<String>();
	        UniqueList<MenuItem> menuItems = new UniqueList<MenuItem>();
	        int num = 0;
			String write = "";
			write += orderMan.getLastOrderNumber() + "\n";
			for (int i = 0; i < customerMan.getCustomers().length; i++) {
				write += "# " + customerMan.getCustomer(i).getFirstName() + "," +  
						customerMan.getCustomer(i).getLastName() + "," + 
						customerMan.getCustomer(i).getId() + "\n";
			}
			
			for (int i = 0; i < menuMan.getMenuItems().length; i++) {
				String id = "MI" + num;
				menuItemIDs.add(id);
				menuItems.add(menuMan.getMenuItems()[i]);
				num++;
				write += "* " + id + "," + menuMan.getMenuItems()[i].getType() + "," + 
						menuMan.getMenuItems()[i].getName() + "," +  
						menuMan.getMenuItems()[i].getPrice() + "\n";
			}
			
			for (int i = 0; i < orderMan.getOrders().length; i++) {
				write += "- " + orderMan.getOrders()[i].getNumber() + "," + 
						orderMan.getOrders()[i].getCustomer().getId() + ",";
				for (int j = 0; j < orderMan.getOrders()[i].getItems().length; j++) {
					for (int k = 0; k < orderMan.getOrders()[i].getItems()[j].getQuantity(); k++) {
						for(int l = 0; l < menuItems.size(); l++) {
							if(menuItems.get(l).getName().equals(orderMan.getOrders()[i].getItems()[j].getMenuItem().getName())) {
								if (j == orderMan.getOrders()[i].getItems().length - 1 && k == orderMan.getOrders()[i].getItems()[j].getQuantity() - 1) {
									write += menuItemIDs.get(l);
								} else {
									write += menuItemIDs.get(l) + ",";
								}
							}
						}
					}
				}
				write += "\n";
			}
			
			
			fileWriter.print(write);
			fileWriter.close();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
	}
}
