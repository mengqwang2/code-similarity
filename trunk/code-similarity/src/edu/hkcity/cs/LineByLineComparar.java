package edu.hkcity.cs;

import java.lang.String;

public class LineByLineComparar extends Comparar{
	// Constructors
	public LineByLineComparar() {} 
	public LineByLineComparar(String tar,String ori){
		super(tar, ori);
	}
	// Compare two formatted strings
	public String compare(){
		Formatter fmt = new Formatter();
		Output output = new PercentageOutput();
		
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
		
		String result = Double.toString(count/tarLength);
		
		output.print(result);
		
		return result;
	}
}
