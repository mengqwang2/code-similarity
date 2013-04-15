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
	private File ori_f;
	private File tar_f;

	@Before
	public void setUp() throws Exception {
		ori_f = new File("ori.txt");
		tar_f = new File("tar.txt");
	}

	@After
	public void tearDown() throws Exception {
		ori_f.delete();
		ori_f = null;
		tar_f.delete();
		tar_f = null;
	}

	@Test
	public void testInput_1() throws IOException{
		FileWriter ori_fw = new FileWriter(ori_f, false);
		FileWriter tar_fw = new FileWriter(tar_f, false);

		String inStr = "Hello world!";
		ori_fw.write(inStr);
		ori_fw.close();
		tar_fw.write(inStr);
		tar_fw.close();

		Input input = new Input("ori.txt", "tar.txt");
		input.getInput();
		assertEquals(inStr, input.getOriginalFile());
		assertEquals(inStr, input.getTargetFile());
	}

	@Test
	public void testInput_2() throws IOException {
		FileWriter ori_fw = new FileWriter(ori_f, false);
		FileWriter tar_fw = new FileWriter(tar_f, false);

		String inStr = "#include <stdio.h>;" + "\n\n" + "int main(){\n"
				+ "    printf(\"hello world!\\n\");\n" + "    return 0;\n"
				+ "}\n";
		ori_fw.write(inStr);
		ori_fw.close();
		tar_fw.write(inStr);
		tar_fw.close();

		Input input = new Input("ori.txt", "tar.txt");
		input.getInput();
		assertEquals(inStr, input.getOriginalFile());
		assertEquals(inStr, input.getTargetFile());
	}
	
	@Test
	public void testInputNA_1() throws IOException {
		FileWriter ori_fw = new FileWriter(ori_f, false);
		FileWriter tar_fw = new FileWriter(tar_f, false);

		String inStr = "Hello world!";
		ori_fw.write(inStr);
		ori_fw.close();
		tar_fw.write(inStr);
		tar_fw.close();

		InputStream oriin = System.in;
		System.setIn(new StringBufferInputStream("ori.txt\ntar.txt\n"));
		Input input = new Input();
		input.getInput();
		assertEquals(inStr, input.getOriginalFile());
		assertEquals(inStr, input.getTargetFile());
		System.setIn(oriin);
	}

	@Test
	public void testInputNA_2() throws IOException {
		FileWriter ori_fw = new FileWriter(ori_f, false);
		FileWriter tar_fw = new FileWriter(tar_f, false);

		String inStr = "#include <stdio.h>;" + "\n\n" + "int main(){\n"
				+ "    printf(\"hello world!\\n\");\n" + "    return 0;\n"
				+ "}\n";
		ori_fw.write(inStr);
		ori_fw.close();
		tar_fw.write(inStr);
		tar_fw.close();

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
		FileWriter ori_fw = new FileWriter(ori_f, false);
		FileWriter tar_fw = new FileWriter(tar_f, false);

		String inStr = "#include <stdio.h>;" + "\n\n" + "int main(){\n"
				+ "    printf(\"hello world!\\n\");\n" + "    return 0;\n"
				+ "}\n";
		ori_fw.write(inStr);
		ori_fw.close();
		tar_fw.write(inStr);
		tar_fw.close();

		InputStream oriin = System.in;
		System.setIn(new StringBufferInputStream("noSuchFile.txt\nori.txt\ntar.txt\n"));
		Input input = new Input();
		input.getInput();
		assertEquals(inStr, input.getOriginalFile());
		assertEquals(inStr, input.getTargetFile());
		System.setIn(oriin);
	}
}
