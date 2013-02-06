package edu.hkcity.cs;

import java.io.*;
import java.lang.String;

public class Formatter {
	protected String formattedString;
	public Formatter(){}
	public Formatter(InputStream data){
	}
	public InputStream getFormattedData(){
		return new ByteArrayInputStream(formattedString.getBytes());
	}
	private String deleteComment(String str){
		String res;
		res=str.replaceAll("/\\*(.|[\\r\\n])*?\\*/", "");
		res=res.replaceAll("//.*$","\n");
		return res;
		
	}
	
}

