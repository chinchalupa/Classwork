package problem;

/**
 * Created by wrightjt on 12/16/2015.
 */
public class Quit extends ExtensionAdapter {

    public String command;
    public String result;

    public Quit() {
        this.command = "quit";
        this.result = "Quitting...";
    }

    @Override
    public String getCommand() {
        return this.command;
    }


    @Override
    public String getResult() {
        return this.result;
    }
}
