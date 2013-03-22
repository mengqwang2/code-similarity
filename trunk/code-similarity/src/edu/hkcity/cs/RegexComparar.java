package edu.hkcity.cs;

public class RegexComparar extends Comparar{
	// Constructor
	public RegexComparar() {
		super();
	}
	
	public RegexComparar(String tar, String ori) {
		super(tar, ori);
	}
	
	// Currently apply to entire source, may switch to function by function later
	public String compare(Formatter fmt, Output output) {
		String target = fmt.format(getTar());
		String original = fmt.format(getOri());
		
		String result = Double.toString(calRegedSim(target, original));
		
		return result;
	}
	
	//
	protected double calRegedSim(String tar, String ori) {
		
		
		String[] tarVars = Utility.extractVarNames(tar);
		String[] oriVars = Utility.extractVarNames(ori);
		
		String[] regedTar = tar.split(" |"+Utility.join(tarVars, "|"));
		String[] regedOri = ori.split(" |"+Utility.join(oriVars, "|"));
		
		return Utility.lcs(regedTar, regedOri);
	}
}
