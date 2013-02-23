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
		return formattedString;
	}
	private String simplyMultispace(){
		return formattedString.replaceAll("[ \t]+"," ");
	}
	private String deleteComment(){
		return formattedString.replaceAll("(/\\*([^*]|[\\r\\n]|(\\*+([^*/]|[\\r\\n])))*\\*+/)|(//.*)", "");
	}
	private String deleteBlankLine(){
		return formattedString.replaceAll("^[\r\n]+", "").replaceAll("[\r\n]+", "\n");
	}
	public String getFormattedData(){
		return formattedString;
	}
}
