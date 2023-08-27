package edu.ncsu.csc216.hloj.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the OrderItem class
 * @author Haley Heath, Colin Scanlon
 *
 */
public class OrderItemTest {

	/**
	 * Tests the getQuantity method
	 */
	@Test
	public void testGetQuantity() {
		try {
			OrderItem o = new OrderItem(new MenuItem("Coffee", "Latte", 2.5));
			o.setQuantity(5);
			assertEquals(5, o.getQuantity());
			int num = o.getQuantity();
			o.setQuantity(num + 1);
			assertEquals(6, o.getQuantity());
		} catch (ModelException e) {
			fail();
		}
	}

	/**
	 * Tests the getMenuItem method
	 */
	@Test
	public void testGetMenuItem() {
		try {
			MenuItem m = new MenuItem("Coffee", "Latte", 2.5);
			OrderItem o = new OrderItem(m);
			assertEquals(m, o.getMenuItem());
		} catch (ModelException e) {
			fail();
		}
	}

	/**
	 * Tests the compareTo method
	 */
	@Test
	public void testCompareTo() {
		try {
			MenuItem m = new MenuItem("Coffee", "Latte", 2.5);
			OrderItem o = new OrderItem(m);
			OrderItem p = new OrderItem(m);
			assertEquals(0, o.compareTo(p));
		} catch (ModelException e) {
			fail();
		}
		
		try {
			OrderItem o = new OrderItem(new MenuItem("Coffee", "Latte", 2.5));
			OrderItem p = new OrderItem(new MenuItem("Tea", "Green Tea", 2.5));
			assertTrue(o.compareTo(p) < 0);
		} catch (ModelException e) {
			fail();
		}
	}

}
