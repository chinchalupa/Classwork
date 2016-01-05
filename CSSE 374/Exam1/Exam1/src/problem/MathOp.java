package problem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wrightjt on 12/16/2015.
 */
public abstract class MathOp extends ExtensionAdapter {

    protected String command;
    protected String application;
    protected List<String> args;

    public MathOp(String command, String application, ArrayList<String> params) {
        this.command = command;
        this.application = application;
        this.args = params;
    }

    @Override
    public ArrayList<String> getArguments() {
        return (ArrayList) this.args;
    }

    @Override
    public String getCommand() {
        return this.command;
    }


    @Override
    public String getApplication() {
        return this.application;
    }
}
