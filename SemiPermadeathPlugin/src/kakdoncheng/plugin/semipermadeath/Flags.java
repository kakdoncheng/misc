package kakdoncheng.plugin.semipermadeath;

public class Flags {

	private static boolean semipermadeathOn=true;
	private static boolean permadeathOn=true;
	private static boolean verbose=true;
	
	public static boolean isSemipermadeathOn() {
		return semipermadeathOn;
	}
	public static void setSemipermadeathOn(boolean semipermadeathOn) {
		Flags.semipermadeathOn = semipermadeathOn;
	}
	public static boolean isPermadeathOn() {
		return permadeathOn;
	}
	public static void setPermadeathOn(boolean permadeathOn) {
		Flags.permadeathOn = permadeathOn;
	}
	public static boolean isVerbose() {
		return verbose;
	}
	public static void setVerbose(boolean verbose) {
		Flags.verbose = verbose;
	}
	

}
