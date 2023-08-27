package edu.ncsu.csc216.hloj.model;

/**
 * An exception that is thrown when business rules are broken
 * 
 * @author Haley Heath, Colin Scanlon
 *
 */
public class ModelException extends Exception {

	/**
	 * Default serial ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an exception
	 */
	public ModelException() {
		this("Model Exception");
	}
	
	/**
	 * Constructs an exception with a message.
	 * @param message String of message to be sent when error is thrown
	 */
	public ModelException(String message) {
		super(message);
	}
}
