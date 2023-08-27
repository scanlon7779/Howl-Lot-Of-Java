package edu.ncsu.csc216.hloj.model;

/**
 * Represents a customer and holds their first name, last name, and id as Strings
 * @author Haley Heath, Colin Scanlon
 *
 */
public class Customer implements Comparable<Customer> {

	/** Customer's first name */
	private String firstName;
	/**	Customer's last name */
	private String lastName;
	/** Customer's ID */
	private String id;
	
	/**
	 * Constructs the customer using the first name, last name, and id	
	 * @param firstName String of customer's first name
	 * @param lastName String of customer's last name
	 * @param id String of customer's id
	 * @throws ModelException if firstName, lastName, or id are empty strings or have only whitespace
	 */
	public Customer(String firstName, String lastName, String id) throws ModelException {
		super();
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
	}

	/**
	 * Returns the first name of the customer
	 * @return the first name of the customer
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Sets the first name of the customer
	 * @param firstName String of the customer's first name
	 * @throws ModelException if firstName is empty string or has only whitespace
	 */
	public void setFirstName(String firstName) throws ModelException {
		if("".equals(firstName.strip())) {
			throw new ModelException("The first name of the customer cannot be empty");
		}
		this.firstName = firstName.strip();
	}
	
	/**
	 * Returns the last name of the customer
	 * @return the last name of the customer
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Sets the last name of the customer
	 * @param lastName String of the customer's last name
	 * @throws ModelException if lastName is empty string or has only whitespace
	 */
	public void setLastName(String lastName) throws ModelException {
		if("".equals(lastName.strip())) {
			throw new ModelException("The last name of the customer cannot be empty");
		}
		this.lastName = lastName.strip();
	}
	
	/**
	 * Returns the id of the customer
	 * @return the id of the customer
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Sets the id of the customer
	 * @param id String of the customer's ID
	 * @throws ModelException if id is empty string or has only whitespace
	 */
	public void setId(String id) throws ModelException {
		if("".equals(id.strip())) {
			throw new ModelException("The id of the customer cannot be empty");
		}
		this.id = id.strip();
	}

	/**
	 * Returns the Customer as a String
	 * @return String of Customer in the format "firstName lastName (id)"
	 */
	@Override
	public String toString() {
		return firstName + " " + lastName + " (" + id + ")";
	}	
	
	/**
	 * Compares the customer to another customer
	 * @param other customer being compared
	 * @return an integer representing the results of the comparison
	 */
	@Override
	public int compareTo(Customer other) {
		if (lastName.toLowerCase().equals(other.getLastName().toLowerCase())) {
			if (firstName.toLowerCase().equals(other.getFirstName().toLowerCase())) {
				return 0;
			}
			return firstName.toLowerCase().compareTo(other.getFirstName().toLowerCase());
		}
		return lastName.toLowerCase().compareTo(other.getLastName().toLowerCase());
	}
}
