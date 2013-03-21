package edu.hkcity.cs;

import java.util.ArrayList;
import java.util.StringTokenizer;
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
		ArrayList<String> funcList=Utility.splitFunction(str);
		for(int n=0;n<funcList.size();n++){
			Pattern pattern = Pattern.compile("[a-zA-Z_][\\w_:<>]*[*]* [*]*[A-Za-z_][\\w_]*.*?;");
	        Matcher matcher = pattern.matcher(funcList.get(n));
	        int lastEnd = 0;
	        StringBuffer resStr = new StringBuffer();
	        while(matcher.find()){
	        	int start=matcher.start();
	        	int end=matcher.end();
	        	resStr.append(funcList.get(n).substring(lastEnd, start));
	        	String mstr = funcList.get(n).substring(start, end);
	        	StringTokenizer st = new StringTokenizer(mstr, ",");
	        	StringBuffer tmpStr = new StringBuffer();
	        	while(st.hasMoreTokens()) {
	        		String s = st.nextToken();
	        		String sdstr = tmpStr.append(s).toString();
	        		boolean hasPBuck = hasParallelBucket(sdstr);
	        		boolean hasAssSym = hasAssignSymbol(sdstr);
	        		if ( hasPBuck && hasAssSym ) {
	        			resStr.append(getRefinedStatement(sdstr));
	        			tmpStr = new StringBuffer();
	        		}else if( !hasAssSym )
	        			tmpStr = new StringBuffer();
	        		else if (st.hasMoreTokens())
	        			tmpStr.append(',');
	        	}
	        	lastEnd = end;
	        }
	        if (resStr.charAt(resStr.length()-1) == ',') resStr.replace(resStr.length()-1, resStr.length(), ";");
	        resStr.append(funcList.get(n).substring(lastEnd));
	        funcList.set(n, resStr.toString());
		}
		StringBuffer res = new StringBuffer();
		for(int n=0;n<funcList.size();n++){
			res.append(funcList.get(n));
		}
		return res.toString();
	}
	
	private boolean hasParallelBucket(String str) {
		int op = -1, cl = -1;
		int i = 0;
		while(i!= -1) {
			op++;
			i = str.indexOf('(', ++i);
		}
		i = 0;
		while(i!= -1) {
			cl++;
			i = str.indexOf(')', ++i);
		}
		return op == cl;
	}
	private boolean hasAssignSymbol(String str) {
		int i = str.indexOf('=');
		if(i == -1 || i==0 || i == str.length()-1) return false;
		if(i>0 && str.charAt(i-1) == '!')return false;
		if(i<str.length()-1 && str.charAt(i+1) == '=')	return false;
		return true;
	}
	private String getRefinedStatement(String str) {
		int i = str.indexOf('=');
		int start = i - 1;
		if (str.charAt(start) == ' ') {
			while(start >= 0 && str.charAt(start) == ' ')
				start--;
		} 
		while(start >= 0 && str.charAt(start) != ' ')
			start--;
		String resStr = str.substring(start+1);
		if (!resStr.endsWith(";")) resStr+=",";
		return resStr;
	}
}
