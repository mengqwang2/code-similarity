package edu.hkcity.cs.unittest;

import static org.junit.Assert.*;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.hkcity.cs.Input;

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
	public void testInput_1() {
		try {
			FileWriter ori_fw = new FileWriter(ori_f, false);
			FileWriter tar_fw = new FileWriter(tar_f, false);
			
			String inStr = "Hello world!";
			ori_fw.write(inStr);
			ori_fw.close();
			tar_fw.write(inStr);
			tar_fw.close();
			
			Input input = new Input("ori.txt", "tar.txt");
			assertEquals(inStr, input.getOriginalFile());
			assertEquals(inStr, input.getTargetFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInput_2() {
		try {
			FileWriter ori_fw = new FileWriter(ori_f, false);
			FileWriter tar_fw = new FileWriter(tar_f, false);
			
			String inStr = 
				"#include <stdio.h>;" +
				"\n\n" +
				"int main(){\n" +
				"    printf(\"hello world!\\n\");\n" +
				"    return 0;\n" +
				"}\n";
			ori_fw.write(inStr);
			ori_fw.close();
			tar_fw.write(inStr);
			tar_fw.close();
			
			Input input = new Input("ori.txt", "tar.txt");
			assertEquals(inStr, input.getOriginalFile());
			assertEquals(inStr, input.getTargetFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInput_3() {
		try {
			FileWriter ori_fw = new FileWriter(ori_f, false);
			FileWriter tar_fw = new FileWriter(tar_f, false);
			
			String inStr = "";
			ori_fw.write(inStr);
			ori_fw.close();
			tar_fw.write(inStr);
			tar_fw.close();
			
			Input input = new Input("ori.txt", "tar.txt");
			assertEquals(inStr, input.getOriginalFile());
			assertEquals(inStr, input.getTargetFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
