package edu.hkcity.cs;

import java.lang.String;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FuncByFuncComparar extends Comparar{
	// Constructors
 
	public FuncByFuncComparar(String tar,String ori){
		super(tar, ori);
	}
	// Compare two formatted strings
	public String compare(){
		Formatter fmt = new Formatter();
		Output output = new PercentageOutput();
		
		String target = fmt.format(super.getTar());
		String original = fmt.format(super.getOri());
			
		return new String("");
	}
	
	@SuppressWarnings("null")
	private String[] getTok(String str){
	      // String to be scanned to find the pattern.
	      String pattern = "(\\w+)";

	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);

	      // Now create matcher object.
	      String[] tokArr = null;
	      Matcher m = r.matcher(str);
	      int n = m.groupCount();
	      if (m.find( )) {
	    	  for(int i = 0; i!= n; ++i) {
	    		  tokArr[i] = new String(m.group(i));
	    	  }
	      }
	      return tokArr;
	}
	
	private double checkSimilarity(String tar, String org) {
		return 0.1;
	}
	
	private double calSimilarity(double sims[], int lnt[], int length){
		return 0.1;
	}
}
 