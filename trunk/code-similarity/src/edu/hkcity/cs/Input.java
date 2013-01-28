package edu.hkcity.cs;

import java.io.*;
import java.util.Scanner;

public class Input {
	private String filename;
	public Input(){
		filename=null;
        Scanner scanner = new Scanner(System.in);
        while(filename==null){
			System.out.print("Please enter filename:"); 
			filename=scanner.next();
			try{
				new FileInputStream(filename);
			}catch(FileNotFoundException e){
				System.out.println("File Not Found:"+filename);
				filename=null;
			}
        }
	}
	public Input(String filename){
		this.filename=filename;
		try {
			new FileInputStream(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public String getFilename(){
		return filename;
	}
	public InputStream getStream(){
		try {
			return new FileInputStream(filename);
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found:"+filename);
			return null;
		}
	}
}
