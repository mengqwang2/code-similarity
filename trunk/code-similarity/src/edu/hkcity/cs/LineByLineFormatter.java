package edu.hkcity.cs;

import java.io.*;

public class LineByLineFormatter extends Formatter{
	public LineByLineFormatter(InputStream data) {
		super(data);
		BufferedReader br=new BufferedReader(new InputStreamReader(data));
		formattedString="";
		String lineString;
	    try {
			while((lineString=br.readLine())!=null){
				if(!lineString.equals("\n")){
					formattedString+=lineString;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
