package problem;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.junit.Test;

public class CommandTest {

	/***
	 * Runs the program's main method, simulating user input,
	 * then asserting the printed output is correct.
	 *
	 * This approach is a terrible idea. See FIXME entries below.
	 *
	 * @param input: input the user would type into the program.
	 * @param expected: a subset of the text the program should print.
	 * @throws Exception
	 */
	private final void runMain(String input, String expected) throws Exception{
		// redirect stdin to read in the input string
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		// redirect stdout to save the result to a byte array
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		// run main
		CommandRunner.main(new String[]{});

		// see if the expected text is somewhere in the output
		String actual = new String(out.toByteArray());
		boolean result = actual.contains(expected);
		assertTrue("Expected to contain: "+expected + System.lineSeparator()+
				" Actually is: "+actual, result);
	}

	@Test
	public final void testSortAscending() throws Exception {
		// tests the sort extension
		// FIXME: rewrite this test so that it does not call main

		ArrayList<String> list = new ArrayList<String>();
		
		list.add("5");
		list.add("10");
		list.add("2");
		list.add("4");
		
		assertEquals(CommandRunner.getResult("problem.", "Numericascending", "Sort", list), "2, 4, 5, 10");
		
	}

	@Test
	public final void testAddition() throws Exception {
		// tests the add command
		// FIXME: rewrite this test so that it does not call main
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("5");
		list.add("10");
		list.add("2");
		list.add("4");
		
		assertEquals(CommandRunner.getResult("problem.", "Add", "MathOp", list), "21");

	}

	@Test
	public final void testFindMin() throws Exception {
		// tests the find_min command
		// FIXME: rewrite this test so that it does not call main
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("5");
		list.add("10");
		list.add("2");
		list.add("4");
		
		assertEquals(CommandRunner.getResult("problem.", "Find_min", "MathOp", list), "Found at index: 2");

	}

	@Test
	public final void testFindMinNoArgs() throws Exception {
		// tests the find_min edge case
		// FIXME: rewrite this test so that it does not call main
		ArrayList<String> list = new ArrayList<String>();
		
		
		
		assertEquals(CommandRunner.getResult("problem.", "Find_min", "MathOp", list), "");

	}

	@Test(expected=FormatException.class)
	public final void testCmdModelWithEmptyInput() throws Exception {
		// test format errors
		// FIXME: rewrite this test so that it does not call main
		if(CommandRunner.throwBlanks("")) {
			throw new FormatException("FORMAT ERROR: Empty command is not supported!");
		}
	}


	@Test(expected=FormatException.class)
	public final void testCmdModelWithNoCommand() throws Exception {
		// test format errors
		// FIXME: rewrite this test so that it does not call main
		StringTokenizer tokenizer = new StringTokenizer("MathOp");
		if(CommandRunner.throwTokens(tokenizer))
			throw new FormatException("");

	}


	@Test(expected=FormatException.class)
	public final void testCmdModelWithNoCommandAndWithExtraSpace() throws Exception {
		// test format errors
		// FIXME: rewrite this test so that it does not call main
		StringTokenizer tokenizer = new StringTokenizer("MathOp");
		if(CommandRunner.throwTokens(tokenizer))
			throw new FormatException("");
	}
}
