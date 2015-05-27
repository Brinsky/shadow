package shadow.test.output;

import static junit.framework.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shadow.Configuration;
import shadow.ConfigurationException;
import shadow.Job;
import shadow.Main;

public class OutputTest {
	
	// To simplify removal, every unit test executable will have the same name
	private static final String executableName = Job.properExecutableName("OutputTest");
	private static final Path executable = Paths.get("shadow", "test", executableName);

	private ArrayList<String> args = new ArrayList<String>();
	
	@Before
	public void setup() throws Exception {		
				
		args.add("-o");
		args.add(executableName);
		
		if( System.getProperty("os.name").contains("Windows") ) {
			args.add("-c");
			args.add("windows.xml");
		}
	}
	
	@After
	public void cleanup() throws IOException {
		
		// Try to remove the unit test executable
		try
		{
			Files.delete(executable);
		}
		catch(Exception e)
		{}
	}
	
	private void run(String[] programArgs, String expectedOutput) throws IOException, ConfigurationException, InterruptedException {
		run( programArgs, expectedOutput, "" );
	}
	
	private void run(String[] programArgs, String expectedOutput, String expectedError ) throws IOException, ConfigurationException, InterruptedException {
		
		// Should be initialized at this point by call to Main.run()
		Configuration config = Configuration.getConfiguration();
		
		Path fullExecutable = config.getSystemImport().resolve(executable);
		
		List<String> programCommand = new ArrayList<String>();
		programCommand.add(fullExecutable.toAbsolutePath().toString());
		
		for (String arg : programArgs)
			programCommand.add(arg);
		
		Process program = new ProcessBuilder(programCommand).start();
		
		//regular output
		BufferedReader reader = new BufferedReader(new InputStreamReader(program.getInputStream()));		
		StringBuilder builder = new StringBuilder();
		String line;
		do {
			line = reader.readLine();
			if (line != null)
				builder.append(line).append('\n');
		} while (line != null);		
		String output = builder.toString();
		assertEquals(expectedOutput, output);
		
		//error output
		reader = new BufferedReader(new InputStreamReader(program.getErrorStream()));
		builder = new StringBuilder();
		do {
			line = reader.readLine();
			if (line != null)
				builder.append(line).append('\n');
		} while (line != null);
		
		String error = builder.toString();	
		assertEquals(expectedError, error);		
		
		program.waitFor(); //keeps program from being deleted while running
	}
	
	@Test public void testTest() throws Exception {
		args.add("shadow/test/Test.shadow");
		Main.run(args.toArray(new String[] { }));
		run(new String[0],
				"true\n" + 
				"true\n" + 
				"false\n" + 
				"false\n" + 
				"true\n");
	}	
	
	@Test public void testArrayList() throws Exception {
		args.add("shadow/test/ArrayListTest.shadow");
		Main.run(args.toArray(new String[] { }));
		run(new String[] {"all", "good", "men", "shall", "perish"},
			"true\n" +
			"true\n" +
			"false\n" +
			"all\n" +
			"men\n" +
			"shall\n" +
			"[all, men, shall]\n");
	}	
	
	@Test public void testArray() throws Exception {
		args.add("shadow/test/ArrayTest.shadow");
		Main.run(args.toArray(new String[] { }));
		run(new String[0],
				"[0, zero, one, two, 4]\n" +
				"[zero, one, two]\n" +
				"[]\n" +
				"[0]\n" +
				"[0, zero]\n" +
				"[0, zero, one]\n" +
				"[0, zero, one, two]\n" +
				"[0, zero, one, two, 4]\n" +
				"[]\n" +
				"[zero]\n" +
				"[zero, one]\n" +
				"[zero, one, two]\n" +
				"[zero, one, two, 4]\n" +
				"[]\n" +
				"[one]\n" +
				"[one, two]\n" +
				"[one, two, 4]\n" +
				"[]\n" +
				"[two]\n" +
				"[two, 4]\n" +
				"[]\n" +
				"[4]\n" +
				"[]\n");			
	}	
	
	@Test public void testChild() throws Exception {
		args.add("shadow/test/ChildTest.shadow");
		Main.run(args.toArray(new String[] { }));
		run(new String[0],
				"shadow:test@ParentTest:create(\"Hello World!\")\n" + 
				"shadow:test@ChildTest:main([])\n");
	}
	
	@Test public void testConsole() throws Exception {
		args.add("shadow/test/ConsoleTest.shadow");
		Main.run(args.toArray(new String[] { }));
	}
	
	@Test public void testException() throws Exception {
		args.add("shadow/test/ExceptionTest.shadow");
		Main.run(args.toArray(new String[] { }));
	}
	
	@Test public void testFile() throws Exception {
		args.add("shadow/test/FileTest.shadow");
		Main.run(args.toArray(new String[] { }));
	}
	
	@Test public void testInterface() throws Exception {
		args.add("shadow/test/InterfaceTest.shadow");
		Main.run(args.toArray(new String[] { }));
	}
	
	@Test public void testLoop() throws Exception {
		args.add("shadow/test/LoopTest.shadow");
		Main.run(args.toArray(new String[] { }));
	}
	
	@Test public void testSort() throws Exception {
		args.add("shadow/test/SortMain.shadow");
		Main.run(args.toArray(new String[] { }));
	}
	
	@Test public void testTry() throws Exception {
		args.add("shadow/test/TryTest.shadow");
		Main.run(args.toArray(new String[] { }));
	}
	
	@Test public void testToughTry() throws Exception {
		args.add("shadow/test/ToughTry.shadow");
		Main.run(args.toArray(new String[] { }));
	}
	
	@Test public void testPrimitive() throws Exception {
		args.add("shadow/test/PrimitiveTest.shadow");
		Main.run(args.toArray(new String[] { }));
		run(new String[0],
				"5\n" + 
				"5\n" + 
				"5\n" + 
				"5\n" + 
				"5\n" +
				"8\n");
	}
	
	@Test public void testCheck() throws Exception {
		args.add("shadow/test/PrimitiveTest.shadow");
		Main.run(args.toArray(new String[] { }));
		run(new String[0],
				"5\n" + 
				"5\n" + 
				"5\n" + 
				"5\n" + 
				"5\n" +
				"8\n");
	}
	
	@Test public void testNullableWithCheck() throws Exception {
		args.add("shadow/test/NullableWithCheckTest.shadow");
		Main.run(args.toArray(new String[] { }));
		run(new String[0],
				"Recovered!\n");
	}
	
	@Test public void testNullableWithoutCheck() throws Exception {
		args.add("shadow/test/NullableWithoutCheckTest.shadow");
		Main.run(args.toArray(new String[] { }));
		run(new String[0], "",
				"shadow:standard@UnexpectedNullException\n");
	}
	
	@Test public void testSwitch() throws Exception {
		args.add("shadow/test/SwitchTest.shadow");
		Main.run(args.toArray(new String[] { }));
		run(new String[] {"bedula", "sesame"}, 
				"Default!\n" +
				"Default!\n" +
				"Three!\n" +
				"Four!\n" +
				"Five\n" +
				"Default!\n" +
				"Others!\n" +
				"Others!\n" +
				"Others!\n" +
				"Ten!\n" +
				"Welcome, bedula\n" +
				"That's the magic word!\n" +
				"separate scopes\n");
	}
	

	@Test public void testArrayCreate() throws Exception {
		args.add("shadow/test/ArrayCreateTest.shadow");
		Main.run(args.toArray(new String[] { }));
		run(new String[0], 
				"a[0]: \n" +
						"a[1]: \n" +
						"a[2]: \n" +
						"a[3]: \n" +
						"a[4]: \n" +
						"a[5]: \n" +
						"a[6]: \n" +
						"a[7]: \n" +
						"a[8]: \n" +
						"a[9]: \n" +
						"b[0]: Test\n" +
						"b[1]: Test\n" +
						"b[2]: Test\n" +
						"b[3]: Test\n" +
						"b[4]: Test\n" +
						"b[5]: Test\n" +
						"b[6]: Test\n" +
						"b[7]: Test\n" +
						"b[8]: Test\n" +
						"b[9]: Test\n" +
						"c[0]: 5\n" +
						"c[1]: 5\n" +
						"c[2]: 5\n" +
						"c[3]: 5\n" +
						"c[4]: 5\n" +
						"c[5]: 5\n" +
						"c[6]: 5\n" +
						"c[7]: 5\n" +
						"c[8]: 5\n" +
						"c[9]: 5\n" +
						"d[0]: 1\n" +
						"d[1]: 2\n" +
						"d[2]: 3\n" +
						"d[3]: 4\n" +
						"d[4]: 5\n" +
						"d[5]: 6\n" +
						"d[6]: 7\n" +
						"d[7]: 8\n" +
						"d[8]: 9\n" +
						"d[9]: 10\n" +
						"e[0][0]: 1\n" +
						"e[0][1]: 2\n" +
						"e[0][2]: 3\n" +
						"e[0][3]: 4\n" +
						"e[0][4]: 5\n" +
						"e[1][0]: 6\n" +
						"e[1][1]: 7\n" +
						"e[1][2]: 8\n" +
						"e[1][3]: 9\n" +
						"e[1][4]: 10\n" +
						"e[2][0]: 11\n" +
						"e[2][1]: 12\n" +
						"e[2][2]: 13\n" +
						"e[2][3]: 14\n" +
						"e[2][4]: 15\n" +
						"f[0,0]: 1\n" +
						"f[0,1]: 2\n" +
						"f[0,2]: 3\n" +
						"f[0,3]: 4\n" +
						"f[0,4]: 5\n" +
						"f[1,0]: 6\n" +
						"f[1,1]: 7\n" +
						"f[1,2]: 8\n" +
						"f[1,3]: 9\n" +
						"f[1,4]: 10\n" +
						"f[2,0]: 11\n" +
						"f[2,1]: 12\n" +
						"f[2,2]: 13\n" +
						"f[2,3]: 14\n" +
						"f[2,4]: 15\n");
	}
	
	@Test public void testArrayDefault() throws Exception {
		args.add("shadow/test/ArrayDefaultTest.shadow");
		Main.run(args.toArray(new String[] { }));
		run(new String[0], 
				"a[0]: Wombat\n" +
						"a[1]: Wombat\n" +
						"a[2]: Wombat\n" +
						"a[3]: Wombat\n" +
						"a[4]: Wombat\n" +
						"b[0]: 42\n" +
						"b[1]: 42\n" +
						"b[2]: 42\n" +
						"b[3]: 42\n" +
						"b[4]: 42\n");
	}
}
