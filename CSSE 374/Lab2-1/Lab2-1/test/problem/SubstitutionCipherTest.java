package problem;

import static org.junit.Assert.*;

import edu.roshulman.csse374.editor.TextEditor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SubstitutionCipherTest {
	private SubstitutionCipher substitutionCipher;
	private InputStream in;
	private OutputStream out;
	private TextEditor editor;
	private String inputFile;
	private String outputFile;


	@Before
	public void setUp() throws Exception {
		this.substitutionCipher = new SubstitutionCipher();

		this.inputFile = "./input_output/in.txt";
		this.outputFile = "./input_output/out.txt";

		this.in = new DecryptStream(this.inputFile);
		this.out = new EncryptionStream(this.outputFile);

		this.editor = new TextEditor(in, out);
	}

	@After
	public void tearDown() throws Exception {
		this.substitutionCipher = null;
	}

	@Test
	public final void testSymmetryBaseCase() {
		char plain = 'C';
		char cipher = substitutionCipher.encrypt(plain);
		char actual = substitutionCipher.decrypt(cipher);

		assertEquals("Broken symmetry", plain, actual);
	}

	@Test
	public final void testSymmetryLowerBound() {
		char plain = 'A';
		char cipher = substitutionCipher.encrypt(plain);
		char actual = substitutionCipher.decrypt(cipher);

		assertEquals("Broken symmetry", plain, actual);
	}

	@Test
	public final void testSymmetryUpperBound() {
		char plain = 'z';
		char cipher = substitutionCipher.encrypt(plain);
		char actual = substitutionCipher.decrypt(cipher);

		assertEquals("Broken symmetry", plain, actual);
	}

	@Test
	public final void testExecutes() throws Exception {
		this.editor.execute();
		assertEquals("Executes Properly", true, true);
	}

	@Test
	public final void testCorrectValues() {

	}
}
