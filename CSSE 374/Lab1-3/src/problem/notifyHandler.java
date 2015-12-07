package problem;

/**
 * Created by wrightjt on 12/6/2015.
 */
public class notifyHandler implements AddonHandler {

    private String toPrint;

    public notifyHandler(String toPrint) {
        this.toPrint = toPrint;
    }

    @Override
    public void runHandler() {
        System.out.println(this.toPrint);
    }
}
