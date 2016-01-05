package problem;

import java.util.ArrayList;

/**
 * Created by wrightjt on 12/16/2015.
 */
public class Add extends MathOp {

    public Add(String command, String application, ArrayList<String> rawArgs) {
        super(command, application, rawArgs);
    }

    @Override
    public String getResult() {
        int sum = 0;
        for(String i : this.args) {
            sum += Integer.parseInt(i);
        }
        return String.valueOf(sum);
    }
}
