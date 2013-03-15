package edu.hkcity.cs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		ArrayList<String> targetFuncList=splitFunction(target);
		ArrayList<String> originalFuncList=splitFunction(original);
		
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
	private ArrayList<String> splitFunction(String program){
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
	
	@SuppressWarnings("unused")

	private double calSimilarity(double sims[], int lnt[]){
		double interSim=0;
		int totalLine=0;
		for(int i=0;i<sims.length;i++){
			interSim+=sims[i]*lnt[i];
			totalLine+=lnt[i];
		}
		return (double)interSim/totalLine;
	}
}

 