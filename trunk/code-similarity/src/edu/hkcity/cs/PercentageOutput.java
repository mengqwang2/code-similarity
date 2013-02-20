package edu.hkcity.cs;

public class PercentageOutput extends Output{
	public PercentageOutput(){
	}
	
	@Override
	public void print(Double result) {
		System.out.printf( "%.2f%%\n", result * 100);
	}
}
