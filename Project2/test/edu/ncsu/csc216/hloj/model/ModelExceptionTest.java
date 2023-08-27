package edu.ncsu.csc216.hloj.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the ModelException class
 * @author Haley Heath, Colin Scanlon
 *
 */
public class ModelExceptionTest {

	/**
	 * Tests the ModelException constructor without a param
	 */
	@Test
	public void testModelException() {
		ModelException me = new ModelException();
		assertEquals("Model Exception", me.getMessage());
	}

	/**
	 * Tests the ModelException constructor with a param
	 */
	@Test
	public void testModelExceptionString() {
		ModelException me = new ModelException("No order picked");
		assertEquals("No order picked", me.getMessage());
	}

}
