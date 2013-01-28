package edu.hkcity.cs;

public abstract class Output {
	protected Comparar comparar;
	public Output(Comparar c) {
		comparar = c;
	}
	
	public abstract void display();
}
