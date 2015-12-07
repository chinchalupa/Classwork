package problem;

/**
 * Created by wrightjt on 12/6/2015.
 */
public class reverseAddon extends Addon {

    private AddonHandler handler;

    public reverseAddon(String toPrint) {
        this.handler = new reverseHandler(toPrint);
    }

    @Override
    public void runHandler() {
        this.handler.runHandler();
    }
}
