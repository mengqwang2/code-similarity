package edu.hkcity.cs;

import java.util.Arrays;

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
		
		Arrays.sort(tarVars);
		Arrays.sort(oriVars);
		
		int tar_length = tarVars.length;
		int ori_length = oriVars.length;
		
		String[] sortedTar = new String[tar_length];
		String[] sortedOri = new String[ori_length];
		
		for(int i=0;i<tar_length;++i)
			sortedTar[i] = tarVars[tar_length-1-i];
		for(int i=0;i<ori_length;++i)
			sortedOri[i] = oriVars[ori_length-1-i];
		
		String[] regedTar = tar.split("\\s|;|"+Utility.join(sortedTar, "|"));
		String[] regedOri = ori.split("\\s|;|"+Utility.join(sortedOri, "|"));
		
		return Utility.lcs(regedTar, regedOri);
	}
}
