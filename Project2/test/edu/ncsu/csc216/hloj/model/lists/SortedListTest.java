package edu.ncsu.csc216.hloj.model.lists;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the sorted List
 * @author colinscanlon
 *
 */
public class SortedListTest {

	/**
	 * Testing the add method
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		list.add("apple");
		list.add("banana");
		assertEquals(2, list.size());
		list.add("carrot");
		assertEquals(3, list.size());
		list.add("zebra");
		assertEquals(4, list.size());
		list.add("potato");
		assertEquals(5, list.size());
		list.add("tofu");
		assertEquals(6, list.size());
		list.add("zeb");
		assertEquals(7, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("carrot", list.get(2));
		assertEquals("potato", list.get(3));
		assertEquals("tofu", list.get(4));
		assertEquals("zeb", list.get(5));
		assertEquals("zebra", list.get(6));
	}
	
	/**
	 * Testing the add method
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		try {
			list.add("apple");
			list.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			//Success
		}
		
		try {
			list.add("apple");
			list.remove(10);
			fail();
		} catch (IndexOutOfBoundsException e) {
			//Success
		}
		
		SortedList<String> list2 = new SortedList<String>();
		list2.add("apple");
		list2.add("banana");
		list2.add("carrot");
		assertEquals("apple", list2.remove(0));
		assertEquals(2, list2.size());
		assertEquals("carrot", list2.remove(1));
		assertEquals("banana", list2.get(0));
		list2.add("apple");
		list2.add("carrot");
		assertEquals("apple", list2.get(0));
		assertEquals("banana", list2.get(1));
		assertEquals("carrot", list2.get(2));
		assertEquals("banana", list2.remove(1));
		assertEquals(2, list2.size()); 
		
		
	}
	
	/**
	 * Testing the add method
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		list.add("carrot");
		list.add("banana");
		list.add("apple");
		assertEquals(3, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("carrot", list.get(2));
		assertEquals("banana", list.get(1));
		assertEquals("carrot", list.get(2));
		
	}
	

}
