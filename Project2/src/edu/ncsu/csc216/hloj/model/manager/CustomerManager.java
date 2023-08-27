package edu.ncsu.csc216.hloj.model.manager;

import edu.ncsu.csc216.hloj.model.Customer;
import edu.ncsu.csc216.hloj.model.ModelException;
import edu.ncsu.csc216.hloj.model.lists.SortedList;

/**
 * Customer manager maintains and manipulates a list of customers 
 * @author Colin Scanlon, Haley Heath
 *
 */
public class CustomerManager {

	/** instance of itself */
	private static CustomerManager instance;
	
	/**	List of customers */
	private SortedList<Customer> customers;
	
	/**
	 * Private constructor for the Customer Manager
	 */
	private CustomerManager() {
		customers = new SortedList<Customer>();
	}
	
	/** 
	 * Returns the instance of the customer manager
	 * @return the one instance of customer manager
	 */
	public static CustomerManager getInstance() {
		if (instance == null) {
			instance = new CustomerManager();
		}
		return instance;
	}
	
	/**
	 * Adds a Customer to the customer list
	 * @param c the Customer to add
	 * @throws ModelException this is just for a little bit and then we'll get rid of it
	 */
	public void addCustomer(Customer c) throws ModelException {
		for (Customer cu : customers) {
			if (cu.getId().equals(c.getId())) {
				throw new ModelException("A customer with this id already exists");
			}
		}
		customers.add(c);
	}
	
	/**
	 * Gets an array of Customers
	 * @return an array of Customers
	 */
	public Customer[] getCustomers() {
		Customer[] ret = new Customer[customers.size()];
		for (int i = 0; i < customers.size(); i++) {
			ret[i] = customers.get(i);
		}
		return ret;
	}
	
	/**
	 * Gets a customer at a given id
	 * @param id the id of the customer to get
	 * @return the customer with the given id
	 */
	public Customer getCustomer(int id) {
		return customers.get(id);
	}
	
	/**
	 * Gets the customer with the given custom string
	 * @param s the custom string of the customer
	 * @return The customer with the custom string
	 */
	public Customer getCustomer(String s) {
		for (int i = 0; i < customers.size(); i++) {
			if (s.equals(customers.get(i).getId())) {
				return customers.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Removes the given customer from the list
	 * @param c the customer to remove from the list
	 * @throws ModelException if the customer exists of has open orders
	 */
	public void removeCustomer(Customer c) throws ModelException {
		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).equals(c)) {
				if (OrderManager.getInstance().getOrdersByCustomer(c).length != 0) {
					throw new ModelException("Cannot remove a customer with open orders");
				}
				customers.remove(i);
				return;
			}
		}
		throw new ModelException("Customer does not exist.");
	}
	
	/**
	 * Edits the given customer with the given id
	 * @param id the id of the customer to edit
	 * @param c the customer to edit
	 * @throws ModelException the new customer has the same id as another but not the one being edited
	 */
	public void editCustomer(int id, Customer c) throws ModelException {
		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).equals(c) && id != i) {
				throw new ModelException("You are adding a customer that already exists");
			}
		}
		
		for (int i = 0; i < customers.size(); i++) {
			if (id == i) {
				Customer j = customers.remove(id);
				try {
					addCustomer(c);
				} catch (ModelException e) {
					customers.add(j);
					throw e;
				}
				return;
			}
		}
		throw new IndexOutOfBoundsException();
		
	}
	
	/**
	 * Removes all customers from the list
	 */
	public void removeAllCustomers() {
		customers = new SortedList<Customer>();
	}
}
