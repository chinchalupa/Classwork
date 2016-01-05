package problem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import edu.roshulman.csse374.editor.TextEditor;

public class TextEditorApp {
	public static void main(String[] args) throws Exception {
		InputStream in = new DecryptStream("./input_output/out.txt");
		OutputStream out = new EncryptionStream("./input_output/out2.txt");

		TextEditor editor = new TextEditor(in, out);
		editor.execute();
	}	
}
