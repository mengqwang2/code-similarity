package edu.hkcity.cs.unittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.hkcity.cs.Comparar;
import edu.hkcity.cs.FuncByFuncComparar;

public class FuncByFuncCompararTest {
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCheckSimilarity() {
		class FuncStub extends FuncByFuncComparar {
			public double testCheckSim(String t, String o){
				return checkSimilarity(t,o);
			}
			private double checkSimilarity(String tar, String org) {
				String []token1={"abc","abd","qwe","qaz"};
				String []token2={"abd","sdf","ghj","qaz"};
				int n=token1.length;
			    int m=token2.length;
			    int[][] C = new int[n+1][m+1];
					
			    /* C[i][0] = 0 for 0<=i<=n */
			    for (int i = 0; i <= n; i++) {
			    	C[i][0] = 0;
			    }
				
			    /* C[0][j] = 0 for  0<=j<=m */
			    for (int j = 0; j <= m; j++) {
			        C[0][j] = 0;
			    }
			    /* dynamic programming */
			    for (int i = 1; i <= n; i++) {
			    	for (int j = 1; j <= m; j++) {
			    		if (token1[i-1]== token2[j-1]) 
			            {
			    			C[i][j]=C[i-1][j-1]+1;
			            }
			            else if (C[i-1][j]>=C[i][j-1]) 
			            {
			            	C[i][j]=C[i-1][j];
			            }
			            else 
			            {
			            	C[i][j]=C[i][j-1];     
			            }
			        }
			    }
			    return C[n][m]*1.0/n;
			}
		};
		FuncStub comp = new FuncStub();
		String result = Double.toString(comp.testCheckSim("", ""));
		assertEquals(result, "0.5");
	}
	
	@Test
	public void testCompare_1() {
		String ori = "int main () { printf(\"hello!\\n\");}";
		String tar = ori;
		Comparar c = new FuncByFuncComparar(tar, ori);
		assertEquals(c.compare(), "1.0");
	}

}
