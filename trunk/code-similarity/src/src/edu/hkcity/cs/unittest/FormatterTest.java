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
	public void reference_test() {
		String str = "Normal Text";
		assertEquals("Normal Text", fm.format(str) );
	}
	
	@Test
	public void testDeleteIndent_1() {
		String str = " Hello world!";
		assertEquals("Hello world!", fm.format(str) );
	}
	
	@Test
	public void testDeleteIndent_2() {
		String str = "\tHello world!";
		assertEquals("Hello world!", fm.format(str) );
	}
	
	@Test
	public void testDeleteIndent_3() {
		String str = "         Hello world!";
		assertEquals("Hello world!", fm.format(str) );
	}
	
	@Test
	public void testDeleteIndent_4() {
		String str = "\t\t\t\t\t\tHello world!";
		assertEquals("Hello world!", fm.format(str) );
	}
	
	@Test
	public void testDeleteIndent_5() {
		String str = "\t   \t \t\t    Hello world!";
		assertEquals("Hello world!", fm.format(str) );
	}
	
	@Test 
	public void testSimplyMultispace_1() {
		String str = "Hello        world!            ";
		assertEquals("Hello world! ", fm.format(str) );
	}
	
	@Test
	public void testSimplyMultispace_2() {
		String str = "Hello\t     \t world! \t";
		assertEquals("Hello world! ", fm.format(str) );
	}
	
	@Test
	public void testSimplyMultispace_3() {
		String str = "O\t\t \t   \t     \t\t\t\t    \t \t \t    \tK";
		assertEquals("O K", fm.format(str) );
	}
	
	@Test
	public void testDeleteComment_1() {
		String str = "System.out.println(\"Hi!\\n\")// Say Hi!\nreturn 0;/*\n*Bye!\n*/";
		assertEquals("System.out.println(\"Hi!\\n\")\nreturn 0;", fm.format(str) );
	}
	
	@Test
	public void testDeleteComment_2() {
		String str = "System.out.println(\"Hi!\\n\")/*\n*Say Hi!\n*/\nreturn 0;/*\n*Bye!\n*/";
		assertEquals("System.out.println(\"Hi!\\n\")\nreturn 0;", fm.format(str) );
	}
	
	@Test
	public void testDeleteComment_3() {
		String str = "System.out.println(\"Hi!\\n\")// Say Hi!\nreturn 0;//Bye!";
		assertEquals("System.out.println(\"Hi!\\n\")\nreturn 0;", fm.format(str) );
	}
	
	@Test
	public void testDeleteComment_4() {
		String str = "System.out.println(\"Hi!\\n\")/*\n*Say Hi!\n*/\nreturn 0;//Bye!";
		assertEquals("System.out.println(\"Hi!\\n\")\nreturn 0;", fm.format(str) );
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
}
