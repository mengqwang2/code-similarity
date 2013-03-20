package edu.hkcity.cs;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

public class FuncByFuncComparar extends Comparar{
	public FuncByFuncComparar() {
		super();
	}
	public FuncByFuncComparar(String tar,String ori){
		super(tar, ori);
	}
	public static void main(String[] args){
		try {
	        StringBuffer fileData = new StringBuffer();
	        BufferedReader reader;
				reader = new BufferedReader(new FileReader("test.c"));
	        char[] buf = new char[1024];
	        int numRead=0;
	        while((numRead=reader.read(buf)) != -1){
	            String readData = String.valueOf(buf, 0, numRead);
	            fileData.append(readData);
	        }
	        reader.close();
	        System.out.printf("Start test \n");
	        new FuncByFuncComparar(fileData.toString(),fileData.toString()).compare();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// For KK & Bill.
	public String compare(){		
		Formatter fmt = new Formatter();
		Output output = new PercentageOutput();
		
		//String target = fmt.format(getTar());
		//String original = fmt.format(getOri());
		String target = getTar();
		String original = getOri();
		ArrayList<String> targetFuncList=fmt.splitFunction(target);
		ArrayList<String> originalFuncList=fmt.splitFunction(original);
		
		boolean oriFuncPaired[] = new boolean[originalFuncList.size()];
		double sims[] = new double [Math.max(targetFuncList.size(), originalFuncList.size())];
		int lnt[] = new int[sims.length];
		int sims_i = 0, count_j = 0;
		for(int i=0; i<targetFuncList.size(); i++) {
			double max_sim = -1;
			int max_j = -1;
			for(int j=0; j<originalFuncList.size(); j++) {
				if(oriFuncPaired[j]) continue;
				double sim = checkSimilarity(targetFuncList.get(i),originalFuncList.get(j));
				if(sim > max_sim) {
					max_j = j;
					max_sim = sim;
				}
			}
			if(max_j != -1) {
				count_j++;
				oriFuncPaired[max_j] = true;
				lnt[sims_i] = Math.max(targetFuncList.get(i).length(),originalFuncList.get(max_j).length());
			}else {
				lnt[sims_i] = targetFuncList.get(i).length();
			}
			sims[sims_i++] =  max_sim;
		}
		if(count_j != originalFuncList.size()) {
			for(int j=0; j<originalFuncList.size(); j++) {
				if(oriFuncPaired[j]) continue;
				sims[sims_i] =  0;
				lnt[sims_i++] = originalFuncList.get(j).length();
				oriFuncPaired[j] = true;
			}
		}
		
		
		String result = Double.toString(calSimilarity(sims, lnt));
		
		output.print(result);
		
		return result;	
	}
	public String[] getTokP(String str){
		return getTok(str);
	}
	private String[] getTok(String str){
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

	public double Pch(String tar,String org){
		return checkSimilarity(tar,org);
	
	}
	// LCS
	private double checkSimilarity(String tar, String org) {
		  String[] token1 = getTok(tar);
	      String[] token2 = getTok(org);
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
	
	private double calSimilarity(double sims[], int lnt[]){
		double interSim=0;
		int totalLine=0;
		for(int i=0;i<sims.length;i++){
			interSim+=sims[i]*lnt[i];
			totalLine+=lnt[i];
		}
		return (double)interSim/totalLine;
	}
	
	// Jenny & Benson  Cosine similarity
	/*
	 * Cosine similarity is a measure of similarity between two vectors 
	 * of an inner product space that measures the cosine of the angle between 
	 * them. The cosine of 0 is 1, and less than 1 for any other angle; the lowest 
	 * value of the cosine is -1. The cosine of the angle between two vectors 
	 * thus determines whether two vectors are pointing in roughly the same direction.
	 * input:
	 * 		two formatted string, i.e remove redundant spaces with one space
	 * output:
	 * 		cosine similarity of input strings;
	 */
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
	//jenny & Benson
	public double calSameVar(String []tar,String[] ori)
    {
        int count=0;
        int nTar=tar.length;
        int nOri=ori.length;
        for(int i=0;i<nTar;i++)
        {
            for(int j=0;j<nOri;j++)
             {
                if(tar[i]==ori[j])
                    count++;
            }
        }
        
        return (double)count/nTar;

    }

	
	
	
	
	// Gavin - please retain all code below at the bottom of the class, for my easy debug, thanks!
	private double calLogicSim(String tar, String ori) {
		Formatter fmt = new Formatter();
		String fmt_tar = fmt.format(tar);
		String fmt_ori = fmt.format(ori);
		
		String tar_varNames[] = getVarNames(fmt_tar);
		String ori_varNames[] = getVarNames(fmt_ori);
		
		String logicTar = join(fmt_tar.split(join(tar_varNames, "|")),"");
		String logicOri = join(fmt_ori.split(join(ori_varNames, "|")),"");
		
		// Must call revised LCS (more general version with getTok moved outside LCS)
		return checkSimilarity(logicTar, logicOri);
	}
	protected String[] getVarNames(String source) {
		String varNames[];
		varNames = source.split(join(keywords, "|"));
		return varNames;
	}
	protected String[] keywords={"abstract","continue", "for", "new", "switch", "assert", "default", "goto", "package", "synchronized", "boolean", "do", "if", "private", "this", 
			"break", "double", "implements", "protected", "throw", 
			"byte", "else", "import", "public", "throws", 
			"case", "enum", "instanceof", "return", "transient", 
			"catch", "extends", "int", "short", "try", 
			"char", "final", "interface", "static", "void", 
			"class", "finally", "long", "strictfp", "volatile", 
			"const", "float", "native", "super", "while", "true", "false", "null",
			"\'.*\'", "\".*\"","\\p{Punct}\\p{Digit}*", "\\p{Space}\\p{Digit}*"
			};
	protected String join(String []arr, String sep) {
		String result="";
		int length=arr.length;
		for(int i=0;i<length;++i)
			result += arr[i]+sep;
		return result;
	}
	
	
	
	protected boolean isVar(String token) {
		return !java.util.regex.Pattern.matches(join(keywords, "|")+"|\\p{Digit}+.*", token);
	}


	    protected String replace(String str, String tar){
	    	str = str.replaceAll(" ", "");
	    	tar = tar.replaceAll(" ", "");
	    	
	        // String to be scanned to find the pattern.
	        String pattern = "(\\p{Punct})";

	        // Create a Pattern object
	        Pattern r = Pattern.compile(pattern);

	        Matcher m = r.matcher(str);

	        StringBuffer sb = new StringBuffer();

	        while (m.find( )) {
	                m.appendReplacement(sb, "\\\\"+m.group());
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

	        // Create a Pattern object
	        Pattern re_r = Pattern.compile(re_pattern);
	        Matcher re_m = re_r.matcher(tar);	
	        re_m.find( );
	        int numMatches = re_m.groupCount();
	        for(int i=0; i!=numMatches; ++i) {
	        	tar = tar.replaceAll(re_m.group(i+1), var.get(i));
	        }
	        
	        return tar;
	    }
	}




 