package edu.hkcity.cs;

public class Formatter {
	private String formattedString;
	public Formatter(){
	}
	public String format(String str){
		formattedString = str;
		formattedString = deleteIndent();
		formattedString = simplyMultispace();
		formattedString = deleteComment();
		formattedString = deleteBlankLine();
		return formattedString;
	}
	private String deleteIndent(){
		return formattedString;//TO DO
	}
	private String simplyMultispace(){
		return formattedString.replaceAll("\b+"," ");
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
