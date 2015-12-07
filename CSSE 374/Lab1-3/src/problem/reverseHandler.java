package problem;

/**
 * Created by wrightjt on 12/6/2015.
 */
public class reverseHandler implements AddonHandler {

    private String reverse;

    public reverseHandler(String toPrint) {
        this.reverse = new StringBuilder(toPrint).reverse().toString();
    }

    @Override
    public void runHandler() {
        System.out.println(this.reverse);
    }
}
