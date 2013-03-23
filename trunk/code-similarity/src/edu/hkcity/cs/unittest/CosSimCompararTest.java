package edu.hkcity.cs.unittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.hkcity.cs.Comparar;
import edu.hkcity.cs.CosSimComparar;
import edu.hkcity.cs.Formatter;
import edu.hkcity.cs.PercentageOutput;

public class CosSimCompararTest {
	private CosSimComparar cos = new CosSimComparar();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCompare_1() {
		String ori = "int main () { x();}";
		String tar = "int main () { y();}";
		Comparar c = new CosSimComparar(tar, ori);
		assertEquals(c.compare(new Formatter(), new PercentageOutput()),
				"0.7999999999999998");
	}

	@Test
	public void testCompare_2() {
		String ori = "int main () { x();y();z();}";
		String tar = "int main () { y();z();x();}";
		Comparar c = new CosSimComparar(tar, ori);
		assertEquals(c.compare(new Formatter(), new PercentageOutput()),
				"0.7999999999999998");
	}

	@Test
	public void testCompare_3() {
		String ori = "int ha () { }";
		String tar = "int main () { y();z();}";
		Comparar c = new CosSimComparar(tar, ori);
		assertEquals(c.compare(new Formatter(), new PercentageOutput()),
				"0.5999999999999999");
	}

	@Test
	public void testCompare_4() {

		String ori = "int main () { y();z();}";
		String tar = "int ha () { }";
		Comparar c = new CosSimComparar(tar, ori);
		assertEquals(c.compare(new Formatter(), new PercentageOutput()),
				"0.5999999999999999");
	}

	@Test
	public void testCompare_5() {
		String ori = "int main () { y();z(); }";
		String tar = "int main () { }";
		Comparar c = new CosSimComparar(tar, ori);
		assertEquals(c.compare(new Formatter(), new PercentageOutput()),
				"0.9128709291752769");
	}

	@Test
	public void testCompare_6() {
		String ori = "int main () { y();z(); }";
		String tar = "int main () { a();b();c();}";
		Comparar c = new CosSimComparar(tar, ori);
		assertEquals(c.compare(new Formatter(), new PercentageOutput()),
				"0.7302967433402214");
	}

	@Test
	public void testPubCosSimliar1() {
		String str1 = new String(
				"int main() { int a=1,b=2; int c; c=a+b; if(c>2*b) cout<<c; else cout<<0; } ");
		String str2 = new String(
				"int main() { int a=1,b=2; int c; c=a+b; if(c>2*b) cout<<c; else cout<<0; } ");
		double res = cos.PubCosSimliar(str1, str2);
		String StrRes = Double.toString(res);
		assertEquals(StrRes, new String("0.9999999999999998"));

	}

	@Test
	// shift code
	public void testPubCosSimliar2() {
		String str1 = new String(
				"int main() { int c; int a=1,b=2; c=a+b; if(c>2*b) cout<<c; else cout<<0; } ");
		String str2 = new String(
				"int main() { int a=1,b=2; int c; c=a+b; if(c>2*b) cout<<c; else cout<<0; } ");
		double res = cos.PubCosSimliar(str1, str2);
		String StrRes = Double.toString(res);
		assertEquals(StrRes, new String("0.9999999999999998"));

	}

	@Test
	// first part remove
	public void testPubCosSimliar3() {
		String str1 = new String("c=a+b; if(c>2*b) cout<<c; else cout<<0; } ");
		String str2 = new String(
				"int main() { int c; int a=1,b=2; c=a+b; if(c>2*b) cout<<c; else cout<<0; } ");
		double res = cos.PubCosSimliar(str1, str2);
		String StrRes = Double.toString(res);
		assertEquals(StrRes, new String("0.5619514869490164"));

	}

	@Test
	// last part remove
	public void testPubCosSimliar4() {
		String str1 = new String("int main() { int c; int a=1,b=2; ");
		String str2 = new String(
				"int main() { int c; int a=1,b=2; c=a+b; if(c>2*b) cout<<c; else cout<<0; } ");
		double res = cos.PubCosSimliar(str1, str2);
		String StrRes = Double.toString(res);
		assertEquals(StrRes, new String("0.827170191868511"));
	}
}
