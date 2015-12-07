package problem;

/**
 * Created by wrightjt on 12/6/2015.
 */
public class notifyAddon extends Addon {

    private AddonHandler handler;

    public notifyAddon(String toPrint) {
        this.handler = new notifyHandler(toPrint);
    }

    @Override
    public void runHandler() {
        this.handler.runHandler();
    }
}
