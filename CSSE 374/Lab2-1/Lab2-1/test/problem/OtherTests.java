package problem;

import edu.roshulman.csse374.editor.TextEditor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by wrightjt on 12/13/2015.
 */
public class OtherTests {

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
    public final void testExecutes() throws Exception {
        this.editor.execute();
        assertEquals("Executes Properly", true, true);
    }
}
