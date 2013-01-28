package edu.hkcity.cs;

public class PercentageOutput extends Output{
	public PercentageOutput(Comparar c) {
		super(c);
	}
	
	@Override
	public void display(){
		System.out.printf( "%.2f%%%n", c.getResult() * 100);
	}
}
