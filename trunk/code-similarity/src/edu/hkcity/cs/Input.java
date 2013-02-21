package edu.hkcity.cs;

import java.io.*;

public class Input {
	
	private String originalFile;
	private String targetFile;
	
	/*
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
	*/
	
	
	public Input(String oriName, String tarName){
		try {
			File oriFile = new File(oriName);
			File tarFile = new File(tarName);
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
			
			new LineByLineComparar(originalFile, targetFile);
			
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
}
