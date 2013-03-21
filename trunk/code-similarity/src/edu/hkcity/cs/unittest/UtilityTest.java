package edu.hkcity.cs.unittest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.hkcity.cs.Utility;

public class UtilityTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testJoin1() {
		String[] testArr = {"this", "is", "a", "test", "string"};
		String result = Utility.join(testArr, " ");
		assertEquals(result, "this is a test string");
	}
	
	@Test
	public void testJoin2() {
		String[] testArr = {"there are one", "two", "three", "four", "five", "six people"};
		String result = Utility.join(testArr, ", ");
		assertEquals(result, "there are one, two, three, four, five, six people");
	}
	
	@Test
	public void testIsVar1() {
		boolean result = Utility.isVar("abcd");
		assertEquals(result, true);
	}
	
	@Test
	public void testIsVar2() {
		boolean result = Utility.isVar("var123");
		assertEquals(result, true);
	}
	
	@Test
	public void testIsVar3() {
		boolean result = Utility.isVar(":var?");
		assertEquals(result, false);
	}
	
	@Test
	public void testIsVar4() {
		boolean result = Utility.isVar("456var");
		assertEquals(result, false);
	}
	
	@Test
	public void testExtractVarNames1() {
		String testStr = "int a=0, b=0; int c = a+b;";
		String result = Utility.join(Utility.extractVarNames(testStr),"");
		assertEquals(result, "abc");
	}
	
	@Test
	public void testExtractVarNames2() {
		String testStr = "int sum=0;length=10;for(int i=0;i<length;++i)sum+=i;";
		String result = Utility.join(Utility.extractVarNames(testStr),"");
		assertEquals(result, "sumlengthi");
	}
	
	@Test
	public void testExtractVarNames3() {
		String testStr = "ArrayList<String> result = new ArrayList<String>();int length=arr.length;for(int i=0;i<length;++i) {String s = arr[i];if(!result.contains(s))result.add(s);}return result.toArray(new String[result.size()]);";
		String result = Utility.join(Utility.extractVarNames(testStr),"");
		assertEquals(result, "ArrayListStringresultlengtharriscontainsaddtoArraysize");
	}
	
	@Test
	public void testExtractVarNames4() {
		String testStr = "return (java.util.regex.Pattern.matches(\"[a-zA-Z]\\w*\", token)&&	!java.util.regex.Pattern.matches(join(keywords, \"|\"), token));";
		String result = Utility.join(Utility.extractVarNames(testStr),"");
		assertEquals(result, "javautilregexPatternmatchesazAZwtokenjoinkeywords");
	}
	/*
	@Test
	public void testLcs() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTok() {
		fail("Not yet implemented");
	}

	@Test
	public void testCosSimliar() {
		fail("Not yet implemented");
	}
	*/
}
