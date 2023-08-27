package edu.ncsu.csc216.hloj.model.io;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.hloj.model.manager.CustomerManager;
import edu.ncsu.csc216.hloj.model.manager.MenuManager;
import edu.ncsu.csc216.hloj.model.manager.OrderManager;

/**
 * Tests for the HLOJDataReader class
 * @author colinscanlon
 *
 */
public class HLOJDataReaderTest {

	/**
	 * Tests that everything was read in from a valid file and put in the correct order
	 */
	@Test
	public void testReadValidFile() {		

		HLOJDataReader.readData("test-files/data1.txt");
		OrderManager orderMan = OrderManager.getInstance();
        MenuManager menuMan = MenuManager.getInstance();
        CustomerManager customerMan = CustomerManager.getInstance();
        
        assertEquals("Sarah", customerMan.getCustomer(1).getFirstName());
        assertEquals("Heckman", customerMan.getCustomer(1).getLastName());
        assertEquals("James", customerMan.getCustomer(2).getFirstName());
        assertEquals("Ignacio X.", customerMan.getCustomer(0).getFirstName());
        assertEquals("sesmith5", customerMan.getCustomer(1).getId());
        
        assertEquals("Large Coffee", menuMan.getMenuItem(0).getName());
        assertEquals("Chai Latte", menuMan.getMenuItem(2).getName());
        assertEquals("Donut", menuMan.getMenuItem(1).getName());
        
        assertEquals(3, menuMan.getMenuItems().length);
        
        assertEquals(3, orderMan.getOrders().length);
        assertEquals(152, orderMan.getOrders()[1].getNumber());
       	assertEquals(3, orderMan.getOrders()[1].getItems().length);
     
        

	}

}
