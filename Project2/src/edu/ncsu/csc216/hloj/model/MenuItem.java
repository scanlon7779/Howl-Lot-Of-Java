package edu.ncsu.csc216.hloj.model;

/**
 * Holds the name, type, and price of an item
 * @author Haley Heath, Colin Scanlon
 *
 */
public class MenuItem implements Comparable<MenuItem> {

	/** String of the item's type */
	private String type;
	/** String of the item's name */
	private String name;
	/** Double of the item's price */
	private double price;
	
	/**
	 * Constructor of MenuItem
	 * @param type String of the type of the item
	 * @param name String of the name of the item
	 * @param price Double of the price of the item
	 * @throws ModelException if the type or name are empty strings or only have whitespace, 
	 * 				or if price is less than or equal to 0
	 */
	public MenuItem(String type, String name, double price) throws ModelException {
		setType(type);
		setName(name);
		setPrice(price);
	}
	
	/**
	 * Returns the type of the item as a String
	 * @return String of item type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Sets the type of the item to the String input
	 * @param type String of item type
	 * @throws ModelException if the type is an empty string or only whitespace
	 */
	public void setType(String type) throws ModelException {
		if("".equals(type.strip())) {
			throw new ModelException("The type of the menu item cannot be empty");
		}
		this.type = type.strip();
	}
	
	/**
	 * Returns the name of the item as a String
	 * @return String of item name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of the item to the String input
	 * @param name String of item name
	 * @throws ModelException if the name is an empty string or only whitespace
	 */
	public void setName(String name) throws ModelException {
		if("".equals(name.strip())) {
			throw new ModelException("The name of the menu item cannot be empty");
		}
		this.name = name.strip();
	}
	
	/**
	 * Returns the price of the item as a double
	 * @return double of item price
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Sets the price of the item to the double input
	 * @param price double of item price
	 * @throws ModelException if the price is less than or equal to 0
	 */
	public void setPrice(double price) throws ModelException {
		if(price <= 0) {
			throw new ModelException("The price of the menu item must be greater than zero");
		}
		this.price = price;
	}
	
	/**
	 * Returns a String of the item
	 * @return String of item in the format "(type) name- $price"
	 */
	@Override
	public String toString() {
		return "(" + type + ") " + name + " - $" + price;
	}
	
	/**
	 * Compares the menuItem to another menuItem
	 * @param other menu item being compared
	 * @return an integer representing the results of the comparison
	 */
	@Override
	public int compareTo(MenuItem other) {
		return type.toLowerCase().compareTo(other.getType().toLowerCase());
	}
}
