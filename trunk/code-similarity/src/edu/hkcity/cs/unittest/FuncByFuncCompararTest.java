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
	/*
	@Test
	public void testCheckSimilarity() {
		class FuncStub extends FuncByFuncComparar {
			public double testCheckSim(String t, String o){
				return checkSimilarity(t,o);
			}
			private double checkSimilarity(String tar, String org) {
				String []token1={"a","b","c","d"};
				String []token2={"a","b","c","d"};
				int n=token1.length;
			    int m=token2.length;
			    int[][] C = new int[n+1][m+1];
					
			    // C[i][0] = 0 for 0<=i<=n 
			    for (int i = 0; i <= n; i++) {
			    	C[i][0] = 0;
			    }
				
			    // C[0][j] = 0 for  0<=j<=m 
			    for (int j = 0; j <= m; j++) {
			        C[0][j] = 0;
			    }
			    // dynamic programming 
			    for (int i = 1; i <= n; i++) {
			    	for (int j = 1; j <= m; j++) {
			    		if (token1[i-1].equals(token2[j-1])) 
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
		FuncByFuncComparar comp = new FuncByFuncComparar();
		//String result = Double.toString(comp.Pch("aa+b", "aa+b"));
		//assertEquals(result, "1.0");
	}

	@Test
	public void testgetTokP(){
		FuncByFuncComparar comp=new FuncByFuncComparar();
		String ori=new String("public void testgetTokP(){ FuncByFuncComparar comp=new FuncByFuncComparar();");
		//String[] s=comp.getTokP(ori);
		
		//assertEquals(s,"ss");
		
		
	}
	@Test
	public void testCheckSimilarity1() {
		
		FuncByFuncComparar comp = new FuncByFuncComparar();
		String result = Double.toString(comp.Pch("aa+b", "aa+b"));
		assertEquals(result, "1.0");
	}
	
	@Test
	public void testCompare_1() {
		String ori = "int main () { printf(\"hello!\\n\");}";
		String tar = ori;
		Comparar c = new FuncByFuncComparar(tar, ori);
		assertEquals(c.compare(), "1.0");
	}*/
	
	@Test
	public void testCompare_2() {
		String ori = "int main () { x();}";
		String tar = "int main () { y();}";
		Comparar c = new FuncByFuncComparar(tar, ori);
		assertEquals(c.compare(), "0.5");
	}
	
	@Test
	public void testCompare_3() {
		String ori = "int main () { x();y();z();}";
		String tar = "int main () { y();z();x();}";
		Comparar c = new FuncByFuncComparar(tar, ori);
		assertEquals(c.compare(), "0.75");
	}
	
	@Test
	public void testCompare_4() {
		String ori = "int ha () { }";
		String tar = "int main () { y();z();}";
		Comparar c = new FuncByFuncComparar(tar, ori);
		assertEquals(c.compare(), "0.0");
	}
	
	@Test
	public void testCompare_5() {
		
		String ori = "int main () { y();z();}";
		String tar = "int ha () { }";
		Comparar c = new FuncByFuncComparar(tar, ori);
		assertEquals(c.compare(), "0.0");
	}
	
	@Test
	public void testCompare_6() {
		String ori = "int main () { y();z(); }";
		String tar = "int main () { }";
		Comparar c = new FuncByFuncComparar(tar, ori);
		assertEquals(c.compare(), "1.0");
	}
	
	@Test
	public void testCompare_7() {
		String ori = "int main () { y();z(); }";
		String tar = "int main () { a();b();c();}";
		Comparar c = new FuncByFuncComparar(tar, ori);
		assertEquals(c.compare(), "0.25");
	}
	
	/*
	@Test
	public void testGetVarNames() {
		class FuncStub extends FuncByFuncComparar {
			public String testGetVarN(String source) {
				String result="";
				String[] temp = getVarNames(source);
				int length=temp.length;
				for(int i=0;i<length;++i)
					result += temp[i];
				return result;
			}
			public boolean testIsVar(String token) {
				return isVar(token);
			}
		}

		FuncStub fc = new FuncStub();
		//String result = fc.testGetVarN("int 123var1=12342\n\n\n3333var2+234var3;");
		//assertEquals(result, "var1var2var3");
		boolean result = fc.testIsVar("abc");
		assertEquals(result, true);
	}
	*/
	@Test
	public void testBuildRegex() {
		class FuncStub extends FuncByFuncComparar {
			public String testBuildRegex(String str, String tar){
				return this.replace(str, tar);
			}
		}
		FuncStub fc = new FuncStub();
		String result = fc.testBuildRegex("for(   a=0;a!=10;a++){k=a+c+d;   a+=10;b+c+d}","for(i=0;i!=10;i++){k=i+n+m;i+=10;b+n+m}");
		assertEquals(result, "for(a=0;a!=10;a++){k=a+c+d;a+=10;b+c+d}");
	}
}
