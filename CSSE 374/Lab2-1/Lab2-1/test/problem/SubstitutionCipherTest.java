package problem;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SubstitutionCipherTest {
	private SubstitutionCipher substitutionCipher;

	@Before
	public void setUp() throws Exception {
		this.substitutionCipher = new SubstitutionCipher();
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
}
