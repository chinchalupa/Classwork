package problem;

import java.nio.file.Path;

/**
 * Created by wrightjt on 12/6/2015.
 */
public interface FileOpener {
    public Process openFile(Path file);
}
