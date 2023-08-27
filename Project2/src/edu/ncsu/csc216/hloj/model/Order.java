package edu.ncsu.csc216.hloj.model;

import edu.ncsu.csc216.hloj.model.lists.SortedList;

/**
 * The Order class which stores each order's number, customer, and list of items
 * 
 * @author Haley Heath, Colin Scanlon
 *
 */
public class Order implements Comparable<Order> { 

	/**	The order number */
	private int number;
	/** The customer who placed the order */
	private Customer customer;
	/** The list of items ordered */
	private SortedList<OrderItem> items;
	
	/** 
	 * Constructs a new order
	 * @param number integer of the order's number
	 * @param customer Customer who placed the order
	 * @throws ModelException if the order number is less than 1
	 */
	public Order(int number, Customer customer) throws ModelException {
		if(number < 1) {
			throw new ModelException("Order numbers must be larger than zero");
		}
		this.number = number;
		this.customer = customer;
		items = new SortedList<OrderItem>();
	}	
	
	/**
	 * Gets the order item index of the menu item
	 * @param item menu item the index is needed for
	 * @return integer of the index of the menu item
	 */
	private int getOrderItemIndexForMenuItems(MenuItem item) {
		int idx = -1;
		for (int i = 0; i < items.size(); i++ ) {
			if (items.get(i).getMenuItem().getName().equals(item.getName())) {
				idx = i;
			}
		}
		return idx;
	}
	
	/**
	 * Adds a menu item to the order
	 * @param item menu item being added to the order
	 */
	public void addMenuItem(MenuItem item) {
		int idx = getOrderItemIndexForMenuItems(item);
		try {
			if (idx == -1) {
				OrderItem o = new OrderItem(item);
				items.add(o);
			} else {
				int num = items.get(idx).getQuantity() + 1;
				items.get(idx).setQuantity(num);
			}
		} catch (ModelException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	/**
	 * Removes a menu item from the order
	 * @param item menu item being removed from the order
	 */
	public void removeMenuItem(MenuItem item) {
		int idx = getOrderItemIndexForMenuItems(item);
		try {
			if (getOrderItemIndexForMenuItems(item) != -1) {
				int num = items.get(idx).getQuantity();
				
				if (num == 1) {
					items.remove(idx);
				} else {			
					items.get(idx).setQuantity(num - 1);
				}
			}
		} catch (ModelException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	/**
	 * Returns the order number
	 * @return integer of the order number
	 */
	public int getNumber() {
		return number;
	}
	
	/**
	 * Returns the customer who placed the order
	 * @return the Customer who placed the order
	 */
	public Customer getCustomer() {
		return customer;
	}
	
	/**
	 * Returns an array of the order items
	 * @return an array of the order items
	 */
	public OrderItem[] getItems() {
		OrderItem[] arr = new OrderItem[items.size()];
		for (int i = 0; i < items.size(); i++) {
			arr[i] = items.get(i);
		}
		
		return arr;
	}
	
	/**
	 * Returns the total cost of the order
	 * @return the total cost of the order
	 */
	public double getTotal() {
		double total = 0;
		for (OrderItem i: items) {
			double n = i.getQuantity() * i.getMenuItem().getPrice();
			total += n;
		}
		return total;
	}
	
	/**
	 * Returns the Order as a String
	 * @return String of the order in the format �#number for customerFirstName customerLastName - Total: $total�.
	 */
	@Override
	public String toString() {
		return "#" + Integer.toString(number) + " for " + customer.getFirstName() + " " + customer.getLastName() +
				" - Total: $" + getTotal();
	}
	
	/**
	 * Compares this order to another
	 * @param other order to be compared
	 * @return integer representing the result of the comparison
	 */
	@Override
	public int compareTo(Order other) {
		if (this.number < other.getNumber()) {
			return -1;
		} else if (this.number > other.getNumber()) {
			return 1;
		} else {
			return 0;
		}
	}

}
