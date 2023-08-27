package edu.ncsu.csc216.hloj.model.lists;

import java.util.AbstractList;

/**
 * The UniqueList that maintains a list of elements that doesn't allow duplicate elements
 * @author colinscanlon
 *
 * @param <E> The type of elements in the list
 */
public class UniqueList<E> extends AbstractList<E> {

	/** The initial capacity of the list **/
	private static final int INIT_CAPACITY = 10;
	
	/** The local list variable that holds all the elements **/
	private E[] list;
	
	/** The size of the list **/
	private int size;
	
	/**
	 * The constructor for a unique list of capacity 0
	 */
	@SuppressWarnings("unchecked")
	public UniqueList() {
		list = (E[]) new Object [INIT_CAPACITY];
		size = 0;
	}
	
	/**
	 * The constructor for a unique list with a given capacity
	 * @param capacity The capacity of the list to create
	 */
	@SuppressWarnings("unchecked")
	public UniqueList(int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException();
		}
		list = (E[]) new Object [capacity];
		size = 0;
	}
	
	/**
	 * Adds and element to the list
	 * @param element the element to add
	 * @return if the element was added or not
	 */
	public boolean add(E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		for (int i = 0; i < size; i++) {
			if (list[i].equals(element)) {
				throw new IllegalArgumentException();
			} 
		}
		
		if (size() == list.length) {
			increaseCapacity();
		}
		
		list[size()] = element;
		size++;
		return true;
	}
	
	/**
	 * Adds a given element at a specific id
	 * @param id the id to add the element at
	 * @param element the element to add
	 */
	public void add(int id, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		validateIndex(id);
		
		for (int i = 0; i < size; i++) {
			if (list[i].equals(element)) {
				throw new IllegalArgumentException();
			} 
		}
		
		if (size() == list.length) {
			increaseCapacity();
		}
		
		for (int i = size(); i > id; i--) {
			list[i] = list[i - 1];
		}
		list[id] = element;
		size++;
	}
	
	/**
	 * Removes the element at a given id from the list
	 * @param id The id of the element to remove
	 * @return The element that was removed from the list
	 */
	public E remove(int id) { 
		if (id < 0 || id >= size) {
			throw new IndexOutOfBoundsException();
		}
		if (size == 0) {
			throw new IndexOutOfBoundsException();
		}
		E element = list[id];
		for (int i = id; i < size(); i++) {
			list[i] = list[i + 1];
		}
		size--;
		return element;
	}
	
	/**
	 * Gets the element at a given id
	 * @param id the id of the item to get
	 * @return The element at the given id
	 */
	public E get(int id) {
		if (id < 0 || id >= size) {
			throw new IndexOutOfBoundsException();
		}
		return list[id];
	}
	
	/**
	 * Returns the size of the list
	 * @return the size of the list
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Determines if the index given is valid
	 * @param index the index to asses for validity
	 */
	private void validateIndex(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
	}
	
	/**
	 * Increases the capacity of the list
	 */
	@SuppressWarnings("unchecked")
	private void increaseCapacity() {
		E[] old = list;
		list = (E[]) new Object [old.length * 2];
		
		for(int i = 0; i < old.length; i++) {
			list[i] = old[i];
		}
	}
	
}
