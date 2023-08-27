package edu.ncsu.csc216.hloj.model.manager;

import edu.ncsu.csc216.hloj.model.Customer;
import edu.ncsu.csc216.hloj.model.ModelException;
import edu.ncsu.csc216.hloj.model.Order;
import edu.ncsu.csc216.hloj.model.lists.SortedList;

/**
 * The order manager that processes all orders and manipulates the list of orders in the system
 * @author Haley Heath
 *
 */
public class OrderManager {

	/** Instance of OrderManager */
	private static OrderManager instance;
	
	/** List of orders */
	private SortedList<Order> orders;
	
	/** The order number on the most recent order **/
	private int lastOrderNumber;
	
	/** the maximum number of orders one customer can have **/
	private static final int MAX_ORDERS_PER_CUSTOMER = 3;
	
	/**
	 * The private constructor of OrderManager
	 */
	private OrderManager() {
		orders = new SortedList<Order>();
		lastOrderNumber = 0;
	}
	
	/** 
	 * Returns the single instance of order manager
	 * @return The instance of order manager
	 */
	public static OrderManager getInstance() {
		if (instance == null) {
			instance = new OrderManager();
		}
		return instance;
	}
	
	/**
	 * Gets the order number of the last order
	 * @return the last order number
	 */
	public int getLastOrderNumber() {
		return lastOrderNumber;
	}
	
	/**
	 * Sets the order number of the last order
	 * @param num The number to set the last order number to
	 */
	public void setLastOrderNumber(int num) {
		this.lastOrderNumber = num;
	}
	
	/**
	 * Gets the next order of the customer
	 * @param c The customer who's orders they are
	 * @return The next Order
	 * @throws ModelException if the customer has 3 open orders already
	 */
	public Order getNextOrder(Customer c) throws ModelException { 
		int count = 0;
		for(int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getCustomer().equals(c)) {
				count++;
			}
		}
		if(count >= MAX_ORDERS_PER_CUSTOMER) {
			throw new ModelException("A customer cannot have more than 3 open orders");
		} else {
			Order o = new Order(lastOrderNumber + 1, c);
			setLastOrderNumber(o.getNumber());
			return o;
		}
	}
	
	/**
	 * Places the given order into the list
	 * @param o the order to place
	 * @throws ModelException If an order with this number already exists or the customer has 3 orders
	 */
	public void placeOrder(Order o) throws ModelException {
		Customer c = o.getCustomer();
		int count = 0;
		for(int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getCustomer().equals(c)) {
				count++;
			}
			if (orders.get(i).getNumber() == o.getNumber()) {
				throw new ModelException("An order with this number already exists");
			}
		}
		
		if(count >= MAX_ORDERS_PER_CUSTOMER) {
			throw new ModelException("A customer cannot have more than 3 open orders");
		}
		
		if(o.getItems().length == 0) {
			throw new ModelException("Orders can only be placed if they contain at least one item");
		}
		
		if(o.getNumber() > getLastOrderNumber()) {
			throw new ModelException("Order number is invalid");
		}
		
		orders.add(o);
	}
	
	/**
	 * Gets an array of all the orders
	 * @return The array of orders
	 */
	public Order[] getOrders() {
		Order[] orderArray = new Order[orders.size()];
		for(int i = 0; i < orders.size(); i++) {
			orderArray[i] = orders.get(i);
		}
		return orderArray;
	}
	
	/**
	 * Gets an array of orders for a given customer
	 * @param c The customer who's orders they are
	 * @return The array of orders for the customer
	 */
	public Order[] getOrdersByCustomer(Customer c) {
		int count = 0;
		for(int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getCustomer().equals(c)) {
				count++;
			}
		}

		Order[] orderArray = new Order[count];
		
		for(int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getCustomer().equals(c)) {
				if (orderArray[0] == null) {
					orderArray[0] = orders.get(i);
				} else if (orderArray.length > 1 && orderArray[1] == null) {
					orderArray[1] = orders.get(i);
				} else {
					orderArray[2] = orders.get(i);
				}
			}
		}
		
		return orderArray;
	}
	
	/**
	 * Gets an order with the given id from the list
	 * @param id The id of the order to get
	 * @return The order with the given id
	 */
	public Order getOrder(int id) {
		return orders.get(id);
	}
	
	/**
	 * Cancels the given order
	 * @param o The order to be canceled
	 * @throws ModelException is thrown if the order does not exist
	 */
	public void cancelOrder(Order o) throws ModelException {
		for (int i = 0; i < orders.size(); i++) {
			if(orders.get(i).equals(o)) {
				orders.remove(i);
				return;
			}
		}
		throw new ModelException("Order does not exist");
	}
	
	/**
	 * Fulfills the given order
	 * @param o The order to fulfill
	 * @throws ModelException is thrown if the order does not exist
	 */
	public void fulfillOrder(Order o) throws ModelException {
		for (int i = 0; i < orders.size(); i++) {
			if(orders.get(i).equals(o)) {
				if (i == 0) {
					orders.remove(i);
					return;
				} else {
					throw new ModelException("Orders must be fulfilled in the order in which they were placed");
				}
			}
		}
		throw new ModelException("Order does not exist");
	}
	
	/**
	 * Removes all orders from the order manager
	 */
	public void removeAllOrders() {
		while(orders.size() != 0) {
			orders.remove(0);
		}
		setLastOrderNumber(0);
	}
	
}

