package havocpixel.util;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Proxy extends Thread {
	final InputStream is;
	final PrintStream os;
	Proxy(InputStream is, PrintStream os) {
		this.is=is;
		this.os=os;
	}
	public void run() {
		try {
			try {
				InputStreamReader isr=new InputStreamReader(is);
				BufferedReader br=new BufferedReader(isr);
				String line=null;
				while ((line=br.readLine())!=null) {
					os.println(line);
				}
			} catch (Exception ex) {
				ex.getMessage();
			}
		} catch (Exception ex) {
			ex.getMessage();
		}finally{
			Utils.fixws("");
		}
	}
}
