package problem.blueberrymuffin;

import com.sun.xml.internal.bind.v2.util.ByteArrayOutputStreamEx;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;


/**
 * Created by Jeremy on 1/10/2016.
 */
public class VirtualThreadManagerTest {

    // TO BE NOTED: I cannot test the total number of threads because my Java unreasonably makes an additional thread for no apparent reason.

    private VirtualThreadManager virtualThreadManager = new VirtualThreadManager();
    private ByteArrayOutputStream baos;

    @Before
    public void setUp() throws Exception {
        this.virtualThreadManager = new VirtualThreadManager();
        this.baos = new ByteArrayOutputStream();

    }

    @Test
    public void testStopManager() throws Exception {
        this.virtualThreadManager.stopManager();
        assertEquals(false, this.virtualThreadManager.isRunning());
    }

    @Test
    public void testAddThread() throws Exception {
        VirtualThread virtualThread = new VirtualThread(new Runnable() {
            @Override
            public void run() {
                // Create a stream to hold the output
                PrintStream ps = new PrintStream(baos);
                // IMPORTANT: Save the old System.out!
                PrintStream old = System.out;
                // Tell Java to use your special stream
                System.setOut(ps);
                // Print some output: goes to your special stream
                System.out.println("Testing!");
                // Put things back
                System.out.flush();
                System.setOut(old);
                // Show what happened
                System.out.println("Here: " + baos.toString());
            }
        });
        this.virtualThreadManager.addThread(virtualThread);
        assertEquals(true, this.virtualThreadManager.getInactiveThreads().size() == 1);
    }

    @Test
    public void testManageThreads() throws Exception {
        this.virtualThreadManager.manageThreads();
        assertEquals("Testing!", baos.toString());

    }
}