package edu.hkcity.cs.unittest;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringBufferInputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.hkcity.cs.Input;
import edu.hkcity.cs.Output;

public class InputTest {
    private File oriF;
    private File tarF;

    @Before
    public void setUp() throws Exception {
        oriF = new File("ori.txt");
        tarF = new File("tar.txt");
    }

    @After
    public void tearDown() throws Exception {
        oriF.delete();
        oriF = null;
        tarF.delete();
        tarF = null;
    }

    @Test
    public void testInput1() throws IOException{
        FileWriter oriFw = new FileWriter(oriF, false);
        FileWriter tarFw = new FileWriter(tarF, false);

        String inStr = "Hello world!";
        oriFw.write(inStr);
        oriFw.close();
        tarFw.write(inStr);
        tarFw.close();

        Input input = new Input("ori.txt", "tar.txt");
        input.getInput();
        assertEquals(inStr, input.getOriginalFile());
        assertEquals(inStr, input.getTargetFile());
    }

    @Test
    public void testInput2() throws IOException {
        FileWriter oriFw = new FileWriter(oriF, false);
        FileWriter tarFw = new FileWriter(tarF, false);

        String inStr = "#include <stdio.h>;" + "\n\n" + "int main(){\n"
                + "    printf(\"hello world!\\n\");\n" + "    return 0;\n"
                + "}\n";
        oriFw.write(inStr);
        oriFw.close();
        tarFw.write(inStr);
        tarFw.close();

        Input input = new Input("ori.txt", "tar.txt");
        input.getInput();
        assertEquals(inStr, input.getOriginalFile());
        assertEquals(inStr, input.getTargetFile());
    }

    @Test
    public void testInputNA1() throws IOException {
        FileWriter oriFw = new FileWriter(oriF, false);
        FileWriter tarFw = new FileWriter(tarF, false);

        String inStr = "Hello world!";
        oriFw.write(inStr);
        oriFw.close();
        tarFw.write(inStr);
        tarFw.close();

        InputStream oriin = System.in;
        System.setIn(new StringBufferInputStream("ori.txt\ntar.txt\n"));
        Input input = new Input();
        input.getInput();
        assertEquals(inStr, input.getOriginalFile());
        assertEquals(inStr, input.getTargetFile());
        System.setIn(oriin);
    }

    @Test
    public void testInputNA2() throws IOException {
        FileWriter oriFw = new FileWriter(oriF, false);
        FileWriter tarFw = new FileWriter(tarF, false);

        String inStr = "#include <stdio.h>;" + "\n\n" + "int main(){\n"
                + "    printf(\"hello world!\\n\");\n" + "    return 0;\n"
                + "}\n";
        oriFw.write(inStr);
        oriFw.close();
        tarFw.write(inStr);
        tarFw.close();

        InputStream oriin = System.in;
        System.setIn(new StringBufferInputStream("ori.txt\ntar.txt\n"));
        Input input = new Input();
        input.getInput();
        assertEquals(inStr, input.getOriginalFile());
        assertEquals(inStr, input.getTargetFile());
        System.setIn(oriin);
    }

    @Test
    public void testInputWrongFileName() throws IOException {
        FileWriter oriFw = new FileWriter(oriF, false);
        FileWriter tarFw = new FileWriter(tarF, false);

        String inStr = "#include <stdio.h>;" + "\n\n" + "int main(){\n"
                + "    printf(\"hello world!\\n\");\n" + "    return 0;\n"
                + "}\n";
        oriFw.write(inStr);
        oriFw.close();
        tarFw.write(inStr);
        tarFw.close();

        InputStream oriin = System.in;
        System.setIn(new StringBufferInputStream("noSuchFile.txt\nori.txt\ntar.txt\n"));
        Input input = new Input();
        input.getInput();
        assertEquals(inStr, input.getOriginalFile());
        assertEquals(inStr, input.getTargetFile());
        System.setIn(oriin);
    }
}
