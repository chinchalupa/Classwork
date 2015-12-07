package problem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by wrightjt on 12/6/2015.
 */
public class openHTMLFile implements FileOpener {
    @Override
    public Process openFile(Path file) {
        ProcessBuilder pBuilder = null;
        String command = null;
        String arg = null;

        String filename = file.toString();

        command = "explorer";
        arg = filename;

        pBuilder = new ProcessBuilder(command, arg);


        Process process = null;
        try {
            process = pBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return process;
    }
}
