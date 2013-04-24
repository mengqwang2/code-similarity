package edu.hkcity.cs.unittest;

import static org.junit.Assert.*;
import java.lang.String;
import org.junit.Before;
import org.junit.Test;

import edu.hkcity.cs.Formatter;
import edu.hkcity.cs.Output;
import edu.hkcity.cs.LineByLineComparar;

public class LineByLineComparerTest {
	private LineByLineComparar comp;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	//Test compare func by multiline inputs(5:5 lines, 3 matched)
	public void testCompare_case1() {
		String tar = "#include <iostream>\nusingnamespace std;\nvoid main() {\n    cout << \"hello world!\";\n}";
		String ori = "#include <iostream>\nusingnamespace standard;\nint main() {\n    cout << \"hello world!\";\n}";
		comp = new LineByLineComparar(tar, ori);
		String result = comp.compare(new Formatter(), new Output());
		assertEquals("0.6", result);
	}

	@Test
	//Test compare func by multiline inputs(same input)
	public void testCompare_case2() {
		String tar = "#include <iostream>\nusingnamespace std;\n\nvoid main() {    int sum=0;\n    for(int i=1;i<=100;++i)\n        sum += i;\n    cout << \"The sum from 1 to 100 is: \" << sum << endl;\n}";
		String ori = "#include <iostream>\nusingnamespace std;\n\nvoid main() {    int sum=0;\n    for(int i=1;i<=100;++i)\n        sum += i;\n    cout << \"The sum from 1 to 100 is: \" << sum << endl;\n}";
		comp = new LineByLineComparar(tar, ori);
		String result = comp.compare(new Formatter(), new Output());
		assertEquals("1.0", result );
	}

	@Test
	//Test compare func by multiline inputs(8:12 lines, 1 matched)
	public void testCompare_case3() {
		String tar = "#include<iosteam>\nusing namespace std;\n\nint main(){\nint x,y;//variable\ncin>>x>>y;\ncout<<x+y;/*\noutput value\n*/\nreturn 0;\n}\n";
		String ori = "#include<iosteam>\n\nvoid main(){\nint a,b;\nstd::cin>>a>>b;\nstd::cout<<a+b<<endl;\n}\n";
		comp = new LineByLineComparar(tar, ori);
		String result = comp.compare(new Formatter(), new Output());
		assertEquals("0.125", result);
	}

	@Test
	//Test compare func by multiline inputs(10:8 lines, 3 matched)
	public void testCompare_case4() {
		String tar = "#include<iostream>\nusing namespace std;\nint x,y;\nint main(){\nwhile(cin>>x>>y)\n    cout<<x+y;\ncout<<endl;\n}\n";
		String ori = "#include<iostream>\nusing namespace std;\nint main(){\nint x,y;\nwhile(cin>>x)\n{    cin>>y;\n    cout<<x+y<<endl;\n}\n}\n";
		comp = new LineByLineComparar(tar, ori);
		String result = comp.compare(new Formatter(), new Output());
		assertEquals("0.375", result);
	}
}
