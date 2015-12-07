package problem;

import static org.junit.Assert.*;

import org.junit.Test;

public class DataStandardizerTest {
	@Test
	public final void testParseGoogle() {
		DataStandardizer unifier = new DataStandardizer();
		unifier.parse("./input_output/io.gogl");
		
		String expected = "google";
		String actual = unifier.getCompany();
		assertEquals(expected, actual);

		StringBuffer buffer = new StringBuffer();
		buffer.append("geo1 : 100\n");
		buffer.append("geo2 : 450\n");
		buffer.append("geo3 : 90\n");
		buffer.append("geo4 : 750");

		expected = buffer.toString().trim();
		actual = unifier.getData().trim();
		assertEquals(expected, actual);
	}

	@Test
	public final void testParseMicrosoft() {
		DataStandardizer unifier = new DataStandardizer();
		unifier.parse("./input_output/io.ms");
		
		String expected = "microsoft";
		String actual = unifier.getCompany();
		assertEquals(expected, actual);

		StringBuffer buffer = new StringBuffer();
		buffer.append("ms1 : 100\n");
		buffer.append("ms2 : 450\n");
		buffer.append("ms3 : 90\n");
		buffer.append("ms4 : 750");

		expected = buffer.toString().trim();
		actual = unifier.getData().trim();
		assertEquals(expected, actual);
	}
}
