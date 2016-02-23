package problem.blueberrymuffin;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Jeremy on 1/10/2016.
 */
public class VirtualThreadManager {
    private ArrayList<Thread> runningThreads;
    private ArrayList<Thread> inactiveThreads;

    private boolean isRunning;

    public VirtualThreadManager() {
        this.runningThreads = new ArrayList<>(3);
        this.inactiveThreads = new ArrayList<>();
        this.isRunning = true;
    }

    public void stopManager() {
        this.isRunning = false;
    }

    public void addThread(VirtualThread virtualThread) {
        this.inactiveThreads.add(virtualThread);
    }

    public void manageThreads() throws Exception {
        System.out.println("Initial count: " + Thread.activeCount());
        while(isRunning) {

            if(this.runningThreads.size() < 3 && !this.inactiveThreads.isEmpty()) {
                Thread thread = this.inactiveThreads.remove(0);
                this.runningThreads.add(thread);
                thread.start();
                System.out.println("Thread count: " + Thread.activeCount());
            }

            for(int i = 0; i < this.runningThreads.size(); i++) {
                if(!this.runningThreads.get(i).isAlive()) {
                    this.runningThreads.remove(i);
                }
            }
        }
    }


    public ArrayList<Thread> getRunningThreads() {
        return runningThreads;
    }

    public ArrayList<Thread> getInactiveThreads() {
        return inactiveThreads;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
