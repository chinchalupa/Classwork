package problem;

import java.io.*;

/**
 * Created by wrightjt on 12/13/2015.
 */
public class EncryptionStream extends FileOutputStream {

    private SubstitutionCipher cipher;

    public EncryptionStream(String name) throws FileNotFoundException {
        super(name);
        this.cipher = new SubstitutionCipher();
    }

    @Override
    public void write(int b) throws IOException {
        if(b != -1)
            b = this.cipher.encrypt((char) b);
        super.write((byte) b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        super.write(b, off, len);
        for(int i = off; i < off + len; ++i) {
            b[i] = (byte) this.cipher.encrypt((char) b[i]);
        }
    }
}
