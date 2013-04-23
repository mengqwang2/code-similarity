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
    public void testCompare1() {
        String tar = "#include <iostream>\nusingnamespace std;\nvoid main() {\n\tcout << \"hello world!\";\n}";
        String ori = "#include <iostream>\nusingnamespace standard;\nint main() {\n\tcout << \"hello world!\";\n}";
        comp = new LineByLineComparar(tar, ori);
        String result = comp.compare(new Formatter(), new Output());
        assertEquals(result, "0.6");
    }

    @Test
    public void testCompare2() {
        String tar = "#include <iostream>\nusingnamespace std;\n\nvoid main() {\tint sum=0;\n\tfor(int i=1;i<=100;++i)\n\t\tsum += i;\n\tcout << \"The sum from 1 to 100 is: \" << sum << endl;\n}";
        String ori = "#include <iostream>\nusingnamespace std;\n\nvoid main() {\tint sum=0;\n\tfor(int i=1;i<=100;++i)\n\t\tsum += i;\n\tcout << \"The sum from 1 to 100 is: \" << sum << endl;\n}";
        comp = new LineByLineComparar(tar, ori);
        String result = comp.compare(new Formatter(), new Output());
        assertEquals(result, "1.0");
    }

    @Test
    public void testCompare3() {
        String tar = "#include<iosteam>\nusing namespace std;\n\nint main(){\nint x,y;//variable\ncin>>x>>y;\ncout<<x+y;/*\noutput value\n*/\nreturn 0;\n}\n";
        String ori = "#include<iosteam>\n\nvoid main(){\nint a,b;\nstd::cin>>a>>b;\nstd::cout<<a+b<<endl;\n}\n";
        comp = new LineByLineComparar(tar, ori);
        String result = comp.compare(new Formatter(), new Output());
        assertEquals(result, "0.125");
    }

    @Test
    public void testCompare4() {
        String tar = "#include<iostream>\nusing namespace std;\nint x,y;\nint main(){\nwhile(cin>>x>>y)\n\tcout<<x+y;\ncout<<endl;\n}\n";
        String ori = "#include<iostream>\nusing namespace std;\nint main(){\nint x,y;\nwhile(cin>>x)\n{\tcin>>y;\n\tcout<<x+y<<endl;\n}\n}\n";
        comp = new LineByLineComparar(tar, ori);
        String result = comp.compare(new Formatter(), new Output());
        assertEquals(result, "0.375");
    }
}
