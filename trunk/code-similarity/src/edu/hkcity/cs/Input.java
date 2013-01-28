package edu.hkcity.cs;

import java.io.*;

public class Input {
	private String filename;
	private InputStream stream;
	public Input(){
		
	}
	
	public Input(String filename){
		try {
			stream=new FileInputStream(filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getFilename(){
		return filename;
	}
	
	public InputStream getStream(){
		return stream;
	}
}
