package edu.hkcity.cs;

import java.util.ArrayList;

public class FuncByFuncComparar extends Comparar{
	// Constructor
	public FuncByFuncComparar() {
		super();
	}
	
	public FuncByFuncComparar(String tar,String ori){
		super(tar, ori);
		info = "Function by Function Comparar";
	}
	
	// Overload compare()
	public String compare(Formatter fmt, Output output){		
		//String target = fmt.format(getTar());
		//String original = fmt.format(getOri());
		String target = getTar();
		String original = getOri();
		ArrayList<String> targetFuncList=Utility.splitFunction(target);
		ArrayList<String> originalFuncList=Utility.splitFunction(original);
		
		boolean oriFuncPaired[] = new boolean[originalFuncList.size()];
		double sims[] = new double [Math.max(targetFuncList.size(), originalFuncList.size())];
		int lnt[] = new int[sims.length];
		int sims_i = 0, count_j = 0;
		for(int i=0; i<targetFuncList.size(); i++) {
			double max_sim = -1;
			int max_j = -1;
			for(int j=0; j<originalFuncList.size(); j++) {
				if(oriFuncPaired[j]) continue;
				// !! need negotiation getTok's name should be changed later
				// need refactor, notice that checkSimilarity() is moved to Utility and renamed as lcs() and the parameters have been changed
				String[] tarFunToken = Utility.getTok(targetFuncList.get(i));
				String[] oriFunToken = Utility.getTok(originalFuncList.get(j));
				
				double sim = Utility.lcs(tarFunToken, oriFunToken);
				if(sim > max_sim) {
					max_j = j;
					max_sim = sim;
				}
			}
			if(max_j != -1) {
				count_j++;
				oriFuncPaired[max_j] = true;
				lnt[sims_i] = Math.max(targetFuncList.get(i).length(),originalFuncList.get(max_j).length());
			}else {
				lnt[sims_i] = targetFuncList.get(i).length();
			}
			sims[sims_i++] =  max_sim;
		}
		if(count_j != originalFuncList.size()) {
			for(int j=0; j<originalFuncList.size(); j++) {
				if(oriFuncPaired[j]) continue;
				sims[sims_i] =  0;
				lnt[sims_i++] = originalFuncList.get(j).length();
				oriFuncPaired[j] = true;
			}
		}
		
		
		Double result = calSimilarity(sims, lnt);
		
		output.print(info, result);
		
		return Double.toString(result);	
	}
	
	private double calSimilarity(double sims[], int lnt[]){
		double interSim=0;
		int totalLine=0;
		for(int i=0;i<sims.length;i++){
			interSim+=sims[i]*lnt[i];
			totalLine+=lnt[i];
		}
		return (double)interSim/totalLine;
	}
}




 