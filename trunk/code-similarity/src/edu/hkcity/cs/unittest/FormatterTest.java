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
		String str = "Hello worid!\r\n\r\n";
		assertEquals("Hello worid!\n", fm.format(str) );
	}
	
	@Test 
	public void testDeleteBlankLine_2() {
		String str = "\r\n";
		assertEquals("\n", fm.format(str) );
	}
	
	@Test 
	public void testDeleteBlankLine_3() {
		String str = "\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n";
		assertEquals("\n", fm.format(str) );
	}
	
	@Test 
	public void testDeleteBlankLine_4() {
		String str = "Hi";
		assertEquals("Hi", fm.format(str) );
	}
	
}
