package edu.hkcity.cs.unittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.hkcity.cs.FuncByFuncComparar;

public class FuncByFuncCompararTest {
	FuncByFuncComparar comp;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCheckSimilarity() {
		String tar ="#include <iostream>\nusingnamespace std;\nvoid main() {\n\tcout << \"hello world!\";\n}";
		String ori ="#include <iostream>\nusingnamespace standard;\nint main() {\n\tcout << \"hello world!\";\n}";
		comp = new FuncByFuncComparar();
		String result = Double.toString(comp.testCheckSim(tar, ori));
		//Double result = comp.testCheckSim(tar, ori);
		assertEquals(result, "0.5");
	}

}
