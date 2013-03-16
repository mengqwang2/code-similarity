package edu.hkcity.cs;

import java.io.*;
import java.util.Scanner;

public class Input {
	private String originalFileName;
	private String targetFileName;
	private String originalFile;
	private String targetFile;
	
	public Input(){
		originalFileName=getFilename("Please enter original name:");
		targetFileName=getFilename("Please enter target name:");
	}
	public Input(String oriName, String tarName){
		originalFileName=oriName;
		targetFileName=tarName;
	}

	private String getFilename(String msg) {
        Scanner scanner = new Scanner(System.in);
        String fileName=null;
        while(fileName==null){
			System.out.print(msg); 
			fileName=scanner.next();
			try{
				new FileInputStream(fileName);
			}catch(FileNotFoundException e){
				System.out.println("File Not Found:"+fileName);
				fileName=null;
			}
        }
        return fileName;
	}
	
	public void getInput() {
		try {
			File oriFile = new File(originalFileName);
			File tarFile = new File(targetFileName);
			InputStream oriStream = new FileInputStream(oriFile);
			InputStream tarStream = new FileInputStream(tarFile);
			byte[] oriBuf = new byte[(int) oriFile.length()];
			byte[] tarBuf = new byte[(int) tarFile.length()];
			
			oriStream.read(oriBuf);
			tarStream.read(tarBuf);
			
			oriStream.close();
			tarStream.close();
			
			originalFile = new String(oriBuf);
			targetFile = new String(tarBuf);
			
			new LineByLineComparar(originalFile, targetFile).compare();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getOriginalFile() {
		return originalFile;
	}

	public void setOriginalFile(String originalFile) {
		this.originalFile = originalFile;
	}

	public String getTargetFile() {
		return targetFile;
	}

	public void setTargetFile(String targetFile) {
		this.targetFile = targetFile;
	}
	
	public static void main(String[] args){
		Input in=new Input();
		in.getInput();
	}
}
