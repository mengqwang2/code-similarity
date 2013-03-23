package edu.hkcity.cs.unittest;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.hkcity.cs.Output;

public class OutputTest {
	private final ByteArrayOutputStream output = new ByteArrayOutputStream();
	private Output po;

	@Before
	public void setUp() {
		System.setOut(new PrintStream(output));
		po = new Output();
	}

	@After
	public void tearDown() {
		//System.setOut(null);
		po = null;
	}

	@Test
	public void testPrint_1() {
		po.print("name", 0.333333333);
		assertEquals("name : 33.33%\n", output.toString());
	}
	
	@Test
	public void testPrint_2() {
		po.print("ss", 1);
		assertEquals("ss : 100.00%\n", output.toString());
	}
	
	@Test
	public void testPrint_3() {
		po.print("formatter", 0.99);
		assertEquals("formatter : 99.00%\n", output.toString());
	}
}
