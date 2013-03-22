package edu.hkcity.cs;

import java.lang.String;

public class LineByLineComparar extends Comparar{
	// Constructors
	public LineByLineComparar() {
		super();
	}
	
	public LineByLineComparar(String tar,String ori){
		super(tar, ori);
		info = "LineByLine Comparar";
	}
	
	// Overload compare()
	public String compare(Formatter fmt, Output output){
		String target = fmt.format(super.getTar());
		String original = fmt.format(super.getOri());
		
		String[] tarStr = target.split("\n");
		String[] oriStr = original.split("\n");
		
		int tarLength = tarStr.length;
		int oriLength = oriStr.length;
		
		double count=0;
		
		for(int i=0; i!=tarLength;++i){
			if(i>=oriLength)
				break;
			if(tarStr[i].equals(oriStr[i])){
				++count;
			}
		}
		
		Double result = count/tarLength;
		
		output.print(info, result);
		
		return Double.toString(result);
	}
}