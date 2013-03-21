package edu.hkcity.cs.unittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.hkcity.cs.RegexComparar;

public class RegexCompararTest {
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testCalRegedSim1() {
		class RegexStub extends RegexComparar {
			public double testCalRS(String tar, String ori) {
				return calRegedSim(tar, ori);
			}
		}
		String code1 = "int main(){printf(\"hello world!\");return 1;}";
		String code2 = "int main(){printf(\"hello world!\");return 1;}";
		RegexStub comp = new RegexStub();
		String result = Double.toString(comp.testCalRS(code1, code2));
		assertEquals(result, "1.0");
	}
		
	@Test
	public void testCalRegedSim2() {
		class RegexStub extends RegexComparar {
			public double testCalRS(String tar, String ori) {
				return calRegedSim(tar, ori);
			}
		}
		String code1 = "E=mc^2";
		String code2 = "P=pir^2";
		RegexStub comp = new RegexStub();
		String result = Double.toString(comp.testCalRS(code1, code2));
		assertEquals(result, "1.0");
	}
	
	@Test
	public void testCalRegedSim3() {
		class RegexStub extends RegexComparar {
			public double testCalRS(String tar, String ori) {
				return calRegedSim(tar, ori);
			}
		}
		String code1 = "E=mc^2 is the most famous formula";
		String code2 = "Omg=omg^100 are you kidding me?";
		RegexStub comp = new RegexStub();
		String result = Double.toString(comp.testCalRS(code1, code2));
		assertEquals(result, "1.0");
	}

	
	/*
	@Test
	public void testCompare1() {
	}
	@Test
	public void testCompare2() {
	}
	@Test
	public void testCompare3() {
	}
	*/
}
