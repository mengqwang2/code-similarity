package edu.hkcity.cs.unittest;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.*;

import edu.hkcity.cs.Comparar;
import edu.hkcity.cs.PercentageOutput;

public class PercentageOutputTest {
	private final ByteArrayOutputStream output = new ByteArrayOutputStream();
	private PercentageOutput po;

	@Before
	public void setUp() {
		System.setOut(new PrintStream(output));
	}

	@After
	public void tearDown() {
		System.setOut(null);
		po = null;
	}

	@Test
	public void testDisplay_1() {
		Comparar c = new Comparar(0.3333333333);
		po = new PercentageOutput(c);
		po.display();
		assertEquals("33.33%\n", output.toString());
	}
	
	@Test
	public void testDisplay_2() {
		Comparar c = new Comparar(1);
		po = new PercentageOutput(c);
		po.display();
		assertEquals("100.00%\n", output.toString());
	}
}
