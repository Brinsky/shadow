package shadow.test.doctool;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shadow.doctool.DocumentationTool;
import shadow.doctool.ProcessedDocumentation;
import shadow.typecheck.type.Documentation;

public class DocumentationTest 
{
	private static final Path outputDirectory = Paths.get("shadow", "test", "doctool", "docs");
	private ArrayList<String> args = new ArrayList<String>();
	
	@Before
	public void setup() throws Exception 
	{
		try {
			FileUtils.deleteDirectory(outputDirectory.toFile());
		} catch(Exception e) {}
		
		if (System.getProperty("os.name").contains("Windows")) {
			args.add("-c");
			args.add("windows.xml");
		}
		
		args.add("-o");
		System.out.println(outputDirectory.toString());
		args.add(outputDirectory.toString());
	}
	
	@After
	public void cleanup()
	{	
		// Try to remove the documentation directory
		try {
			FileUtils.deleteDirectory(outputDirectory.toFile());
		} catch(Exception e) {}
	}
	
	/**
	 * This test should only fail if the documentation tool can't properly
	 * ignore misplaced documentation comments
	 */
	@Test public void misplacedTest() throws Exception 
	{
		args.add("shadow/test/doctool/Misplaced.shadow");
		DocumentationTool.document(args.toArray(new String[] { }));
	}

	@Test public void directiveParsingTest() throws Exception
	{
		String text = 	"This method accomplishes meaningful tasks\n" +
						"@param example An important parameter that\n" +
						"is crucial to this method.\n" +
						"@throws fakeException This indicates a serious failure\n" +
						"@fake this isn't a real directive!\n" +
						"@numbers1234 isn't real either";
		Documentation documentation = new Documentation();
		documentation.addBlock(text);
		
		ProcessedDocumentation result = documentation.process();
		String mainText = result.getMainText();
		
		assertEquals("This method accomplishes meaningful tasks", mainText);
		
		/*
		// Directive type assertions
		assertEquals(DirectiveParser.DirectiveType.PARAM, directives.get(0).getType());
		assertEquals(DirectiveParser.DirectiveType.THROWS, directives.get(1).getType());
		
		// Directive content assertions
		assertEquals("example", directives.get(0).getArgument(0));
		assertEquals("An important parameter that is crucial to this method.",
				directives.get(0).getDescription());
		assertEquals("fakeException", directives.get(1).getArgument(0));
		assertEquals("This indicates a serious failure @fake this isn't a real "
				+ "directive! @numbers1234 isn't real either",
				directives.get(1).getDescription());
		*/
	}
}