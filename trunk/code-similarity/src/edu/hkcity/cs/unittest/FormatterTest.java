package edu.hkcity.cs.unittest;


import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.hkcity.cs.Formatter;

public class FormatterTest {
	private Formatter fm;
	@Before
	public void setUp() throws Exception {
		fm = new Formatter();
	}

	@After
	public void tearDown() throws Exception {
		fm = null;
	}

	@Test 
	public void reference_test() {
		String str = "Normal Text";
		assertEquals("Normal Text", fm.format(str) );
	}
	
	@Test
	public void testDeleteIndent_1() {
		String str = " Hello world!";
		assertEquals("Hello world!", fm.format(str) );
	}
	
	@Test
	public void testDeleteIndent_2() {
		String str = "\tHello world!";
		assertEquals("Hello world!", fm.format(str) );
	}
	
	@Test
	public void testDeleteIndent_3() {
		String str = "         Hello world!";
		assertEquals("Hello world!", fm.format(str) );
	}
	
	@Test
	public void testDeleteIndent_4() {
		String str = "\t\t\t\t\t\tHello world!";
		assertEquals("Hello world!", fm.format(str) );
	}
	
	@Test
	public void testDeleteIndent_5() {
		String str = "\t   \t \t\t    Hello world!";
		assertEquals("Hello world!", fm.format(str) );
	}
	
	@Test 
	public void testSimplyMultispace_1() {
		String str = "Hello        world!            ";
		assertEquals("Hello world! ", fm.format(str) );
	}
	
	@Test
	public void testSimplyMultispace_2() {
		String str = "Hello\t     \t world! \t";
		assertEquals("Hello world! ", fm.format(str) );
	}
	
	@Test
	public void testSimplyMultispace_3() {
		String str = "O\t\t \t   \t     \t\t\t\t    \t \t \t    \tK";
		assertEquals("O K", fm.format(str) );
	}
	
	@Test
	public void testDeleteComment_1() {
		String str = "System.out.println(\"Hi!\\n\")// Say Hi!\nreturn 0;/*\n*Bye!\n*/";
		assertEquals("System.out.println(\"Hi!\\n\")\nreturn 0;", fm.format(str) );
	}
	
	@Test
	public void testDeleteComment_2() {
		String str = "System.out.println(\"Hi!\\n\")/*\n*Say Hi!\n*/\nreturn 0;/*\n*Bye!\n*/";
		assertEquals("System.out.println(\"Hi!\\n\")\nreturn 0;", fm.format(str) );
	}
	
	@Test
	public void testDeleteComment_3() {
		String str = "System.out.println(\"Hi!\\n\")// Say Hi!\nreturn 0;//Bye!";
		assertEquals("System.out.println(\"Hi!\\n\")\nreturn 0;", fm.format(str) );
	}
	
	@Test
	public void testDeleteComment_4() {
		String str = "System.out.println(\"Hi!\\n\")/*\n*Say Hi!\n*/\nreturn 0;//Bye!";
		assertEquals("System.out.println(\"Hi!\\n\")\nreturn 0;", fm.format(str) );
	}
	
	@Test 
	public void testDeleteBlankLine_1() {
		String str = "\n\n\r\nHello worid!\r\n\r\n\n\n";
		assertEquals("Hello worid!\n", fm.format(str) );
	}
	
	@Test 
	public void testDeleteBlankLine_2() {
		String str = "\n\r\n";
		assertEquals("", fm.format(str) );
	}

	@Test 
	public void testDeleteBlankLine_3() {
		String str = "\r\n\r\nO\r\n\r\n\r\n\rK\n\r\n!\r\n";
		assertEquals("O\nK\n!\n", fm.format(str) );
	}
	
	@Test 
	public void testVariableDeclaration_1() {
		String str = "int main(){\nint a;\n}";
		assertEquals("int main(){\n\n}", fm.formatVariableDeclaration(str) );
	}
	
	@Test 
	public void testVariableDeclaration_2() {
		String str = "int main(){\nint a=1;\n}";
		assertEquals("int main(){\na=1;\n}", fm.formatVariableDeclaration(str) );
	}
	
	@Test 
	public void testVariableDeclaration_3() {
		String str = "int main(){\nint __a,a=1,b,c=3,d=2/2,e=time(NULL);\n}";
		assertEquals("int main(){\na=1,c=3,d=2/2,e=time(NULL);\n}", fm.formatVariableDeclaration(str) );
	}
	
	@Test 
	public void testVariableDeclaration_4() {
		String str = "int main(){\nint a=test(1,2.3,\"test\"),c;\n}";
		assertEquals("int main(){\na=test(1,2.3,\"test\");\n}", fm.formatVariableDeclaration(str) );
	}
	
	@Test 
	public void testVariableDeclaration_5() {
		String str = "int main(){\nint** a=new int[5][5];\nint **a=new int[5][5];\nint a[5][5]={};\n}";
		assertEquals("int main(){\na=new int[5][5];\n**a=new int[5][5];\na[5][5]={};\n}", fm.formatVariableDeclaration(str) );
	}
	
	@Test 
	public void testVariableDeclaration_6() {
		String str = "int main(){\nint a=test(1==2,2!=3,b(c++),\"test(1,2,\\\"test\\\")\"),b=(1+2-c());\nMyClass obj1,object=new otherClass(__a()),obj2;\n}";
		assertEquals("int main(){\na=test(1==2,2!=3,b(c++),\"test(1,2,\\\"test\\\")\"),b=(1+2-c());\nobject=new otherClass(__a());\n}", fm.formatVariableDeclaration(str) );
	}
}
