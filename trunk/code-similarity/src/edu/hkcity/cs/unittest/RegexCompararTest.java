package edu.hkcity.cs.unittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.hkcity.cs.Formatter;
import edu.hkcity.cs.Output;
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
        String code1 = "The quick brown fox jumps over the lazy dog.";
        String code2 = "You can see actually these two are not code so they have quite high similarity.";
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
        String code1 = "E=m*(c^2)";
        String code2 = "P=PI*(r^2)";
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
        String code1 = "E=m*(c^2) is the most famous formula?";
        String code2 = "Omg=omg*(omg^2) are you kidding me that they are the same?";
        RegexStub comp = new RegexStub();
        String result = Double.toString(comp.testCalRS(code1, code2));
        assertEquals(result, "1.0");
    }

    @Test
    public void testCompare1() {
        String code1 = "The      quick brown fox jumps over the lazy dog.";
        String code2 = "You can see actually these two      are not code so they are treated the same.";
        RegexComparar comp = new RegexComparar(code1, code2);
        String result = comp.compare(new Formatter(), new Output());
        assertEquals(result, "1.0");
    }

    @Test
    public void testCompare2() {
        String code1 = "E\n!=\tm*(c^2)\t\t\t";
        String code2 = "P~=PI*(r^2)\n\n\n";
        RegexComparar comp = new RegexComparar(code1, code2);
        String result = comp.compare(new Formatter(), new Output());
        assertEquals(result, "0.875");
    }

    @Test
    public void testCompare3() {
        String code1 = "E=m*(c^2)\n is the most famous formula and frankly I am kidding you";
        String code2 = "Omg=omg*(omg^2)            are you kidding me that they are the same yes sir";
        RegexComparar comp = new RegexComparar(code1, code2);
        String result = comp.compare(new Formatter(), new Output());
        assertEquals(result, "1.0");
    }
}
