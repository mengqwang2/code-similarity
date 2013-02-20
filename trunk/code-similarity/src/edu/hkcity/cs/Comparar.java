package edu.hkcity.cs;

import java.lang.String;

import edu.hkcity.cs.*;

public abstract class Comparar{
	// Constructors
	public Comparar() {} 
	public Comparar(String tar,String ori){
		this.setTar(tar);
		this.setOri(ori);
	}
	// Compare two formatted strings
	public abstract void compare();
	
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
}
