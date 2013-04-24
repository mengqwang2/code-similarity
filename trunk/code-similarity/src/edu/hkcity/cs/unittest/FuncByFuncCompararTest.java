package edu.hkcity.cs.unittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.hkcity.cs.Comparar;
import edu.hkcity.cs.FuncByFuncComparar;
import edu.hkcity.cs.Formatter;
import edu.hkcity.cs.Output;

public class FuncByFuncCompararTest {
	@Before
	public void setUp() throws Exception {
	}

	@Test
	// Test FuncByFuncComparar compare function (2 different function call statement)
	public void testCompare_1() {
		String ori = "int main () { x();}";
		String tar = "int main () { y();}";
		Comparar c = new FuncByFuncComparar(tar, ori);
		assertEquals(c.compare(new Formatter(), new Output()), "0.6666");
	}

	@Test
	// Test FuncByFuncComparar compare function (3 function call statement, different order)
	public void testCompare_2() {
		String ori = "int main () { x();y();z();}";
		String tar = "int main () { y();z();x();}";
		Comparar c = new FuncByFuncComparar(tar, ori);
		assertEquals(c.compare(new Formatter(), new Output()), "0.8");
	}

	@Test
    // Test FuncByFuncComparar compare function (different function name and statements)
	public void testCompare_3() {
		String ori = "int ha () { }";
		String tar = "int main () { y();z();}";
		Comparar c = new FuncByFuncComparar(tar, ori);
		assertEquals(c.compare(new Formatter(), new Output()), "0.5");
	}

	@Test
    // Test FuncByFuncComparar compare function (different function name and statements, parameter reversed )
	public void testCompare_4() {
		String ori = "int main () { y();z();}";
		String tar = "int ha () { }";
		Comparar c = new FuncByFuncComparar(tar, ori);
		assertEquals(c.compare(new Formatter(), new Output()), "0.25");
	}

	@Test
    // Test FuncByFuncComparar compare function (same function name ,different statements, parameter reversed )
	public void testCompare_5() {
		String ori = "int main () { y();z(); }";
		String tar = "int main () { }";
		Comparar c = new FuncByFuncComparar(tar, ori);
		assertEquals(c.compare(new Formatter(), new Output()), "0.5");
	}
	
	@Test
	// Test FuncByFuncComparar compare function (multi functions, partial identical)
	public void testCompare_6() {
		String ori = "int a(){a1();}\nint b(){b1();}\nint c(){c1();}\nint d(){d1();}";
		String tar = "int a(){a1();}\nint b(){b1();}";
		Comparar c = new FuncByFuncComparar(tar, ori);
		assertEquals(c.compare(new Formatter(), new Output()), "0.5");
	}
	
	@Test
	// Test FuncByFuncComparar compare function (multi functions, partial identical, parameter reversed )
	public void testCompare_7() {
		String ori = "int a(){a1();}\nint b(){b1();}";
		String tar = "int a(){a1();}\nint b(){b1();}\nint c(){c1();}\nint d(){d1();}";
		Comparar c = new FuncByFuncComparar(tar, ori);
		assertEquals(c.compare(new Formatter(), new Output()), "0.5");
	}
}
