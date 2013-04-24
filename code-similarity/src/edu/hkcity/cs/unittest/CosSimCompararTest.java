package edu.hkcity.cs.unittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.hkcity.cs.Comparar;
import edu.hkcity.cs.CosSimComparar;
import edu.hkcity.cs.Formatter;
import edu.hkcity.cs.Output;

public class CosSimCompararTest {
    private CosSimComparar cos = new CosSimComparar();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    // Test CosSimComparar compare function (2 different function call statement)
    public void testCompare1() {
        String ori = "int main () { x();}";
        String tar = "int main () { y();}";
        Comparar c = new CosSimComparar(tar, ori);
        assertEquals(c.compare(new Formatter(), new Output()),
                "0.7999999999999998");
    }

    @Test
    // Test CosSimComparar compare function (3 function call statement, different order)
    public void testCompare2() {
        String ori = "int main () { x();y();z();}";
        String tar = "int main () { y();z();x();}";
        Comparar c = new CosSimComparar(tar, ori);
        assertEquals(c.compare(new Formatter(), new Output()),
                "0.7999999999999998");
    }

    @Test
    // Test CosSimComparar compare function (different function name and statements)
    public void testCompare3() {
        String ori = "int ha () { }";
        String tar = "int main () { y();z();}";
        Comparar c = new CosSimComparar(tar, ori);
        assertEquals(c.compare(new Formatter(), new Output()),
                "0.5999999999999999");
    }

    @Test
    // Test CosSimComparar compare function (different function name and statements, parameter reversed )
    public void testCompare4() {
        String ori = "int main () { y();z();}";
        String tar = "int ha () { }";
        Comparar c = new CosSimComparar(tar, ori);
        assertEquals(c.compare(new Formatter(), new Output()),
                "0.5999999999999999");
    }

    @Test
    // Test CosSimComparar compare function (same function name ,different statements, parameter reversed )
    public void testCompare5() {
        String ori = "int main () { y();z(); }";
        String tar = "int main () { }";
        Comparar c = new CosSimComparar(tar, ori);
        assertEquals(c.compare(new Formatter(), new Output()),
                "0.9128709291752769");
    }

    @Test
    // Test CosSimComparar compare function (same function name, different statements)
    public void testCompare6() {
        String ori = "int main () { y();z(); }";
        String tar = "int main () { a();b();c();}";
        Comparar c = new CosSimComparar(tar, ori);
        assertEquals(c.compare(new Formatter(), new Output()),
                "0.7302967433402214");
    }

    @Test
 // Test PubCosSimliar function (identical string)
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
    // Test PubCosSimliar function (code shifted )
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
    // Test PubCosSimliar function (first part removed)
    public void testPubCosSimliar3() {
        String str1 = new String("c=a+b; if(c>2*b) cout<<c; else cout<<0; } ");
        String str2 = new String(
                "int main() { int c; int a=1,b=2; c=a+b; if(c>2*b) cout<<c; else cout<<0; } ");
        double res = cos.PubCosSimliar(str1, str2);
        String StrRes = Double.toString(res);
        assertEquals(StrRes, new String("0.5619514869490164"));

    }

    @Test
    // Test PubCosSimliar function (last part removed)
    public void testPubCosSimliar4() {
        String str1 = new String("int main() { int c; int a=1,b=2; ");
        String str2 = new String(
                "int main() { int c; int a=1,b=2; c=a+b; if(c>2*b) cout<<c; else cout<<0; } ");
        double res = cos.PubCosSimliar(str1, str2);
        String StrRes = Double.toString(res);
        assertEquals(StrRes, new String("0.827170191868511"));
    }
}
