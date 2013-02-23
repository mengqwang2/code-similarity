package edu.hkcity.cs.unittest;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.*;

import edu.hkcity.cs.*;
class LineByLineCompararStub extends LineByLineComparar{
	public LineByLineCompararStub(String tar,String ori){
		super(tar, ori);
	}
}

public class InputTest {
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
	public void testReadOrg() {
		Input in=new Input("File1.cpp","File2.cpp");
	}
}
