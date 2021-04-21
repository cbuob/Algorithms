import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Simulates the judge.
 * How to use this class:
 * <ol>
 * 	<li>Add junit-4.11.jar and hamcrest-core-1.3.jar as library to your project (if not already done)</li>
 *  <li>Copy the test files *.input and *.output into directory testdata/</li>
 *  <li>Run the test (In Eclipse: Right click on this file -> Run As -> JUnit Test)</li>
 *  <li>The program output can be found in *.prog for debugging.</li>
 * </ol>
 * 
 * @author Sandro Felicioni (original design with subcase guessing)
 * @author Tomas Gavenciak (simplified, add program output storage and line numbers)
 */
@RunWith(Parameterized.class)
public class Judge {
	
	private static final String IN_EXT = ".input";
	private static final String OUT_EXT = ".output";
	private static final String PROGOUT_EXT = ".prog";
	private static final String TEST_DIR = "testdata";

	private String inputFile;
	private String outputFile;
	private String progFile;

	public Judge(String filename){
		this.inputFile = filename + IN_EXT;
		this.outputFile = filename + OUT_EXT;
		this.progFile = filename + PROGOUT_EXT;
	}
	
	@Test 
	public void test() throws IOException {

		// validate with test sets
		System.out.println("############################################");
		System.out.println("# Input: " + inputFile);
		System.out.println("# Expected output: " + outputFile);
		System.out.println("# Program output written to: " + progFile);

		
		// redirect io streams
		PrintStream oldOut = System.out;
		
		System.setOut(new PrintStream(progFile));
		System.setIn(new FileInputStream(inputFile));

		// run your code
		Main.main(new String[0]);
		
		// revert io streams
		System.out.close();
		System.in.close();
		System.setOut(oldOut);
		
		Scanner expectedOutputScanner = new Scanner(new FileInputStream(outputFile));
		Scanner outputScanner = new Scanner(new FileInputStream(progFile));
		
		for (int line = 1; ; line++) {
			
			if ((!expectedOutputScanner.hasNextLine()) && (!outputScanner.hasNextLine()))
				break;
			if (!outputScanner.hasNextLine()) {
				System.err.format("# Expected output has more lines than program output:\n# Expected output line %d:\n%s\n",
						line, expectedOutputScanner.nextLine());
				Assert.fail();
			}
			if (!expectedOutputScanner.hasNextLine()) {
				System.err.format("# Program output has more lines than expected:\n# Program output line %d:\n%s\n",
						line, outputScanner.nextLine());
				Assert.fail();
			}
			String expected = expectedOutputScanner.nextLine();
			String actual = outputScanner.nextLine();;

			if(!expected.equals(actual)) {
				System.err.format("# Outputs differ:\n# Expected output line %d:\n%s\n# Program output line %d:\n%s\n",
						line, expected, line, actual);
				Assert.fail();
			}
	
		}
		
		expectedOutputScanner.close();
		outputScanner.close();
		System.out.println("# Test successfull.");
	}
	
	/**
	 * Dynamically adds a JUnit test for each test file.
	 */
	@Parameters(name = "{0}")
	public static Collection<String[]> data(){
		
		List<String[]> files = new ArrayList<String[]>();
		
		
		System.out.println("Scan for test files...");
		File folder = new File(TEST_DIR);
		for (File file : folder.listFiles()) {
			if (file.isFile() && file.getName().endsWith(IN_EXT)) {
				String rawName = file.getName().substring(0, file.getName().length() - IN_EXT.length());
				files.add(0, new String[] {TEST_DIR + "/" + rawName});
				System.out.println(" - " + rawName); 
			}
		}
		
		if(files.isEmpty()){
			System.err.println("No testfiles were found. Make sure to place them into directory " + TEST_DIR);
		}
		return files;
	}
}
