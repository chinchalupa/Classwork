package problem;

import java.io.File;
import java.nio.file.Path;

/**
 * Created by wrightjt on 12/6/2015.
 */
public class PDFOpener extends Files {

    private FileOpener opener;

    public PDFOpener() {
        this.opener = new openPDF();
    }

    @Override
    public Process openFile(Path file) {
        return this.opener.openFile(file);
    }
}
