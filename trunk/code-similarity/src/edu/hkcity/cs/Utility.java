package edu.hkcity.cs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 *	The utilities
 */
public class Utility {
	// Predetermined keywords and operators, extensible
	protected static String[] keywords={"abstract","continue", "for", "new", "switch", "assert", "default", "goto", "package", "synchronized", "boolean", "do", "if", "private", "this", 
			"break", "double", "implements", "protected", "throw", 
			"byte", "else", "import", "public", "throws", 
			"case", "enum", "instanceof", "return", "transient", 
			"catch", "extends", "int", "short", "try", 
			"char", "final", "interface", "static", "void", 
			"class", "finally", "long", "strictfp", "volatile", 
			"const", "float", "native", "super", "while", "true", "false", "null",
			"\'.*\'", "\".*\"","\\p{Punct}\\p{Digit}*", "\\p{Space}\\p{Digit}*", "|\\p{Digit}+.*"
			};
	
	// Join elements in an array to a string with a delimiter
	// Input: String[]
	// Output: String
	public static String join(String []arr, String delimiter) {
		String result="";
		int length=arr.length;
		for(int i=0;i<length;++i)
			result += arr[i]+delimiter;
		return result;
	}
	
	// Decide whether a token is predetermined keywords
	// Input: String
	// Output: boolean
	public static boolean isVar(String token) {
		return !java.util.regex.Pattern.matches(join(keywords, "|"), token);
	}
	
	// Extract a list of variable names from a string based on predetermined keywords
	// Input: String
	// Output: String[]
	public static String[] extractVarNames(String source) {
		String[] varNames;
		varNames = source.split(join(keywords, "|"));
		return varNames;
	}
	
	// LCS 
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
	
	// To be rename, please clarify the functionality here
	public String[] getTok(String str){
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
	
	// Jenny & Benson  Cosine similarity
	//
	// Cosine similarity is a measure of similarity between two vectors 
	// of an inner product space that measures the cosine of the angle between 
	// them. The cosine of 0 is 1, and less than 1 for any other angle; the lowest 
	// value of the cosine is -1. The cosine of the angle between two vectors 
	// thus determines whether two vectors are pointing in roughly the same direction.
	// input:
	// 		two formatted string, i.e remove redundant spaces with one space
	// output:
	// 		cosine similarity of input strings;
	//
	public static double CosSimliar(String str1, String str2) 
    { 
        Map<String, int[]> vectorSpace = new HashMap<String, int[]>(); 
        int[] itemCountArray = null;
         
        //get tokens,split by space
        String strArray[] = str1.split(" "); 
        for(int i=0; i<strArray.length; ++i) 
        { 
            if(vectorSpace.containsKey(strArray[i])) 
                ++(vectorSpace.get(strArray[i])[0]); 
            else 
            { 
                itemCountArray = new int[2]; 
                itemCountArray[0] = 1; 
                itemCountArray[1] = 0; 
                vectorSpace.put(strArray[i], itemCountArray); 
            } 
        } 
         
        strArray = str2.split(" "); 
        for(int i=0; i<strArray.length; ++i) 
        { 
            if(vectorSpace.containsKey(strArray[i])) 
                ++(vectorSpace.get(strArray[i])[1]); 
            else 
            { 
                itemCountArray = new int[2]; 
                itemCountArray[0] = 0; 
                itemCountArray[1] = 1; 
                vectorSpace.put(strArray[i], itemCountArray); 
            } 
        } 
         
        //calculate
        double vector1Modulo = 0.00;
        double vector2Modulo = 0.00;
        double vectorProduct = 0.00;
        Iterator iter = vectorSpace.entrySet().iterator(); 
         
        while(iter.hasNext()) 
        { 
            Map.Entry entry = (Map.Entry)iter.next(); 
            itemCountArray = (int[])entry.getValue(); 
             
            vector1Modulo += itemCountArray[0]*itemCountArray[0]; 
            vector2Modulo += itemCountArray[1]*itemCountArray[1]; 
             
            vectorProduct += itemCountArray[0]*itemCountArray[1]; 
        } 
         
        vector1Modulo = Math.sqrt(vector1Modulo); 
        vector2Modulo = Math.sqrt(vector2Modulo); 
         
       
       return (vectorProduct/(vector1Modulo*vector2Modulo)); 
    }
	
	
}
