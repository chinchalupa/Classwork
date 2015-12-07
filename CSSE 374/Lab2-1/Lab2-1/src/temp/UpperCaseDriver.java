package temp;


import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by wrightjt on 12/7/2015.
 */
public class UpperCaseDriver {
    public static void main(String[] args) throws Exception {
        InputStream fIn = new FileInputStream("./input_output/in.txt");
        InputStream uIn = new UpperCaseInputStream(fIn);

        int ch;
        while((ch = uIn.read()) != -1) {
            System.out.println((char) ch);
        }

        uIn.close();
    }
}
