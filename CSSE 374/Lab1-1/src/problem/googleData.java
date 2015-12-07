package problem;

import puzzle.LinePrinter;

/**
 * Created by wrightjt on 12/6/2015.
 */
public class googleData extends CompanyAndData {

    private String allData;
    private Parser parser;

    public googleData(String allData) {
        System.out.println("Google data made");
        this.allData = allData;
        parser = new lineParser(this.allData);
    }

    @Override
    public void parse() {
        System.out.println("To Parse Now");
        parser.parse();
    }

    @Override
    public String getCompany() {
        return parser.getCompany();
    }

    @Override
    public String getData() {
        return parser.getData();
    }
}
