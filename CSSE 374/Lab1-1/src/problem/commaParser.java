package problem;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by wrightjt on 12/6/2015.
 */
public class commaParser implements Parser {

    private String stringToParse;
    private String data;
    private String company;

    public commaParser(String stringToParse) {
        this.stringToParse = stringToParse;
    }

    @Override
    public void parse() {
        String[] alldata = this.stringToParse.split("[[\\r?\\n,]]+");
        this.company = alldata[0];
        ArrayList<String> listdata = new ArrayList<>(Arrays.asList(alldata));
        listdata.remove(0);
        this.data = "";
        for(String s : listdata) {
            if(s != null) {
                this.data += s + "\n";
            }
        }
    }

    @Override
    public String getCompany() {
        return this.company;
    }

    @Override
    public String getData() {
        return this.data;
    }
}
