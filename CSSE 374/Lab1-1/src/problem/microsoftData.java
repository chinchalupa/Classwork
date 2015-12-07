package problem;

/**
 * Created by wrightjt on 12/6/2015.
 */
public class microsoftData extends CompanyAndData {

    private String allData;
    private Parser parser;

    public microsoftData(String allData) {
        this.allData = allData;
        parser = new lineParser(this.allData);

    }

    @Override
    public void parse() {
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
