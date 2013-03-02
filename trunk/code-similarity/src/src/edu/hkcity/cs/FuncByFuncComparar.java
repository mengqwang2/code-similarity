package edu.hkcity.cs;

import java.lang.String;

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
	
	private double checkSimilarity(String tar, String org) {
		return 0.1;
	}
	
	private double calSimilarity(double sims[], int lnt[], int length){
		return 0.1;
	}
}
