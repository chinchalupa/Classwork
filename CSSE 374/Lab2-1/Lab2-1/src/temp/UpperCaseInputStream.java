package temp;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wrightjt on 12/7/2015.
 */
public class UpperCaseInputStream extends FilterInputStream {

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int total = super.read(b, off, len);
        for(int i = off; i < off + total; ++i) {
            b[i] = (byte) Character.toUpperCase(b[i]);

        }
        return total;
    }

    @Override
    public int read() throws IOException {
        int ch = super.read();
        if(ch == -1)
            return ch;

        ch = Character.toUpperCase(ch);
        return ch;
    }

    public UpperCaseInputStream(InputStream in) {
        super(in);
    }
}
