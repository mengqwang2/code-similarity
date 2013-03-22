package edu.hkcity.cs.unittest;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.hkcity.cs.PercentageOutput;

public class PercentageOutputTest {
	private final ByteArrayOutputStream output = new ByteArrayOutputStream();
	private PercentageOutput po;

	@Before
	public void setUp() {
		System.setOut(new PrintStream(output));
		po = new PercentageOutput();
	}

	@After
	public void tearDown() {
		System.setOut(null);
		po = null;
	}

	@Test
	public void testPrint_1() {
		po.print("0.333333333");
		assertEquals("33.33%\n", output.toString());
	}
	
	@Test
	public void testPrint_2() {
		po.print("1");
		assertEquals("100.00%\n", output.toString());
	}
	
	@Test
	public void testPrint_3() {
		po.print("0.99");
		assertEquals("99.00%\n", output.toString());
	}
}