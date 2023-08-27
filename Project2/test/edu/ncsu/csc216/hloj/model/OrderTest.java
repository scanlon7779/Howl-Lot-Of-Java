package edu.ncsu.csc216.hloj.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the Order class
 * @author Haley Heath
 *
 */
public class OrderTest {
	
	/** Customer used for testing */
	private Customer c;
	/** Order used for testing */
	private Order o;
	/** MenuItem for testing */
	private MenuItem m1;
	/** MenuItem for testing */
	private MenuItem m2;
	/** MenuItem for testing */
	private MenuItem m3;
	
	/**
	 * Sets up for testing
	 */
	@Before
	public void setUp() {
		try {
			c = new Customer("First", "Last", "flast");
			o = new Order(1, c);
			m1 = new MenuItem("Coffee", "Latte", 2.5);
			m2 = new MenuItem("Tea", "Chai Latte", 3.0);
			m3 = new MenuItem("Pastries", "Bagel", 2.5);
		} catch (ModelException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	/**
	 * Tests the AddMenuItem method
	 */
	@Test
	public void testAddMenuItem() {
		assertEquals(0, o.getItems().length);
		o.addMenuItem(m1);
		assertEquals(1, o.getItems().length);
		o.addMenuItem(m1);
		assertEquals(1, o.getItems().length);
		assertEquals(2, o.getItems()[0].getQuantity());
		o.addMenuItem(m2);
		assertEquals(2, o.getItems().length);
		o.addMenuItem(m3);
		assertEquals(3, o.getItems().length);
		
		try {
			Customer c1 = new Customer("Odessa", "Howard", "ohoward");
			Order o1 = new Order(1, c1);
			MenuItem  m11 = new MenuItem("Tea", "Apple Tea", 3.0);
			MenuItem m12 = new MenuItem("Coffee", "Latte", 4.15);
			o1.addMenuItem(m11);
			o1.addMenuItem(m12);
			assertEquals(1, o1.getItems()[0].getQuantity());
			o1.addMenuItem(m12);
			assertEquals(2, o1.getItems()[0].getQuantity());
			
		} catch (ModelException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		
		
		
	}

	/**
	 * Tests the RemoveMenuItem method
	 */
	@Test
	public void testRemoveMenuItem() {
		assertEquals(0, o.getItems().length);
		o.addMenuItem(m1);
		assertEquals(1, o.getItems().length);
		o.addMenuItem(m1);
		assertEquals(1, o.getItems().length);
		assertEquals(2, o.getItems()[0].getQuantity());
		o.addMenuItem(m2);
		assertEquals(2, o.getItems().length);
		o.addMenuItem(m3);
		assertEquals(3, o.getItems().length);
		
		o.removeMenuItem(m3);
		assertEquals(2, o.getItems().length);
		o.removeMenuItem(m1);
		assertEquals(2, o.getItems().length);
		assertEquals(1, o.getItems()[0].getQuantity());
	}

	/**
	 * Tests the getNumber method
	 */
	@Test
	public void testGetNumber() {
		assertEquals(1, o.getNumber());
	}

	/**
	 * Tests the getCustomer method
	 */
	@Test
	public void testGetCustomer() {
		assertEquals(c, o.getCustomer());
	}

	/**
	 * Tests the getOrderItems method
	 */
	@Test
	public void testGetOrderItems() {
		try {
			// Creates order items
			OrderItem oi1 = new OrderItem(m1);
			oi1.setQuantity(2);
			OrderItem oi2 = new OrderItem(m2);
			OrderItem oi3 = new OrderItem(m3);
			
			// Adds items to order
			o.addMenuItem(m1);
			o.addMenuItem(m1);
			o.addMenuItem(m2);
			o.addMenuItem(m3);
			 
			OrderItem[] list = o.getItems();
			assertEquals(3, list.length);
			assertEquals(0, oi1.compareTo(list[0]));
			assertEquals(0, oi2.compareTo(list[2]));
			assertEquals(0, oi3.compareTo(list[1]));
			
		} catch (ModelException e) {
			fail();
		}
	}

	/**
	 * Tests the getTotal method
	 */
	@Test
	public void testGetTotal() {
		o.addMenuItem(m1);
		o.addMenuItem(m1);
		o.addMenuItem(m2);
		o.addMenuItem(m3);
		
		assertEquals(10.5, o.getTotal(), 0.01);
	}

	/**
	 * Tests the toString method
	 */
	@Test
	public void testToString() {
		o.addMenuItem(m1);
		o.addMenuItem(m1);
		o.addMenuItem(m2);
		o.addMenuItem(m3);
		
		assertEquals("#1 for First Last - Total: $10.5", o.toString());
	}

	/**
	 * Tests the compareTo method
	 */
	@Test
	public void testCompareTo() {
		try {
			Order o2 = new Order(1, c);
			o.addMenuItem(m1);
			o2.addMenuItem(m1);
			assertEquals(0, o.compareTo(o2));
		} catch (ModelException e) {
			fail();
		}
		
		try {
			Order o2 = new Order(2, c);
			o.addMenuItem(m1);
			o2.addMenuItem(m1);
			assertEquals(-1, o.compareTo(o2));
		} catch (ModelException e) {
			fail();
		}
	}

}
