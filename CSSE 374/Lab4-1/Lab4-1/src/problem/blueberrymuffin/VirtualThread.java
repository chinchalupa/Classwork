package problem.blueberrymuffin;

import java.util.Queue;

/**
 * Created by Jeremy on 1/10/2016.
 */
public class VirtualThread extends Thread implements Runnable {


    public VirtualThread(Runnable target) {
        super(target);
    }
}
