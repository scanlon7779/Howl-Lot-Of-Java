package edu.ncsu.csc216.hloj.model.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.hloj.model.MenuItem;
import edu.ncsu.csc216.hloj.model.ModelException;

/**
 * Tests the MenuManager class
 * 
 * @author Haley Heath
 *
 */
public class MenuManagerTest {

	/** The CustomerManager for testing */
	private MenuManager manager = MenuManager.getInstance();
	/** A menu item for testing */
	private MenuItem item1;
	/** A menu item for testing */
	private MenuItem item2;
	/** A menu item for testing */
	private MenuItem item3;
	/** A menu item for testing */
	private MenuItem item4;
	
	/**
	 * Sets up for tests
	 */
	@Before
	public void setUp() {
		manager.removeAllMenuItems();
		try {
			item1 = new MenuItem("Coffee", "Latte", 2.5);
			item2 = new MenuItem("Pastries", "Bagel", 2.5);
			item3 = new MenuItem("Coffee", "Machiatto", 2.5);
			item4 = new MenuItem("Tea", "Iced Chai", 2.5);
		} catch (ModelException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	/**
	 * Tests the addMenuItem method
	 */
	@Test
	public void testAddMenuItem() {
		manager.addMenuItem(item1);
		manager.addMenuItem(item2);
		manager.addMenuItem(item3);
		manager.addMenuItem(item4);
		assertEquals(4, manager.getMenuItems().length);
	}
	
	/**
	 * Tests the getMenuItems method
	 */
	@Test
	public void testGetMenuItems() {
		manager.addMenuItem(item1);
		manager.addMenuItem(item2);
		manager.addMenuItem(item3);
		manager.addMenuItem(item4);
		
		assertEquals(4, manager.getMenuItems().length);
		assertEquals(item1, manager.getMenuItems()[0]);
		assertEquals(item3, manager.getMenuItems()[1]);
		assertEquals(item2, manager.getMenuItems()[2]);
		assertEquals(item4, manager.getMenuItems()[3]);
	}
	
	/**
	 * Tests the getMenuItem method
	 */
	@Test
	public void testGetMenuItem() {
		manager.addMenuItem(item1);
		manager.addMenuItem(item2);
		manager.addMenuItem(item3);
		manager.addMenuItem(item4);
		
		assertEquals(4, manager.getMenuItems().length);
		assertEquals(item1, manager.getMenuItem(0));
		assertEquals(item3, manager.getMenuItem(1));
		assertEquals(item2, manager.getMenuItem(2));
		assertEquals(item4, manager.getMenuItem(3));
	}

	/**
	 * Tests the removeMenuItem method
	 */
	@Test
	public void testRemoveMenuItem() {
		manager.addMenuItem(item1);
		manager.addMenuItem(item2);
		manager.addMenuItem(item3);
		manager.addMenuItem(item4);
		
		assertEquals(4, manager.getMenuItems().length);
		assertEquals(item1, manager.getMenuItem(0));
		assertEquals(item3, manager.getMenuItem(1));
		assertEquals(item2, manager.getMenuItem(2));
		assertEquals(item4, manager.getMenuItem(3));
		
		try {
			manager.removeMenuItem(2);
			
			assertEquals(3, manager.getMenuItems().length);
			assertEquals(item1, manager.getMenuItem(0));
			assertEquals(item3, manager.getMenuItem(1));
			assertEquals(item4, manager.getMenuItem(2));
		} catch (ModelException e) {
			fail();
		}
		
	}
	
	/**
	 * Tests the editMenuItem method
	 */
	@Test
	public void testEditMenuItem() {
		manager.addMenuItem(item1);
		manager.addMenuItem(item2);
		manager.addMenuItem(item3);
		
		assertEquals(3, manager.getMenuItems().length);
		assertEquals(item1, manager.getMenuItem(0));
		assertEquals(item3, manager.getMenuItem(1));
		assertEquals(item2, manager.getMenuItem(2));
		
		manager.editMenuItem(1, item4);
		
		assertEquals(3, manager.getMenuItems().length);
		assertEquals(item1, manager.getMenuItem(0));
		assertEquals(item2, manager.getMenuItem(1));
		assertEquals(item4, manager.getMenuItem(2));
	}
	
	/**
	 * Tests the removeAllMenuItems
	 */
	@Test
	public void testRemoveAllMenuItems() {
		manager.addMenuItem(item1);
		manager.addMenuItem(item2);
		manager.addMenuItem(item3);
		manager.addMenuItem(item4);
		assertEquals(4, manager.getMenuItems().length);
		
		manager.removeAllMenuItems();
		assertEquals(0, manager.getMenuItems().length);
		
	}
}
