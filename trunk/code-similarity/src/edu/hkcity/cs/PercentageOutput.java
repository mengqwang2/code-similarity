package edu.hkcity.cs;

public class PercentageOutput extends Output{
	public PercentageOutput(Comparar comparar) {
		super(comparar);
	}
	
	@Override
	public void display(){
		System.out.printf( "%.2f%%\n", comparar.getResult() * 100);
	}
}
