package edu.ncsu.csc216.hloj.model.lists;

import java.util.AbstractList;

/**
 * Maintains a list of elements is a sorted order
 * @author colinscanlon
 *
 * @param <E> The type of elements that the list will hold
 */
public class SortedList<E extends Comparable<E>> extends AbstractList<E> {
	/** the size of the sorted list **/
	private int size;
	
	/** The list node for the first item in the list **/
	private ListNode front;

	
	private class ListNode {
		
		/** Data of an element */
		public E data;
		/** Next ListNode */
		public ListNode next;
		
		/**
		 * Constructs a listNode object with preset data
		 * @param data in the node
		 * @param next the next ListNode in the list
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}
	
	/**
	 * Constructor for the sorted list
	 */
	public SortedList() {
		this.front = null;
	}
	
	/**
	 * adds to the list in sorted order
	 * @param element the element to add to the list
	 * @return true if the element is successfully added
	 */
	@Override
	public boolean add(E element) {
		if (element == null) {
			throw new IllegalArgumentException();
		}
		if (size == 0) {
			front = new ListNode(element, null);
			size++;
			return true;
		} 
		
		if (element.compareTo(front.data) < 0){
			front = new ListNode(element, front);
			size++;
			return true;
		} else {
			ListNode current = front;
			while (current.next != null && current.next.data.compareTo(element) <= 0) {
				current = current.next;
			}
			current.next = new ListNode(element, current.next);
			size++;
			return true;
		}
	}
	
	/**
	 * Removes an object with the specified id from the list
	 * @param id The id of the object to remove from the list
	 * @return the element being removed
	 */
	@Override
	public E remove(int id) {
		if (id < 0 || id >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		E data;
		
		if (id == 0) {
			data = front.data;
			front = front.next;
		} else {
		
			ListNode current = front;
			
			for (int i = 0; i < id - 1; i++) {
				current = current.next;
			}
			data = current.next.data;
			current.next = current.next.next;
		}
		size--;
		return data;
	}
	
	/**
	 * Gets the element from the list with the specified id 
	 * @param id the id of the element to get
	 * @return the element with the given id;
	 */
	public E get(int id) {
		if (id < 0 || id >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		ListNode current = front;
		
		for (int i = 0; i < id; i++) {
			current = current.next;
		}
		
		return current.data;
	}
	
	/**
	 * Returns the size of the sorted list
	 * @return the size of the sorted list
	 */
	public int size() {
		return size;
	}

	
	
}
