package problem;

import java.io.File;
import java.nio.file.Path;

/**
 * Created by wrightjt on 12/6/2015.
 */
public abstract class Files implements FileOpener {

    @Override
    public abstract Process openFile(Path file);
}
