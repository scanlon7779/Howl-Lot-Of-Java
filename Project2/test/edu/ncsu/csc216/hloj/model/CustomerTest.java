package edu.ncsu.csc216.hloj.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the Customer class
 * @author Haley Heath, Colin Scanlon
 *
 */
public class CustomerTest {

	/**
	 * Tests the getFirstName method
	 */
	@Test
	public void testGetFirstName() {
		try {
			Customer c = new Customer("First", "Last", "flast");
			assertEquals("First", c.getFirstName());
		} catch (ModelException e) {
			fail();
		}
		
	}

	/**
	 * Tests the getLastName method
	 */
	@Test
	public void testGetLastName() {
		try {
			Customer c = new Customer("First", "Last", "flast");
			assertEquals("Last", c.getLastName());
		} catch (ModelException e) {
			fail();
		}	
	}

	/**
	 * Test the getId method
	 */
	@Test
	public void testGetId() {
		try {
			Customer c = new Customer("First", "Last", "flast");
			assertEquals("flast", c.getId());
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
			Customer c = new Customer("First", "Last", "flast");
			assertEquals("First Last (flast)", c.toString());
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
			Customer c = new Customer("First", "Last", "flast");
			Customer e = new Customer("First", "Last", "flast");
			assertEquals(0, c.compareTo(e));
		} catch (ModelException e) {
			fail();
		}	
		
		try {
			Customer c = new Customer("First", "Last", "flast");
			Customer e = new Customer("Wrong", "Last", "flast");
			assertTrue(c.compareTo(e) < 0);
		} catch (ModelException e) {
			fail();
		}
		
		try {
			Customer c = new Customer("First", "Last", "flast");
			Customer e = new Customer("First", "Wrong", "flast");
			assertTrue(c.compareTo(e) < 0);
		} catch (ModelException e) {
			fail();
		}
		
	}

}
