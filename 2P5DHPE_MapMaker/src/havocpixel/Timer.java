package havocpixel;
import java.util.Calendar;
import java.util.GregorianCalendar;
public class Timer {
	public Timer() {}
	private static int m, d, y, sec, min, hr;
	private static void update() {
	      GregorianCalendar date = new GregorianCalendar();
	      m=date.get(Calendar.MONTH);
	      d=date.get(Calendar.DATE);
	      y=date.get(Calendar.YEAR);
	      sec=date.get(Calendar.SECOND);
	      min=date.get(Calendar.MINUTE);
	      hr=date.get(Calendar.HOUR);
	}
	public static String time() {
		update();
		String k=hr+":"+min+":"+sec;
		return k;
	}
	public static String date() {
		update();
		String k=m+"."+d+"."+y;
		return k;
	}
}
