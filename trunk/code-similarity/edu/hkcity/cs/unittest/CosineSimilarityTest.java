package edu.hkcity.cs.unittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.hkcity.cs.CosineSimilarity;

public class CosineSimilarityTest {
	private CosineSimilarity cos=new CosineSimilarity();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCompare() {
		fail("Not yet implemented");
	}

	@Test
	public void testCosineSimilarity() {
		fail("Not yet implemented");
	}

	@Test
	public void testPubCosSimliar1() {
		String str1=new String("int main() { int a=1,b=2; int c; c=a+b; if(c>2*b) cout<<c; else cout<<0; } ");
		String str2=new String("int main() { int a=1,b=2; int c; c=a+b; if(c>2*b) cout<<c; else cout<<0; } ");
		double res=cos.PubCosSimliar(str1, str2);
		String StrRes=Double.toString(res);
		assertEquals(StrRes,new String("0.9999999999999998"));
		
	}
	@Test
	//shift code
	public void testPubCosSimliar2() {
		String str1=new String("int main() { int c; int a=1,b=2; c=a+b; if(c>2*b) cout<<c; else cout<<0; } ");
		String str2=new String("int main() { int a=1,b=2; int c; c=a+b; if(c>2*b) cout<<c; else cout<<0; } ");
		double res=cos.PubCosSimliar(str1, str2);
		String StrRes=Double.toString(res);
		assertEquals(StrRes,new String("0.9999999999999998"));
		
	}
	@Test
	//first part remove
	public void testPubCosSimliar3() {
		String str1=new String("c=a+b; if(c>2*b) cout<<c; else cout<<0; } ");
		String str2=new String("int main() { int c; int a=1,b=2; c=a+b; if(c>2*b) cout<<c; else cout<<0; } ");
		double res=cos.PubCosSimliar(str1, str2);
		String StrRes=Double.toString(res);
		assertEquals(StrRes,new String("0.5619514869490164"));
		
	}
	@Test
	//last part remove
	public void testPubCosSimliar4() {
		String str1=new String("int main() { int c; int a=1,b=2; ");
		String str2=new String("int main() { int c; int a=1,b=2; c=a+b; if(c>2*b) cout<<c; else cout<<0; } ");
		double res=cos.PubCosSimliar(str1, str2);
		String StrRes=Double.toString(res);
		assertEquals(StrRes,new String("0.827170191868511"));
		
	}
}
