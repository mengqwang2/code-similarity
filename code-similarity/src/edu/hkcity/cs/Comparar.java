package edu.hkcity.cs;

import java.lang.String;

public abstract class Comparar{
	// Constructors
	public Comparar() {} 
	public Comparar(String tar,String ori){
		this.setTar(tar);
		this.setOri(ori);
		info = "Abstract Comparar";
	}
	// Compare two formatted strings
	public abstract String compare(Formatter fmt, Output output);
	
	private void setTar(String tar) {
		this.tar = tar;
	}
	protected String getTar() {
		return tar;
	}
	private void setOri(String ori) {
		this.ori = ori;
	}
	protected String getOri() {
		return ori;
	}

	private String tar;
	private String ori;
	protected String info;
	
}
