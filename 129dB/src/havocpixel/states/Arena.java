package havocpixel.states;

import havocpixel.entities.Character;
import havocpixel.entities.EntityManager;
import havocpixel.entities.Stage;
import havocpixel.gfx.ImageLoader;
import havocpixel.gfx.Textures;
import havocpixel.main.Handler;
import havocpixel.util.Utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Arena extends State{
	private boolean running=false;
		private ArrayList<String> debug;
		private ArrayList<Client> list;
		private Server se;
		private long last=System.currentTimeMillis();
		private int getTime(){
			int u=(90000-(int)(System.currentTimeMillis()-last))/1000;
			return u>0?u:0;
		}
		public Arena(Handler hdlr) {
			super(hdlr);
			debug=new ArrayList<String>();
			list=new ArrayList<Client>();
		}
		
		public void startGame(){
			s=new Stage(hdlr);
			em=new EntityManager(hdlr);
			em.addEntity(s);
			em.addEntity(new Character(hdlr,0,200,100));
			em.addEntity(new Character(hdlr,1,hdlr.$game().$width()-175-200,100));
			running=true;
		}
		public void reset(){
			em.removeChar();
			em.addEntity(new Character(hdlr,0,200,100));
			em.addEntity(new Character(hdlr,1,hdlr.$game().$width()-175-200,100));
			count=3;
			ti=30;
			last=System.currentTimeMillis();
		}
		
		public void start(){
			se=new Server(8080);
			se.start();
		}

		private class Server extends Thread{
			private int port;
			public Server(int port){
				this.port=port;
			}
			public void run(){
				try {
					debug.add("Server hosting on "+InetAddress.getLocalHost().getHostAddress()+":"+port+";");
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try (
			            ServerSocket serverSocket=new ServerSocket(port);
			    ){
					Socket clientSocket;
					serverSocket.setSoTimeout(0);
					while(true){
						clientSocket=serverSocket.accept();
						Client c=new Client(clientSocket, port, list.size());
						c.start();
						list.add(c);
					}
					
			    }catch (IOException e) {
			    	debug.add("Exception caught when trying to listen on port "+port+" or listening for a connection");
			    	debug.add(e.getMessage());
			    }
			}
		}
		private class Client extends Thread{
			private int id,port;
			private Socket clientSocket;
			private PrintWriter out;
			private BufferedReader in;
			public Client(Socket socket,int port,int id){
				this.id=id;
				this.port=port;
				clientSocket=socket;
			}
			public void run(){
				try{
					out=new PrintWriter(clientSocket.getOutputStream(), true);                   
		            in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					debug.add("Client("+id+") connected on port "+port);
					//System.out.println("Server listening on port "+port);
			        String inputLine;
			        while ((inputLine = in.readLine()) != null) {
			            //out.println("Server on port "+port+": "+inputLine);
			        	String te=((id%2)!=0)?"RIGHT PATRICK":"LEFT PATRICK";
			        	out.println("Your Team: "+te);
			            if(!inputLine.equals("READY"))
			            	debug.add("Client("+id+") on port "+port+": "+inputLine);
			            if(running){
			            	if(id%2==0){
				            	if(inputLine.toLowerCase().equals("jump")){
				            		em.$char(1).jump();
					            }else if(inputLine.toLowerCase().equals("left")){
					            	em.$char(1).move(1);
					            }else if(inputLine.toLowerCase().equals("block")){
					            	em.$char(1).block();
					            }else if(inputLine.toLowerCase().equals("right")){
					            	em.$char(1).move(0);
					            }else if(inputLine.toLowerCase().equals("punch")||inputLine.toLowerCase().equals("hit")||inputLine.toLowerCase().equals("attack")){
					            	em.$char(1).attack();
					            }else if(inputLine.toLowerCase().equals("<unk>")){
					            	em.$char(1).doNothing();
					            }
				            }else{
				            	if(inputLine.toLowerCase().equals("jump")){
				            		em.$char(2).jump();
					            }else if(inputLine.toLowerCase().equals("left")){
					            	em.$char(2).move(1);
					            }else if(inputLine.toLowerCase().equals("block")){
					            	em.$char(2).block();
					            }else if(inputLine.toLowerCase().equals("right")){
					            	em.$char(2).move(0);
					            }else if(inputLine.toLowerCase().equals("punch")||inputLine.toLowerCase().equals("hit")||inputLine.toLowerCase().equals("attack")){
						            	em.$char(2).attack();
					            }else if(inputLine.toLowerCase().equals("<unk>")){
					            	em.$char(2).doNothing();
					            }
				            }
			            }
			            
			        }
			    }catch (IOException e) {
			    	debug.add("Exception caught when trying to listen on port "+port+" or listening for a connection");
			    	debug.add(e.getMessage());
			    }
			}
			public void write(String in){
				out.println(in);
			}
		}
		
		int count=3,ti=30;
		@Override
		public void tick() {
			while(debug.size()>50){
				debug.remove(0);
			}
			if(hdlr.$km().keys[KeyEvent.VK_ENTER]){
				startGame();
			}else if(hdlr.$km().keys[KeyEvent.VK_R]){
				reset();
			}else if(hdlr.$km().keys[KeyEvent.VK_S]){
				if(se!=null){
					debug.add("Stopping Server.");
					try {
						se.join();
					} catch (InterruptedException ie) {
						debug.add(ie.getMessage());
					}
				}else{
					debug.add("Server does not exist.");
				}
			}else if(hdlr.$km().keys[KeyEvent.VK_M]){
				if(se!=null){
					debug.add("Sending msg to all clients.");
					for(int i=0;i<list.size();i++){
						Client c=list.get(i);
						if(c!=null){
							c.write("[SERVER]: WARNING");
						}
					}
					
				}else{
					debug.add("Server does not exist.");
				}
			}
			if(running){
				if(!(count>0)){
					if(getTime()==0){
						State.$State().em.$char(1).damage(10000);
						State.$State().em.$char(2).damage(10000);
					}
					em.tick();
				}else{
					last=System.currentTimeMillis()+900;
					em.updateAll();
				}
			}
		}

		BufferedImage back=new Textures(ImageLoader.loadImage("/txr/Tgkpf.png")).crop();
		//Animation bg=new Animation(hdlr,100,Assets.background);
		BufferedImage[] co={
				new Textures(ImageLoader.loadImage("/txr/fight.png")).crop(),
				new Textures(ImageLoader.loadImage("/txr/1.png")).crop(),
				new Textures(ImageLoader.loadImage("/txr/2.png")).crop(),
				new Textures(ImageLoader.loadImage("/txr/3.png")).crop()
		};
		@Override
		public void render(Graphics g) {
			g.setColor(Color.GRAY);
			//g.drawString("FPS "+Math.round(ae0*10.0)/10.0+"", 2, 12);
			for(int i=0;i<75;i++)
				g.drawString("129dB: Arena.", 2, 12*i);
			g.setColor(Color.WHITE);
			g.drawString("[INFO]: 129dB: Press [ENTER] when all the intended players have joined.", 2, 12*2);
			g.drawString("[INFO]: 129dB: Press [S] to stop test server.", 2, 12*3);
			for(int i=0;i<debug.size();i++)
				g.drawString("129dB: "+debug.get(i), 2, 12*(4+i));
			//g.drawString("[2P5DHavocPixel Engine][32bit]["+version+"]", 2, height-5);
			if(running){
				//bg.tick();
				g.drawImage(back,0,0,hdlr.$game().$width(),hdlr.$game().$height(),null);
				em.render(g);
				g.setFont(new Font("San Serif",Font.BOLD,64));
				if(getTime()>9){
					Utils.drawStringWithOutline(g,(getTime()>9)?""+getTime():"0"+getTime(), hdlr.$game().$width()/2-38, 64,Color.YELLOW);
				}else{
					Utils.drawStringWithOutline(g,(getTime()>9)?""+getTime():"0"+getTime(), hdlr.$game().$width()/2-38, 64, (hdlr.$game().$tick()<30)?Color.YELLOW:Color.RED);
				}
				if(count>-1){
					Utils.drawTranslucentImage(co[count],0,0,(count>0)?ti/30.0f:1.0f,hdlr.$game().$width(),hdlr.$game().$height(),g);
					if(ti>0){
						ti--;
					}else{
						count--;
						ti=30;
					}
				}
			}
		}
}
