package edu.hkcity.cs;

import java.io.*;

public abstract class Formatter {
	protected String formattedString;
	public Formatter(String data){
		formattedString=data;
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
		return formattedString.replaceAll("\n\n", "\n");
	}
	public String getFormattedData(){
		return formattedString;
	}
}
