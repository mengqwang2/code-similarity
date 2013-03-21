package edu.hkcity.cs;

public class PercentageOutput extends Output{
	public PercentageOutput(){
	}
	
	@Override 
	public void print(String name,double ratio) {
		System.out.printf("%s : %.2f%%\n",name,ratio*100);
	}
}
