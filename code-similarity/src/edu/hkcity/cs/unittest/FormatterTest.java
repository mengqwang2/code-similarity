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
    //formatter test (Ref text, no formatting can be applied)
    public void reference_test() {
        String str = "Normal Text";
        assertEquals("Normal Text", fm.format(str));
    }

    @Test
  //formatter Delete Indent test (space)
    public void testDeleteIndent1() {
        String str = " Hello world!";
        assertEquals("Hello world!", fm.format(str));
    }

    @Test
    //formatter Delete Indent test (tab)
    public void testDeleteIndent2() {
        String str = "\tHello world!";
        assertEquals("Hello world!", fm.format(str));
    }

    @Test
  //formatter Delete Indent test (multi space )
    public void testDeleteIndent3() {
        String str = "         Hello world!";
        assertEquals("Hello world!", fm.format(str));
    }

    @Test
    //formatter Delete Indent test (multi tab)
    public void testDeleteIndent4() {
        String str = "\t\t\t\t\t\tHello world!";
        assertEquals("Hello world!", fm.format(str));
    }

    @Test
    //formatter Delete Indent test (multi tab and space mixed)
    public void testDeleteIndent5() {
        String str = "\t   \t \t\t    Hello world!";
        assertEquals("Hello world!", fm.format(str));
    }

    @Test
  //formatter Simply Multispace test (multispace)
    public void testSimplyMultispace1() {
        String str = "Hello        world!            ";
        assertEquals("Hello world! ", fm.format(str));
    }

    @Test
    
  //formatter Simply Multispace test (multispace + tab mixed)
    public void testSimplyMultispace2() {
        String str = "Hello\t     \t world! \t";
        assertEquals("Hello world! ", fm.format(str));
    }

    @Test
    //formatter Simply Multispace test (multispace + tab mixed 2)
    public void testSimplyMultispace3() {
        String str = "O\t\t \t   \t     \t\t\t\t    \t \t \t    \tK";
        assertEquals("O K", fm.format(str));
    }

    @Test
  //formatter Delete Comment test (singleline + multiline comment)
    public void testDeleteComment1() {
        String str = "System.out.println(\"Hi!\\n\")// Say Hi!\nreturn 0;/*\n*Bye!\n*/";
        assertEquals("System.out.println(\"Hi!\\n\")\nreturn 0;",
                fm.format(str));
    }

    @Test
  //formatter Delete Comment test (multi multiline comment)
    public void testDeleteComment2() {
        String str = "System.out.println(\"Hi!\\n\")/*\n*Say Hi!\n*/\nreturn 0;/*\n*Bye!\n*/";
        assertEquals("System.out.println(\"Hi!\\n\")\nreturn 0;",
                fm.format(str));
    }

    @Test
    //formatter Delete Comment test (multi singleline comment)
    public void testDeleteComment3() {
        String str = "System.out.println(\"Hi!\\n\")// Say Hi!\nreturn 0;//Bye!";
        assertEquals("System.out.println(\"Hi!\\n\")\nreturn 0;",
                fm.format(str));
    }

    @Test
    //formatter Delete Comment test (singleline + multiline comment 2)
    public void testDeleteComment4() {
        String str = "System.out.println(\"Hi!\\n\")/*\n*Say Hi!\n*/\nreturn 0;//Bye!";
        assertEquals("System.out.println(\"Hi!\\n\")\nreturn 0;",
                fm.format(str));
    }

    @Test
    //formatter Delete BlankLine test (\n + \r\n mixed)
    public void testDeleteBlankLine1() {
        String str = "\n\n\r\nHello worid!\r\n\r\n\n\n";
        assertEquals("Hello worid!\n", fm.format(str));
    }

    @Test
    //formatter Delete BlankLine test (\n + \r\n only)
    public void testDeleteBlankLine2() {
        String str = "\n\r\n";
        assertEquals("", fm.format(str));
    }

    @Test
    //formatter Delete BlankLine test (\n + \r\n  mixed 2)
    public void testDeleteBlankLine3() {
        String str = "\r\n\r\nO\r\n\r\n\r\n\rK\n\r\n!\r\n";
        assertEquals("O\nK\n!\n", fm.format(str));
    }

    @Test
    //formatter simply Variable Declaration test (declaration without init)
    public void testVariableDeclaration1() {
        String str = "int main(){\nint a;\n}";
        assertEquals("int main(){\n\n}", fm.formatVariableDeclaration(str));
    }

    @Test
    //formatter simply Variable Declaration test (declaration with init)
    public void testVariableDeclaration2() {
        String str = "int main(){\nint a=1;\n}";
        assertEquals("int main(){\na=1;\n}", fm.formatVariableDeclaration(str));
    }

    @Test
    //formatter simply Variable Declaration test (declaration with partially init)
    public void testVariableDeclaration3() {
        String str = "int main(){\nint __a,a=1,b,c = 3,d=2/2,e=time(NULL);\n}";
        assertEquals("int main(){\na=1,c = 3,d=2/2,e=time(NULL);\n}",
                fm.formatVariableDeclaration(str));
    }

    @Test
  //formatter simply Variable Declaration test (declaration with partially init 2)
    public void testVariableDeclaration4() {
        String str = "int main(){\nint a=test(1,2.3,\"test\"),c;\n}";
        assertEquals("int main(){\na=test(1,2.3,\"test\");\n}",
                fm.formatVariableDeclaration(str));
    }

    @Test
  //formatter simply Variable Declaration test (pointer and array declaration with partially init)
    public void testVariableDeclaration5() {
        String str = "int main(){\nint** a=new int[5][5];\nint **a=new int[5][5];\nint a[5][5]={};\n}";
        assertEquals(
                "int main(){\na=new int[5][5];\n**a=new int[5][5];\na[5][5]={};\n}",
                fm.formatVariableDeclaration(str));
    }

    @Test
    //formatter simply Variable Declaration test (declaration with partially logical expression init)
    public void testVariableDeclaration6() {
        String str = "int main(){\nint a=test(1==2,2!=3,b(c++),\"test(1,2,\\\"test\\\")\"),b=(1+2-c());\nMyClass obj1,object=new otherClass(__a()),obj2;\n}";
        assertEquals(
                "int main(){\na=test(1==2,2!=3,b(c++),\"test(1,2,\\\"test\\\")\"),b=(1+2-c());\nobject=new otherClass(__a());\n}",
                fm.formatVariableDeclaration(str));
    }
}
