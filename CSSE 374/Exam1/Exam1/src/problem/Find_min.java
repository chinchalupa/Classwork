package problem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by wrightjt on 12/16/2015.
 */
public class Find_min extends MathOp {

    public Find_min(String command, String application, ArrayList<String> params) {
        super(command, application, params);
    }

    @Override
    public String getResult() {
        if(this.args.size() == 0) {
            return "";
        }
        
        
        List<Integer> list = new ArrayList<Integer>();
        for(String s : this.args) {
        	list.add(Integer.parseInt(s));
        }
        return "Found at index: " + String.valueOf(list.indexOf(Collections.min(list)));
    }
}
