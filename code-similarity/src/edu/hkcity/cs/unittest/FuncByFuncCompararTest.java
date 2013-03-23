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
	public void testCompare_1() {
		String ori = "int main () { x();}";
		String tar = "int main () { y();}";
		Comparar c = new FuncByFuncComparar(tar, ori);
		assertEquals(c.compare(new Formatter(), new Output()), "0.5");
	}

	@Test
	public void testCompare_2() {
		String ori = "int main () { x();y();z();}";
		String tar = "int main () { y();z();x();}";
		Comparar c = new FuncByFuncComparar(tar, ori);
		assertEquals(c.compare(new Formatter(), new Output()), "0.75");
	}

	@Test
	public void testCompare_3() {
		String ori = "int ha () { }";
		String tar = "int main () { y();z();}";
		Comparar c = new FuncByFuncComparar(tar, ori);
		assertEquals(c.compare(new Formatter(), new Output()),
				"0.3333333333333333");
	}

	@Test
	public void testCompare_4() {

		String ori = "int main () { y();z();}";
		String tar = "int ha () { }";
		Comparar c = new FuncByFuncComparar(tar, ori);
		assertEquals(c.compare(new Formatter(), new Output()), "1.0");
	}

	@Test
	public void testCompare_5() {
		String ori = "int main () { y();z(); }";
		String tar = "int main () { }";
		Comparar c = new FuncByFuncComparar(tar, ori);
		assertEquals(c.compare(new Formatter(), new Output()), "1.0");
	}

	@Test
	public void testCompare_6() {
		String ori = "int main () { y();z(); }";
		String tar = "int main () { a();b();c();}";
		Comparar c = new FuncByFuncComparar(tar, ori);
		assertEquals(c.compare(new Formatter(), new Output()), "0.75");
	}
}
