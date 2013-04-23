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
    public void testCompare1() {
        String ori = "int main () { x();}";
        String tar = "int main () { y();}";
        Comparar c = new FuncByFuncComparar(tar, ori);
        assertEquals(c.compare(new Formatter(), new Output()), "0.5");
    }

    @Test
    public void testCompare2() {
        String ori = "int main () { x();y();z();}";
        String tar = "int main () { y();z();x();}";
        Comparar c = new FuncByFuncComparar(tar, ori);
        assertEquals(c.compare(new Formatter(), new Output()), "0.75");
    }

    @Test
    public void testCompare3() {
        String ori = "int ha () { }";
        String tar = "int main () { y();z();}";
        Comparar c = new FuncByFuncComparar(tar, ori);
        assertEquals(c.compare(new Formatter(), new Output()),
                "0.0");
    }

    @Test
    public void testCompare4() {

        String ori = "int main () { y();z();}";
        String tar = "int ha () { }";
        Comparar c = new FuncByFuncComparar(tar, ori);
        assertEquals(c.compare(new Formatter(), new Output()), "0.0");
    }

    @Test
    public void testCompare5() {
        String ori = "int main () { y();z(); }";
        String tar = "int main () { }";
        Comparar c = new FuncByFuncComparar(tar, ori);
        assertEquals(c.compare(new Formatter(), new Output()), "1.0");
    }

    @Test
    public void testCompare6() {
        String ori = "int a(){a1();}int b(){b1();}int c(){c1();}int d(){d1();}";
        String tar = "int a(){a1();}int b(){b1();}";
        Comparar c = new FuncByFuncComparar(tar, ori);
        assertEquals(c.compare(new Formatter(), new Output()), "1.0");
    }

    @Test
    public void testCompare7() {
        String ori = "int a(){a1();}int b(){b1();}";
        String tar = "int a(){a1();}int b(){b1();}int c(){c1();}int d(){d1();}";
        Comparar c = new FuncByFuncComparar(tar, ori);
        assertEquals(c.compare(new Formatter(), new Output()), "0.5");
    }
}
