package problem;

import java.util.ArrayList;

/**
 * Created by wrightjt on 12/16/2015.
 */
public interface Extension {

    public String getCommand();

    public ArrayList<String> getArguments();

    public String getApplication();

    public String getResult();
}
