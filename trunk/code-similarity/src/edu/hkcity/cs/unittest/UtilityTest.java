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
    // Test string join function (5 strings, ' 'connecter)
    public void testJoin1() {
        String[] testArr = { "this", "is", "a", "test", "string" };
        String result = Utility.join(testArr, " ");
        assertEquals("this is a test string", result);
    }

    @Test
 // Test string join function (6 strings, ','connecter)
    public void testJoin2() {
        String[] testArr = { "there are one", "two", "three", "four", "five",
                "six people" };
        String result = Utility.join(testArr, ", ");
        assertEquals("there are one, two, three, four, five, six people", result);
    }

    @Test
    // Test isVar checker (valid variable 1)
    public void testIsVar1() {
        boolean result = Utility.isVar("_myVar");
        assertEquals(true, result);
    }

    @Test
    // Test isVar checker (valid variable 2)
    public void testIsVar2() {
        boolean result = Utility.isVar("var123");
        assertEquals(true, result);
    }

    @Test
    // Test isVar checker (invalid variable (? included))
    public void testIsVar3() {
        boolean result = Utility.isVar("areyoukiddingme?");
        assertEquals(false, result);
    }

    @Test
    // Test isVar checker (invalid variable (digit beginning))
    public void testIsVar4() {
        boolean result = Utility.isVar("456var");
        assertEquals(false, result);
    }

    @Test
    // Test ExtractVarNames (3 variables only)
    public void testExtractVarNames1() {
        String testStr = "int a=0, b=\"joke\"; int c = a+b;";
        String result = Utility.join(Utility.extractVarNames(testStr), "");
        assertEquals("abc", result);
    }

    @Test
    // Test ExtractVarNames (3 variables + some code)
    public void testExtractVarNames2() {
        String testStr = "int sum=0;length=10;for(int i=0;i<length;++i)sum+=i;";
        String result = Utility.join(Utility.extractVarNames(testStr), "");
        assertEquals("sumlengthi", result);
    }

    @Test
    // Test ExtractVarNames (some ADT variables +  variables + some code)
    public void testExtractVarNames3() {
        String testStr = "ArrayList<String> result = new ArrayList<String>();int length=arr.length;for(int i=0;i<length;++i) {String s = arr[i];if(!result.contains(s))result.add(s);}return result.toArray(new String[result.size()]);";
        String result = Utility.join(Utility.extractVarNames(testStr), "");
        assertEquals("ArrayListStringresultnewlengtharriscontainsaddtoArraysize", result);
    }

    @Test
    // Test ExtractVarNames (return statement with complex function call and logical expression)
    public void testExtractVarNames4() {
        String testStr = "return (java.util.regex.Pattern.matches(\"[a-zA-Z]\\w*\", token)&&    !java.util.regex.Pattern.matches(join(keywords, \"|\"), token));";
        String result = Utility.join(Utility.extractVarNames(testStr), "");
        assertEquals("javautilregexPatternmatchestokenjoinkeywords", result);
    }

    @Test
    // Test lcs algorithm function  (4:5 tokens, 2 matched)
    public void testLcs1() {
        String[] token1 = { "abc", "def", "acd", "ert" };
        String[] token2 = { "abc", "dgd", "def", "gdf", "sdfa" };
        String result = Double.toString(Utility.lcs(token1, token2));
        assertEquals("0.5", result);
    }

    @Test
    // Test lcs algorithm function  (4:5 tokens, 0 matched)
    public void testLcs2() {
        String[] token1 = { "abd", "def", "acd", "ert" };
        String[] token2 = { "abc", "dgd", "del", "gdf", "sdfa" };
        String result = Double.toString(Utility.lcs(token1, token2));
        assertEquals("0.0", result);
    }

    @Test
    // Test lcs algorithm function  (4:4 tokens, 4 matched)
    public void testLcs3() {
        String[] token1 = { "abd", "def", "acd", "ert" };
        String[] token2 = { "abd", "def", "acd", "ert" };
        String result = Double.toString(Utility.lcs(token1, token2));
        assertEquals("1.0", result);
    }

    @Test
    // Test replace function (partial match pattern)
    public void testReplace1() {
        String org = "foo(bar){for(i=0;i!=10;++i){i+=10;i=2;}}";
        String tar = "bar(foo){for(a=0;a!=10;++a){a+=10;b=a+3;c+=a;a=2;a=3;a=4;}}";
        String result = Utility.replace(org, tar);
        assertEquals("foo(bar){for(i=0;i!=10;++i){i+=10;b=i+3;c+=i;i=2;i=3;i=4;}}", result);
    }

    @Test
    // Test replace function (partial match pattern, reversed parameter)
    public void testReplace2() {
        String org = "bar(foo){for(a=0;a!=10;++a){a+=10;b=a+3;c+=a;a=2;a=3;a=4;}}";
        String tar = "foo(bar){for(i=0;i!=10;++i){i+=10;i=2;}}";
        String result = Utility.replace(org, tar);
        assertEquals("foo(bar){for(i=0;i!=10;++i){i+=10;b=i+3;c+=i;i=2;i=3;i=4;}}", result);
    }

    @Test
    // Test replace function (same pattern)
    public void testReplace3() {
        String org = "bar(foo){for(a=0;a!=10;++a){a+=10;b=a+3;c+=a;a=2;a=3;a=4;}}";
        String tar = "bar(foo){for(i=0;i!=10;++i){i+=10;b=i+3;c+=i;i=2;i=3;i=4;}}";
        String result = Utility.replace(org, tar);
        assertEquals("bar(foo){for(a=0;a!=10;++a){a+=10;b=a+3;c+=a;a=2;a=3;a=4;}}", result);
    }

    @Test
    // Test replace function (same pattern + additional statement)
    public void testReplace4() {
        String org = "int foo(int i=0, int j=1) {printf(\"Hello World\n\");return i+j;}";
        String tar = "int bar(int a=0, int b=1) {printf(\"Sorry World\n\");}";
        String result = Utility.replace(org, tar);
        assertEquals("int bar(int a=0, int b=1) {printf(\"Sorry World\");return a+b;}", result);
    }

    @Test
    // Test replace function (complex pattern)
    public void testReplace5() {
        String org = "void procs_create_msg(char* who, int pid, int ppid, time_t timer) {printf(\"[%s] %s (%d) created. Its parent is %d.\");who=null}";
        String tar = "void msg(char* w, int id, int pid, time_t time) {printf(\"[%s] %s (%d) created. Its parent is %d.\");}";
        String result = Utility.replace(org, tar);
        assertEquals("void msg(char* w, int id, int pid, time_t time) {printf(\"[%s] %s (%d) created. Its parent is %d.\");w=null}", result);
    }
    @Test
    // Test Split function (3 functions)
    public void testSplitFunction() {
        String program = "int main () { a(); }\nint a () { b(); }\nint b () { c(); }";
        ArrayList<String> list=Utility.splitFunction(program);
        for(int n=0;n<list.size();n++){
            System.out.println("LL--"+list.get(n));
        }
        assertEquals(3,list.size());
    }
}
