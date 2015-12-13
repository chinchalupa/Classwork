package problem;

/**
 * This class provides utility methods to run the application 
 * in either test or release mode.
 * 
 * In test mode, user can supply the default operating system
 * to get corresponding look and feel.
 *  
 * @author Chandan R. Rupakheti (chandan.rupakheti@rose-hulman.edu)
 */
public class Configuration {
	public static final String WINDOWS = "Windows";
	public static final String LINUX = "Ubuntu";
	
	private static boolean test = false;
	private static String defaultOS = LINUX;
	
	public static void setTestMode(boolean mode) {
		test = mode;
	}

	public static void setDefaultOS(String os) {
		defaultOS = os;
	}
	

	public static String getOS() {
		if(test)
			return defaultOS.toLowerCase();
		else
			return System.getProperty("os.name").toLowerCase();
	}
}
