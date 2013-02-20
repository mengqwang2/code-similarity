package edu.hkcity.cs;

import java.io.*;

public class Formatter {
	private String formattedString;
	public Formatter(){
	}
	public String format(){
		deleteIndent();
		simplyMultispace();
		deleteComment();
		deleteBlankLine();
		return formattedString;
	}
	private String deleteIndent(){
		return formattedString;//TO DO
	}
	private String simplyMultispace(){
		return formattedString;//TO DO
	}
	private String deleteComment(){
		return formattedString;//TO DO
	}
	private String deleteBlankLine(){
		return formattedString.replaceAll("[\r\n]+", "\n");
	}
	public String getFormattedData(){
		return formattedString;
	}
}
