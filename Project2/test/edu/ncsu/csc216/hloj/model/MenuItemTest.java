package edu.ncsu.csc216.hloj.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the MenuItem Class
 * @author Haley Heath, Colin Scanlon
 *
 */
public class MenuItemTest {

	/**
	 * Tests the getType() method
	 */
	@Test
	public void testGetType() {
		try {
			MenuItem m = new MenuItem("Coffee", "Latte", 2.5);
			assertEquals("Coffee", m.getType());
		} catch (ModelException e) {
			fail();
		}
	}

	/**
	 * Tests the getName method
	 */
	@Test
	public void testGetName() {
		try {
			MenuItem m = new MenuItem("Coffee", "Latte", 2.5);
			assertEquals("Latte", m.getName());
		} catch (ModelException e) {
			fail();
		}
	}

	/**
	 * Tests the getPrice method
	 */
	@Test
	public void testGetPrice() {
		try {
			MenuItem m = new MenuItem("Coffee", "Latte", 2.5);
			assertEquals(2.5, m.getPrice(), 0.01);
		} catch (ModelException e) {
			fail();
		}
	}

	/**
	 * Tests the toString method
	 */
	@Test
	public void testToString() {
		try {
			MenuItem m = new MenuItem("Coffee", "Latte", 2.5);
			assertEquals("(Coffee) Latte - $2.5", m.toString());
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
			MenuItem n = new MenuItem("Coffee", "Latte", 2.5);
			assertEquals(0, m.compareTo(n));
		} catch (ModelException e) {
			fail();
		}
		
		try {
			MenuItem m = new MenuItem("Coffee", "Latte", 2.5);
			MenuItem n = new MenuItem("Wrong", "Latte", 2.5);
			assertTrue(m.compareTo(n) < 0);
		} catch (ModelException e) {
			fail();
		}
		
		try {
			MenuItem m = new MenuItem("Coffee", "Latte", 2.5);
			MenuItem n = new MenuItem("Coffee", "Wrong", 2.5);
			assertEquals(0, m.compareTo(n));
		} catch (ModelException e) {
			fail();
		}
		
		try {
			MenuItem m = new MenuItem("Coffee", "Latte", 2.5);
			MenuItem n = new MenuItem("Coffee", "Latte", 3.0);
			assertEquals(0, m.compareTo(n));
		} catch (ModelException e) {
			fail();
		}
	}

}
