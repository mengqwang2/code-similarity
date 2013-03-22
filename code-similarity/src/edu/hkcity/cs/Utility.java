package edu.hkcity.cs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 *	The utilities
 */
public class Utility {
	// Predetermined keywords, operators and values, extensible
	protected static String[] keywords={
			// keywords
			"auto","double", "int", "struct", "break", "else", "long", "switch", "case", "enum", "register", 
			"typedef", "char", "extern", "return", "union", "const", "float", "short", "unsigned", "continue",
			"for", "signed", "void", "default", "goto", "sizeof", "volatile", "do", "if", "static", "while", "main",
			// values
			"true", "false", "null", "\'.*\'", "\".*\"",
			// punctuation and blank
			"\\p{Punct}", "\\s"
	};
	
	
	// Gavin - Join elements in an array to a string with a delimiter
	// Input: String[]
	// Output: String
	public static String join(String []arr, String delimiter) {
		String result="";
		int length=arr.length;
		for(int i=0;i<length-1;++i)
			result += arr[i]+delimiter;
		result += arr[length-1];
		return result;
	}
	
	
	// Gavin - Decide whether a token is predetermined keywords
	// Input: String
	// Output: boolean
	public static boolean isVar(String token) {
		return (java.util.regex.Pattern.matches("(_|[a-zA-Z])\\w*", token)
				&&	!java.util.regex.Pattern.matches(join(keywords, "|"), token)
				);
	}
	
	
	// Gavin - Remove duplicate element in an string array
	// Input: String[]
	// Output String[]
	public static String[] removeDuplicate(String[] arr) {
		ArrayList<String> result = new ArrayList<String>();
		int length=arr.length;
		
		for(int i=0;i<length;++i) {
			String s = arr[i];
			if(!result.contains(s))
				result.add(s);
		}
		
		return result.toArray(new String[result.size()]);
	}
	
	
	// Gavin - Extract a list of variable names from a string based on predetermined keywords
	// Input: String
	// Output: String[]
	public static String[] extractVarNames(String source) {
		ArrayList<String> list = new ArrayList<String>();
		String[] frag = source.split("\\s");
		int fragLeng = frag.length;
		String[][] vars = new String[fragLeng][];
		for(int i=0; i<fragLeng; ++i) {
			vars[i] = frag[i].split("(\'.*\')|(\".*\")|(\\W)");
			int length=vars[i].length;
			for(int j=0;j<length;++j)
				if(isVar(vars[i][j]))
					list.add(vars[i][j]);
		}
		return removeDuplicate(list.toArray(new String[list.size()]));
	}
	
	
	// Jenny - LCS 
	// Input: String[], String[]
	// Output: double
	public static double lcs(String[] token1, String[] token2) {
		int n=token1.length;
		int m=token2.length;
	      
		int[][] C = new int[n+1][m+1];
			
		/* C[i][0] = 0 for 0<=i<=n */
		for (int i = 0; i <= n; i++) {
		    	C[i][0] = 0;
		    }
			
		/* C[0][j] = 0 for  0<=j<=m */
		for (int j = 0; j <= m; j++) {
			C[0][j] = 0;
		}
		/* dynamic programming */
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {

				if (token1[i-1].equals(token2[j-1])) 
				{	
					C[i][j]=C[i-1][j-1]+1;
				}
				else if (C[i-1][j]>=C[i][j-1]) 
				{
					C[i][j]=C[i-1][j];
				}
				else 
				{
					C[i][j]=C[i][j-1];     
				}
			}
		}

		return C[n][m]*1.0/n;
	}
	
	
	// Coffee - To be rename? please clarify the functionality here
	public static String[] getTok(String str){
	      // String to be scanned to find the pattern.
	      String pattern = "(\\w+)";

	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);

	      // Now create matcher object.	      
	      Matcher m = r.matcher(str);
	     
	      List<String> allMatches= new ArrayList<String>();
	      while (m.find( )) {
	    	  //System.out.print(m.group());
	    	 
	    	  
	    	  allMatches.add(m.group());
	      }
	      return allMatches.toArray(new String[0]);
	}
	
	// Coffee, to Utility or your new comparar class? Currently move it here
    public static String replace(String str, String tar){
    	str = str.replaceAll(" ", "");
    	tar = tar.replaceAll(" ", "");
    	
    	if(str.length()>tar.length()) {
    		String tmp = tar;
    		tar = str;
    		str = tmp;
    	}
    	
        // String to be scanned to find the pattern.
        String pattern = "(\\p{Punct})";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(str);

        StringBuffer sb = new StringBuffer();

        while (m.find( )) {
        	String escape_punct = "\\\\"+m.group();
        	if(m.group().toString().equals(";")||m.group().equals("{")){
        		escape_punct += "(?:.*?)?";
        	} 	
            m.appendReplacement(sb, escape_punct);
        }
        m.appendTail(sb);
        str = sb.toString();

        // String to be scanned to find the pattern.

        String var_pattern = "(\\w+)";

        // Create a Pattern object
        Pattern var_r = Pattern.compile(var_pattern);

        // Now create matcher object.
        Vector<String> var = new Vector<String>();

        Matcher var_m = var_r.matcher(str);

        StringBuffer var_sb = new StringBuffer();

        while (var_m.find( )) {
          //System.out.print(Integer.toString(var.indexOf(var_m.group())+1)+"\n");
          if (isVar( var_m.group() )) {
              int index = var.indexOf(var_m.group());
              if ( index == -1) {
                    var.add(var_m.group());
                    var_m.appendReplacement(var_sb, "(\\\\w*?)");
              } else {
                    var_m.appendReplacement(var_sb, "\\\\"+Integer.toString(index+1));
              }
          }
        }
        var_m.appendTail(var_sb);	    
        
        String re_pattern = var_sb.toString();
        System.out.print(re_pattern+"\n");

        // Create a Pattern object
        Pattern re_r = Pattern.compile(re_pattern);
        Matcher re_m = re_r.matcher(tar);	
        re_m.find( );
        int numMatches = re_m.groupCount();
        for(int i=0; i!=numMatches; ++i) {
        	String toBeReplaced = "\\b"+re_m.group(i+1)+"\\b";
        	String middlewareReplacement = "0R"+var.get(i);
        	System.out.print(toBeReplaced+"\n");
        	tar = tar.replaceAll(toBeReplaced, middlewareReplacement);
        }
        tar = tar.replaceAll("\\b0R", "");
        return tar;
    }
    
    
    // KK & Bill split functions
    // Input: string
    // Output: ArrayList<String>
    public static ArrayList<String> splitFunction(String program){
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
