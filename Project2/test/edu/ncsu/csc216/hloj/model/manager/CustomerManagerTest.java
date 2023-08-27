package edu.ncsu.csc216.hloj.model.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.hloj.model.Customer;
import edu.ncsu.csc216.hloj.model.ModelException;

/**
 * Tests the CustomerManager Class
 * @author Haley Heath
 *
 */
public class CustomerManagerTest {

	/** The CustomerManager for testing */
	private CustomerManager manager = CustomerManager.getInstance();
	/** A customer for testing */
	private Customer c1;
	/** A customer for testing */
	private Customer c2;
	/** A customer for testing */
	private Customer c3;
	/** A customer for testing */
	private Customer c4;
	
	/**
	 * Sets up for tests
	 */
	@Before
	public void setUp() {
		manager.removeAllCustomers();
		try {
			c1 = new Customer("Haley", "Heath", "hheath");
			c2 = new Customer("Colin", "Scanlon", "cscanlon");
			c3 = new Customer("Carter", "Estes", "cestes");
			c4 = new Customer("Mikaela", "McCarson", "mmccarson");
		} catch (ModelException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	/**
	 * Tests the addCustomer method
	 */
	@Test
	public void testAddCustomer() {
		try {
			manager.addCustomer(c1);
			manager.addCustomer(c2);
			manager.addCustomer(c3);
			manager.addCustomer(c4);
			assertEquals(4, manager.getCustomers().length);
		} catch (ModelException e) {
			fail();
		}
	}
	
	/**
	 * Tests the getCustomers method
	 */
	@Test
	public void testGetCustomers() {
		try {
			manager.addCustomer(c1);
			manager.addCustomer(c2);
			manager.addCustomer(c3);
			manager.addCustomer(c4);
			
			assertEquals(4, manager.getCustomers().length);
			assertEquals(c3, manager.getCustomers()[0]);
			assertEquals(c1, manager.getCustomers()[1]);
			assertEquals(c4, manager.getCustomers()[2]);
			assertEquals(c2, manager.getCustomers()[3]);
		} catch (ModelException e) {
			fail();
		}
	}
	
	/**
	 * Tests the getCustomer with integer method
	 */
	@Test
	public void testGetCustomerIdx() {
		try {
			manager.addCustomer(c1);
			manager.addCustomer(c2);
			manager.addCustomer(c3);
			manager.addCustomer(c4);
			
			assertEquals(4, manager.getCustomers().length);
			assertEquals(c3, manager.getCustomer(0));
			assertEquals(c1, manager.getCustomer(1));
			assertEquals(c4, manager.getCustomer(2));
			assertEquals(c2, manager.getCustomer(3));
		} catch (ModelException e) {
			fail();
		}
	}
	
	/**
	 * Tests the getCustomer with String method
	 */
	@Test
	public void testGetCustomerFromString() {
		try {
			manager.addCustomer(c1);
			manager.addCustomer(c2);
			manager.addCustomer(c3);
			manager.addCustomer(c4);
			
			assertEquals(4, manager.getCustomers().length);
			assertEquals(c1, manager.getCustomer("hheath"));
			assertEquals(c2, manager.getCustomer("cscanlon"));
			assertEquals(c3, manager.getCustomer("cestes"));
			assertEquals(c4, manager.getCustomer("mmccarson"));
			assertNull(manager.getCustomer("tstancil"));
		} catch (ModelException e) {
			fail();
		}
	}
	
	/**
	 * Tests the removeCustomer method
	 */
	@Test
	public void testRemoveCustomer() {
		try {
			manager.addCustomer(c1);
			manager.addCustomer(c2);
			manager.addCustomer(c3);
			manager.addCustomer(c4);
			
			assertEquals(4, manager.getCustomers().length);
			assertEquals(c3, manager.getCustomer(0));
			assertEquals(c1, manager.getCustomer(1));
			assertEquals(c4, manager.getCustomer(2));
			assertEquals(c2, manager.getCustomer(3));
			
			manager.removeCustomer(c3);
			
			assertEquals(3, manager.getCustomers().length);
			assertEquals(c1, manager.getCustomer(0));
			assertEquals(c4, manager.getCustomer(1));
			assertEquals(c2, manager.getCustomer(2));
		} catch (ModelException e) {
			fail();
		}
	}
	
	/**
	 * Tests the editCustomer method
	 */
	@Test
	public void testEditCustomer() {
		try {
			Customer c5 = new Customer("Todd", "Stancil", "tstancil");
			
			manager.addCustomer(c1);
			manager.addCustomer(c2);
			manager.addCustomer(c3);
			manager.addCustomer(c4);
			
			assertEquals(4, manager.getCustomers().length);
			assertEquals(c1, manager.getCustomer("hheath"));
			assertEquals(c2, manager.getCustomer("cscanlon"));
			assertEquals(c3, manager.getCustomer("cestes"));
			assertEquals(c4, manager.getCustomer("mmccarson"));
			assertNull(manager.getCustomer("tstancil"));
			
			assertEquals(c3, manager.getCustomers()[0]);
			assertEquals(c1, manager.getCustomers()[1]);
			assertEquals(c4, manager.getCustomers()[2]);
			assertEquals(c2, manager.getCustomers()[3]);
			
			manager.editCustomer(3, c5);
			assertEquals(4, manager.getCustomers().length);
			assertEquals(c1, manager.getCustomer("hheath"));
			assertEquals(c4, manager.getCustomer("mmccarson"));
			assertEquals(c3, manager.getCustomer("cestes"));
			assertNull(manager.getCustomer("cscanlon"));
			assertEquals(c5, manager.getCustomer("tstancil"));
			
		} catch (ModelException e) {
			fail();
		}
	}
	
	/**
	 * Tests the removeAllCustomers method
	 */
	@Test
	public void testRemoveAllCustomers() {
		try {
			manager.addCustomer(c1);
			manager.addCustomer(c2);
			manager.addCustomer(c3);
			manager.addCustomer(c4);
			assertEquals(4, manager.getCustomers().length);
			
			manager.removeAllCustomers();
			assertEquals(0, manager.getCustomers().length);
			
		} catch (ModelException e) {
			fail();
		}
	}

}
