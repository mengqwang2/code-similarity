package edu.hkcity.cs;

import java.io.*;

public class Input {
	private String filename;
	public Input(){
		
	}
	public Input(String filename){
		this.filename=filename;
		try {
			new FileInputStream(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public String getFilename(){
		return filename;
	}
	public InputStream getStream(){
		try {
			return new FileInputStream(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
