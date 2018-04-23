package supporto;

public class PathSeparator {
	public static char get() {
		//Windows path separator: \
		if(System.getProperty("os.name").contains("Windows")) return '\\';
		//Linux path separator: /
		return '/';
	}
}
