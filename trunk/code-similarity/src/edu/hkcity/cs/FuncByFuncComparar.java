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
	// Constructor
	public FuncByFuncComparar() {
		super();
	}
	
	public FuncByFuncComparar(String tar,String ori){
		super(tar, ori);
		info = "Function by Function Comparar";
	}
	
	// Overload compare()
	public String compare(Formatter fmt, Output output){		
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
				// to be refact, notice that checkSimilarity() is moved to Utility and renamed as lcs()
				double sim = Utility.checkSimilarity(targetFuncList.get(i),originalFuncList.get(j));
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
	
	// 
	private double calSimilarity(double sims[], int lnt[]){
		double interSim=0;
		int totalLine=0;
		for(int i=0;i<sims.length;i++){
			interSim+=sims[i]*lnt[i];
			totalLine+=lnt[i];
		}
		return (double)interSim/totalLine;
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

	// Coffee, to Utility to your new comparar class?
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




 