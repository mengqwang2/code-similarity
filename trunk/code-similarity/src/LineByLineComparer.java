package comparer;

import java.lang.String;
import java.io.*;

import edu.hkcity.cs.*;

public class LineByLineComparer extends Comparer{
	// Constructors
	public LineByLineComparer() {} 
	public LineByLineComparer(String tar,String ori){
		super(tar, ori);
	}
	// Compare two formatted strings
	public void compare(Formatter formatter, Output output){
		String target = formatter.format(super.getTar());
		String original = formatter.format(super.getOri());
		
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
		
		output.display(result);
	}
}
