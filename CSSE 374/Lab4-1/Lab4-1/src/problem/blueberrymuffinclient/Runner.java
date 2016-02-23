package problem.blueberrymuffinclient;

import problem.blueberrymuffin.VirtualThread;
import problem.blueberrymuffin.VirtualThreadManager;

import java.util.ArrayList;

/**
 * Created by Jeremy on 1/10/2016.
 */
public class Runner {

    public static ArrayList<VirtualThread> virtualThreads;

    public static void main(String[] args) throws Exception{
        System.out.println(Thread.activeCount());
        VirtualThreadManager virtualThreadManager = new VirtualThreadManager();
        for(int i = 0; i < 9; i++)
            virtualThreadManager.addThread(generateThread(i));
        virtualThreadManager.manageThreads();
    }

    public static VirtualThread generateThread(int threadIdentifier) {
        VirtualThread vt = new VirtualThread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 5; i++) {
                    System.out.println("Hello from thread-" + (threadIdentifier));
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return vt;
    }
}
