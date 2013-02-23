package edu.hkcity.cs.unittest;


import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.hkcity.cs.Formatter;

public class FormatterTest {
	private Formatter fm;
	@Before
	public void setUp() throws Exception {
		fm = new Formatter();
	}

	@After
	public void tearDown() throws Exception {
		fm = null;
	}

	@Test 
	public void testDeleteBlankLine_1() {
		String str = "\n\n\r\nHello worid!\r\n\r\n\n\n";
		assertEquals("Hello worid!\n", fm.format(str) );
	}
	
	@Test 
	public void testDeleteBlankLine_2() {
		String str = "\n\r\n";
		assertEquals("", fm.format(str) );
	}
	
	@Test 
	public void testDeleteBlankLine_3() {
		String str = "\r\n\r\nO\r\n\r\n\r\n\rK\n\r\n!\r\n";
		assertEquals("O\nK\n!\n", fm.format(str) );
	}
	
	@Test 
	public void testSimplyMultispace_1() {
		String str = "                  Hello world!            ";
		assertEquals(" Hello world! ", fm.format(str) );
	}
	
	@Test
	public void testSimplyMultispace_2() {
		String str = "\tHello\t world! \t";
		assertEquals(" Hello world! ", fm.format(str) );
	}
	
	public void testSimplyMultispace_3() {
		String str = "\t\t \t   \t     \t\t\t\t    \t \t \t    \t";
		assertEquals(" ", fm.format(str) );
	}
	
	@Test 
	public void reference_test_() {
		String str = "Hi";
		assertEquals("Hi", fm.format(str) );
	}
	
}
