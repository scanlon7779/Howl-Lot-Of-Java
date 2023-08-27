package edu.ncsu.csc216.hloj.model.lists;

import static org.junit.Assert.*;

import org.junit.Test;


/**
 * Tests the UniqueList class
 * 
 * @author Haley Heath
 *
 */
public class UniqueListTest {

	/**
	 * Tests the add method without an index
	 */
	@Test
	public void testAdd() {
		UniqueList<String> list = new UniqueList<String>(5);
		list.add("Hello");
		assertEquals("Hello", list.get(0));
		list.add("Hi");
		assertEquals("Hi", list.get(1));
		list.add("Go Pack");
		assertEquals("Go Pack", list.get(2));
	}
	
	/**
	 * Tests the add method with an index
	 */
	@Test
	public void testAddInt() {
		UniqueList<String> list = new UniqueList<String>(5);
		list.add("Hello");	
		list.add("Hi");
		list.add("Go Pack");
		
		list.add(1, "Wolf");
		
		assertEquals("Hello", list.get(0));
		assertEquals("Wolf", list.get(1));
		assertEquals("Hi", list.get(2));
		assertEquals("Go Pack", list.get(3));
		
		list.add(0, "Pack");
		
		assertEquals("Pack", list.get(0));
		assertEquals("Hello", list.get(1));
		assertEquals("Wolf", list.get(2));
		assertEquals("Hi", list.get(3));
		assertEquals("Go Pack", list.get(4));
		
		list.add(5, "Tuffy");
		
		assertEquals("Pack", list.get(0));
		assertEquals("Hello", list.get(1));
		assertEquals("Wolf", list.get(2));
		assertEquals("Hi", list.get(3));
		assertEquals("Go Pack", list.get(4));
		assertEquals("Tuffy", list.get(5));
	}
	
	/**
	 * Tests the remove method
	 */
	@Test
	public void testRemove() {
		UniqueList<String> list = new UniqueList<String>();
		list.add("Hello");	
		list.add("Hi");
		list.add("Go Pack");
		
		assertEquals("Hi", list.remove(1));
		assertEquals(2, list.size());
		assertEquals("Hello", list.get(0));
		assertEquals("Go Pack", list.get(1));
		
		assertEquals("Go Pack", list.remove(1));
		assertEquals(1, list.size());
		assertEquals("Hello", list.get(0));

		assertEquals("Hello", list.remove(0));
		assertEquals(0, list.size());
	}

}
