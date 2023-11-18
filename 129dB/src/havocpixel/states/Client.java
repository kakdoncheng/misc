package havocpixel.states;

import havocpixel.main.Handler;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import sphinx.speech.api.SpeechRecognizerMain;
import edu.cmu.sphinx.result.WordResult;

public class Client extends State {

	private ArrayList<String> debug;
	private ArrayList<String> cmd;
	private String host;
	private int port;
	public Client(Handler hdlr) {
		super(hdlr);
		debug=new ArrayList<String>();
		cmd=new ArrayList<String>();
	}

	private class ClientThread extends Thread{
		private String host;
		private int port;
		public ClientThread(String host, int port){
			this.host=host;
			this.port=port;
		}
		public void run(){
			try(
					Socket echoSocket = new Socket(host, port)
			){
				PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
				debug.add("Client sucessfully connected on "+host+":"+port);
				BufferedReader in =
		                new BufferedReader(
		                    new InputStreamReader(echoSocket.getInputStream()));
		        while(true){
		        	if(cmd.size()>0){
		        		String u=cmd.remove(0);
		        		System.out.println(u);
		        		debug.add(u);
		            	out.println(u);
		            }
		        	if(in.ready()){
		        		debug.add(in.readLine());
		        	}
		        }
		     } catch (UnknownHostException ue) {
		          System.err.println("Don't know about host " + host);
		          System.exit(1);
		     } catch (IOException e) {
		          System.err.println("Couldn't get I/O for the connection to "+host);
		          System.exit(1);
		     }
		}
	}
	
	private class SpeechAPI extends SpeechRecognizerMain{
		@Override
		public void makeDecision(String speech , List<WordResult> speechWords) {
			cmd.add(speech);
		}
	}
	
	boolean init=false;
	ClientThread c;
	Thread t;
	public void start(){
		if(!init){
			try{
				host = JOptionPane.showInputDialog("Hostname: ");
				String p = JOptionPane.showInputDialog("Port: ");
				port = Integer.parseInt(p);
				init = true;
				c=new ClientThread(host,port);
				c.start();
				t = new Thread(){
					public void run(){
						new SpeechAPI();
					}
				};
				t.start();
			}catch (Exception e){
				debug.add("Failed to connect to server.");
				debug.add(e.getMessage());
			}
		}
	}
	
	private int cd=0,lim=25;
	@Override
	public void tick() {
		while(debug.size()>50){
			debug.remove(0);
		}
		if(hdlr.$km().keys[KeyEvent.VK_C]){
			start();
		}
		if(cd>0){
			cd--;
		}else{
			if(hdlr.$km().keys[KeyEvent.VK_E]){
				cmd.add("TEST");
				cd=lim;
			}
			if(hdlr.$km().keys[KeyEvent.VK_UP]){
				cmd.add("jump");
				cd=lim;
			}
			if(hdlr.$km().keys[KeyEvent.VK_RIGHT]){
				cmd.add("right");
				cd=lim;
			}
			if(hdlr.$km().keys[KeyEvent.VK_DOWN]){
				cmd.add("block");
				cd=lim;
			}
			if(hdlr.$km().keys[KeyEvent.VK_LEFT]){
				cmd.add("left");
				cd=lim;
			}
			if(hdlr.$km().keys[KeyEvent.VK_SPACE]){
				cmd.add("hit");
				cd=lim;
			}
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		//g.drawString("FPS "+Math.round(ae0*10.0)/10.0+"", 2, 12);
		for(int i=0;i<75;i++)
			g.drawString("129dB: Client.", 2, 12*i);
		g.setColor(Color.WHITE);
		g.drawString("[INFO]: 129dB: Press [C] to attempt to connect to a server.", 2, 12*2);
		g.drawString("[INFO]: 129dB: Press [S] to stop test server.", 2, 12*3);
		for(int i=0;i<debug.size();i++)
			g.drawString("129dB: "+debug.get(i), 2, 12*(4+i));
		//g.drawString("[2P5DHavocPixel Engine][32bit]["+version+"]", 2, height-5);
	}

}
