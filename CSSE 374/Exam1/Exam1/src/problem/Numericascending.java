package problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by wrightjt on 12/16/2015.
 */
public class Numericascending extends Sort {

    public Numericascending(String command, String application, ArrayList<String> params) {
        super(command, application, params);
    }

    @Override
    public String getResult() {
    	List<Integer> intArgs = new ArrayList<Integer>();
    	for(String arg: this.args) {
    		intArgs.add(Integer.parseInt(arg));
    	}
        Collections.sort(intArgs);
        String temp = intArgs.toString();
        return temp.substring(1, temp.length() - 1);
    }
}
