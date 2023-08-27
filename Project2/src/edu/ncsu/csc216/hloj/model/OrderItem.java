package edu.ncsu.csc216.hloj.model;

/**
 * Stores a menu item and the quantity of the item that was ordered
 * 
 * @author Haley Heath, Colin Scanlon
 *
 */
public class OrderItem implements Comparable<OrderItem> {

	/** The quantity of the item being ordered */
	private int quantity;
	/** The menuItem of the item being ordered */
	private MenuItem menuItem;
	
	/**
	 * Constructs an OrderItem using a menuItem as input
	 * @param menuItem being used in the orderItem
	 */
	public OrderItem(MenuItem menuItem) {
		this.menuItem = menuItem;
		quantity = 1;
	}
	
	/**
	 * Returns the quantity of the item being ordered
	 * @return integer of the quantity of the item being ordered
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * Sets the quantity of the item being ordered
	 * @param quantity integer of the quantity of the item being ordered
	 * @throws ModelException if the quantity is less than 1
	 */
	public void setQuantity(int quantity) throws ModelException {
		if(quantity < 1) {
			throw new ModelException("The quantity of an item in an order has to be greater than zero");
		}
		this.quantity = quantity;
	}
	
	/**
	 * Returns the menu item being ordered
	 * @return the menu item being ordered
	 */
	public MenuItem getMenuItem() {
		return menuItem;
	}
	
	/**
	 * Compares the item being ordered to another OrderItem
	 * @param other the other OrderItem being compared
	 * @return an integer representing the comparisons results
	 */
	public int compareTo(OrderItem other) {
		if (menuItem.getType().equals(other.getMenuItem().getType())) {
			return 0;
		}
		return menuItem.getType().compareTo(other.getMenuItem().getType());
	}
}
