package edu.hkcity.cs;

public class PercentageOutput extends Output{
	public PercentageOutput(){
	}
	
	@Override 
	public void print(String result) {
		System.out.printf("%.2f%%\n",Double.parseDouble(result)*100);
	}
}
