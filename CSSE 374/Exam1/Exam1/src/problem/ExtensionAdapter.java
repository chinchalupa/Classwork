package problem;

import java.util.ArrayList;

/**
 * Created by wrightjt on 12/16/2015.
 */
public abstract class ExtensionAdapter implements Extension {
    @Override
    public String getCommand() {
        return null;
    }

    @Override
    public ArrayList<String> getArguments() {
        return null;
    }

    @Override
    public String getApplication() {
        return null;
    }

    @Override
    public String getResult() {
        return null;
    }
}
