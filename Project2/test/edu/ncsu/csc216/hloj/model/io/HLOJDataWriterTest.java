package edu.ncsu.csc216.hloj.model.io;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.hloj.model.manager.CustomerManager;
import edu.ncsu.csc216.hloj.model.manager.MenuManager;
import edu.ncsu.csc216.hloj.model.manager.OrderManager;



/**
 * Tests for the HLOJ Data Writer
 * @author colinscanlon
 *
 */
public class HLOJDataWriterTest {

	/**
	 * Test for writing a file to a valid file 
	 */
	@Test
	public void testValidFile() {
		CustomerManager cm = CustomerManager.getInstance();
        MenuManager mm = MenuManager.getInstance();
		OrderManager om = OrderManager.getInstance();

		HLOJDataReader.readData("test-files/data1.txt");
		HLOJDataWriter.writeData("test-files/data1_writer.txt"); 
		checkFiles("test-files/data1_writer_expected.txt", "test-files/data1_writer.txt");
		HLOJDataReader.readData("test-files/data1_writer.txt"); 
        assertEquals("Ignacio X.", cm.getCustomer(0).getFirstName());
        assertEquals("Heckman", cm.getCustomer(1).getLastName());
        assertEquals("Large Coffee", mm.getMenuItem(0).getName());
        assertEquals("Chai Latte", mm.getMenuItem(2).getName());
        assertEquals("Donut", mm.getMenuItem(1).getName());
        assertEquals(3, om.getOrders().length);
       	assertEquals(3, om.getOrders()[1].getItems().length);

        


		

	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));
			 
			int count = 1;
			
			while (expScanner.hasNextLine()) {
				assertEquals("Difference on line " + count, expScanner.nextLine(), actScanner.nextLine());
				count++;
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
