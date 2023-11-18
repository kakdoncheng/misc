package ciphers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class VIC {
	
	private class EraserThread implements Runnable {
		private boolean stop;
		public EraserThread(String prompt) {
			System.out.print(prompt);
		}

		public void run() {
			stop = true;
			while (stop) {
				System.out.print("\010*");
				try {
					//Thread.currentThread();
					Thread.sleep(1);
				} catch(InterruptedException ie) {
					ie.printStackTrace();
				}
			}
		}

		public void stopMasking() {
			this.stop = false;
		}
	}
	
	public static ArrayList<String> readFile(String filename){
		ArrayList<String> lines = new ArrayList<String>();
		try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line = br.readLine();
		    while (line != null) {
		        lines.add(line);
		        line = br.readLine();
		    }
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		return lines;
	}
	
	public static String readLine(String prompt, boolean hide) {
		EraserThread et = new VIC().new EraserThread(prompt);
		Thread mask = new Thread(et);
		if(hide)
			mask.start();

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String password = "";

		try {
			password = in.readLine();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		if(hide)
			et.stopMasking();
		return password;
	}

	public static void main(String[] args) {
		HexCipher hex=new HexCipher();
		hex.sanityTest();
		/*
		if(args.length<2){
			System.out.println("Usage: java VIC <encode> <file>");
			return;
		}
		HexCipher hex=new HexCipher();
		String id = null,keygroup = null,date = null,phrase = null;
		//"3F","04011993","One digital signature scheme, of many, is based on RSA.","1F4B693C"
		
		ArrayList<String> keys = readFile("key");
		String[] all = keys!=null?keys.get(0).split(":"):readLine("ID:KEYGROUP:MDY: ", false).split(":");
	    id=all[0];
	    keygroup=all[1];
	    date=all[2];
	    
		phrase = readLine(": ",true);
		hex.setInfo(id,date,phrase,keygroup);
		hex.printInfo();
		hex.printKeys();
		hex.printSet();
		
		ArrayList<String> file = readFile(args[1]);
		if(args[0].length()>1){
			String in="/";
			if(file!=null){
				for(String n:file){
					in+=n+"/";
				}
				System.out.println(": "+hex.encode(in));
			}
		}else{
			System.out.println(": "+hex.decode(file.get(0)));
		}
		//*/
	}

}
