package edu.ncsu.csc216.hloj.model.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.hloj.model.Customer;
import edu.ncsu.csc216.hloj.model.MenuItem;
import edu.ncsu.csc216.hloj.model.ModelException;
import edu.ncsu.csc216.hloj.model.Order;

/**
 * Tests the OrderManager Class
 * @author Haley Heath
 *
 */
public class OrderManagerTest {
	
	/** The CustomerManager for testing */
	private OrderManager manager = OrderManager.getInstance();
	/** A customer for testing */
	private Customer c1;
	/** A customer for testing */
	private Customer c2;
	/** A customer for testing */
	private Customer c4;
	/** A menu item for testing */
	private MenuItem m1;
	/** A menu item for testing */
	private MenuItem m2;
	/** A menu item for testing */
	private MenuItem m3;
	/** A menu item for testing */
	private MenuItem m4;
	/** A menu item for testing */
	private MenuItem m5;
	/** An order for testing */
	private Order o1;
	/** An order for testing */
	private Order o2;
	
	/**
	 * Sets up for tests
	 */
	@Before
	public void setUp() {
		manager.removeAllOrders();
		try {
			c1 = new Customer("Odessa ", "Howard ", "ohoward");
			c2 = new Customer("Kasimir", "Santos",  "ksantos");
			c4 = new Customer("Kiara", "Martin", "kmartin");
			m1 = new MenuItem("Tea", "Apple Tea", 3.0);
			m2 = new MenuItem("Coffee", "Latte", 4.15);
			m3 = new MenuItem("Tea", "Earl Gray", 2.5);
			m4 = new MenuItem("Coffee", "Americano", 4.33);
			m5 = new MenuItem("Ice Cream", "Cherry Vanilla", 2.16);
			manager.setLastOrderNumber(13);
			o1 = manager.getNextOrder(c2);
			o2 = manager.getNextOrder(c4);
		} catch (ModelException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	/**
	 * Tests the getLastOrderNumber method
	 */
	@Test
	public void testGetLastOrderNumber() {
		assertEquals(15, manager.getLastOrderNumber());
	}
	
	/**
	 * Tests the getNextOrder method
	 */
	@Test
	public void testGetNextOrder() {
		Order o;
		try {
			o = manager.getNextOrder(c1);
			assertEquals(16, o.getNumber());
			assertEquals(c1, o.getCustomer());
		} catch (ModelException e) {
			fail();
		}
	}

	/**
	 * Tests the placeOrder method
	 */
	@Test
	public void testPlaceOrder() {
		try {
			o1.addMenuItem(m1);
			manager.placeOrder(o1);
			assertEquals(1, manager.getOrders().length);
			assertEquals(14, manager.getOrders()[0].getNumber());
			
			Order o = new Order(14, c1);
			o.addMenuItem(m2);
			manager.placeOrder(o);
			fail();
		} catch (ModelException e) {
			assertEquals("An order with this number already exists", e.getMessage());
			assertEquals(1, manager.getOrders().length);
		}
	}
	
	/**
	 * Tests the getOrders method
	 */
	@Test
	public void testGetOrders() {
		try {
			Order o = manager.getNextOrder(c1);
			o.addMenuItem(m3);
			assertEquals(16, o.getNumber());
			assertEquals(c1, o.getCustomer());
			
			assertEquals(0, manager.getOrders().length);
			manager.placeOrder(o);
			assertEquals(1, manager.getOrders().length);
			assertEquals(o, manager.getOrders()[0]);
		} catch (ModelException e) {
			fail();
		}
	}
	
	/**
	 * Test getOrdersByCustomer
	 */
	@Test
	public void testGetOrdersByCustomer() {
		try {
			Order o = manager.getNextOrder(c1);
			o.addMenuItem(m4);
			assertEquals(16, o.getNumber());
			assertEquals(c1, o.getCustomer());
			manager.placeOrder(o);
			
			Order[] arr = new Order[1];
			arr[0] = o;
			assertArrayEquals(arr, manager.getOrdersByCustomer(c1));
		} catch (ModelException e) {
			fail();
		}
	}
	
	/**
	 * tests the Cancel Order method
	 */
	@Test
	public void testCancelOrder() {
		try {
			Order o = manager.getNextOrder(c1);
			o.addMenuItem(m5);
			assertEquals(16, o.getNumber());
			assertEquals(c1, o.getCustomer());
			
			manager.placeOrder(o);
			assertEquals(1, manager.getOrders().length);
			
			manager.cancelOrder(o);
			assertEquals(0, manager.getOrders().length);
		} catch (ModelException e) {
			fail();
		}
		
		try {
			o2.addMenuItem(m1);
			manager.placeOrder(o2);
			manager.cancelOrder(o1);
		} catch (ModelException e) {
			assertEquals("Order does not exist", e.getMessage());
		}
	}
	
	/**
	 * Test fulfillOrder method
	 */
	@Test
	public void testFulfillOrder() {
		try {
			Order o = manager.getNextOrder(c1);
			o.addMenuItem(m1);
			assertEquals(16, o.getNumber());
			assertEquals(c1, o.getCustomer());
			
			manager.placeOrder(o);
			assertEquals(1, manager.getOrders().length);
			
			manager.fulfillOrder(o);
			assertEquals(0, manager.getOrders().length);
		} catch (ModelException e) {
			fail();
		}
		
		try {
			o2.addMenuItem(m1);
			manager.placeOrder(o2);
			manager.fulfillOrder(o1);
		} catch (ModelException e) {
			assertEquals("Order does not exist", e.getMessage());
		}
	}
	
	/**
	 * Tests the removeAllOrders method
	 */
	@Test
	public void testRemoveAllOrders() {
		try {
			Order o = manager.getNextOrder(c1);
			o.addMenuItem(m2);
			assertEquals(16, o.getNumber());
			assertEquals(c1, o.getCustomer());
			
			manager.placeOrder(o);
			assertEquals(1, manager.getOrders().length);
			
			manager.removeAllOrders();
			assertEquals(0, manager.getOrders().length);
		} catch (ModelException e) {
			fail();
		}
	}
}
