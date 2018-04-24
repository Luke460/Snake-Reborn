package supporto;

public class OSdetector {
	public static char getPathSeparator() {
		//Windows path separator: \
		if(System.getProperty("os.name").contains("Windows")) return '\\';
		//Linux path separator: /
		return '/';
	}
	
	public static boolean isWindows() {
		if(System.getProperty("os.name").contains("Windows")) return true;
		return false;
	}
	
	public static String getOS() {
		return System.getProperty("os.name");
	}
}
