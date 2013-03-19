package edu.hkcity.cs;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		return formattedString.replaceAll("^[ \\t]+", "");
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
	public String formatVariableDeclaration(String str){
		ArrayList<String> funcList=splitFunction(str);
		for(int n=0;n<funcList.size();n++){
			Pattern pattern = Pattern.compile("[A-Za-z_][\\w_]*[*]? [*]?[A-Za-z_][\\w_]*.*?;");
	        Matcher matcher = pattern.matcher(funcList.get(n));
	        while(matcher.find()){
	        	int start=matcher.start();
	        	int end=matcher.end();
	        }
		}
		return formattedString;
	}
	public ArrayList<String> splitFunction(String program){
		ArrayList<String> funcList=new ArrayList<String>();
		Pattern pattern = Pattern.compile("[\\w\\*]* [\\w]*\\((.*)\\)[ \\r\\n]*\\{",Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(program);
    	int start=0;
    	int nextStart=0;
        while(matcher.find()){
        	start=nextStart;
        	nextStart=matcher.start();
	        if(start!=0){
	        	funcList.add(program.substring(start,nextStart).replaceAll("[\\r\\n\\s]+$",""));
	        }
        }
    	funcList.add(program.substring(nextStart,program.length()).replaceAll("[\\r\\n\\s]+$",""));
		return funcList;
	}
}
