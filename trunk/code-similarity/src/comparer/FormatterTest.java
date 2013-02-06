import static org.junit.Assert.*;
import edu.hkcity.cs.*;

import org.junit.Before;
import org.junit.Test;

import Comparer.comparer;


public class FormatterTest {

	private Formatter fmt;
	@Before
	public void setUp() throws Exception {
	}

	/*@Test
	public void testComparer() {
		fail("Not yet implemented");
	}*/
	 
	@Test
	public void test_compare1(){
		//Directly read test files
		fmt= new Formatter();
		String result = fmt.deleteComment("DSF/*dsfa*/SAF//nDFSDA\n");
		assertEquals(result, "DSFSAF\n");
	}
	@Test
	public void test_compare2(){
		//Directly read test files
		fmt= new Formatter();
		String result = fmt.deleteComment("DSF/*dsfa*/\n");
		assertEquals(result, "DSF\n");
	}

}
