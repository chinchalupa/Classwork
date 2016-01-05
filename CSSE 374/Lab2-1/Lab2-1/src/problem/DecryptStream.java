package problem;

import java.io.*;

/**
 * Created by wrightjt on 12/13/2015.
 */
public class DecryptStream extends FileInputStream {


    SubstitutionCipher cipher;

    public DecryptStream(String name) throws FileNotFoundException {
        super(name);
        this.cipher = new SubstitutionCipher();
    }

    @Override
    public int read() throws IOException {
        int ch = super.read();
        if(ch == -1)
            return ch;

        System.out.println((char) ch);
        ch = this.cipher.decrypt((char) ch);
        System.out.println((char) ch);
        return ch;
    }
}
