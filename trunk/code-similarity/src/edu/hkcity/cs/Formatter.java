package edu.hkcity.cs;

import java.io.*;

public abstract class Formatter {
	protected String formattedString;
	public Formatter(InputStream data){
	}
	public InputStream getFormattedData(){
		return new ByteArrayInputStream(formattedString.getBytes());
	}
}
