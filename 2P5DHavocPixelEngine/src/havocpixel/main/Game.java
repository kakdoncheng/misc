package havocpixel.main;

import havocpixel.Timer;
import havocpixel.entities.creatures.Player;
import havocpixel.entities.items.Inventory;
import havocpixel.gfx.Assets;
import havocpixel.gfx.Camera;
import havocpixel.gfx.ImageLoader;
import havocpixel.gui.Display;
import havocpixel.input.KeyManager;
import havocpixel.input.MouseManager;
import havocpixel.states.State;
import havocpixel.states.StateManager;
import havocpixel.util.Utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{
	private Display display;
	private BufferStrategy bs;
	private Graphics g;
	
	private StateManager sm;
	public StateManager $stateManager(){
		return sm;
	}
	public Handler hdlr;
	private KeyManager km;
	private MouseManager mm;
	public KeyManager $keyManager() {
		return km;
	}
	public MouseManager $mouseManager() {
		return mm;
	}
	private Camera camera;
	public Camera $camera() {
		return camera;
	}
	
	private Thread thread;
	
	private boolean running=false;
	private int width, height;
	private String title;
	private boolean full;
	
	public String version="v0.11alpha_javax.swing";
	public String define="a FaiRY tALe;";
	public boolean debug=false;
	public int TPSLimit=60;
	public int FPSLimit=30;
	private double scale=1.5
			;
	private int tick=0;
	private Font f;
	public Font $defaultFont(){
		return f;
	}
	public String $defaultFontName(){
		return f.getFontName();
	}
	public int $width() {
		return this.width;
	}
	public int $height() {
		return this.height;
	}
	
	//deprecated, use $currentScale instead.
	public double $renderScale(){
		return scale;
	}
	public double $currentScaleWidth(){
		if(full)
			return display.$frame().getWidth()/(double)this.width;
		else
			return $renderScale();
	}
	public double $currentScaleHeight(){
		if(full)
			return display.$frame().getHeight()/(double)this.height;
		else
			return $renderScale();
	}
	
	public Game(String title, int width, int height, boolean debug, boolean fullscreen) {
		if (title!=null) {
			this.title=title;
		} else {
			this.title=this.define;
		}
		this.width=width;
		this.height=height;
		hdlr=new Handler(this);
		km=new KeyManager();
		mm=new MouseManager();
		sm=new StateManager(hdlr);
		this.full=fullscreen;
		this.debug=debug;
	}
	
	private void init() {
		Utils.init();
		Assets.load();
		camera=new Camera(hdlr, 0, 0,scale);
		display=new Display((int)(this.width*scale),(int)(this.height*scale),this.title,full);
		display.$frame().addKeyListener(km);
		display.$frame().addMouseListener(mm);
		display.$frame().addMouseMotionListener(mm);
		display.$canvas().addMouseListener(mm);
		display.$canvas().addMouseMotionListener(mm);
		hdlr.setPlayer(new Player(0,0,hdlr));
		hdlr.setInv(new Inventory(hdlr));
		sm.loadStates();
		State.setState(hdlr.$sm().$defWorld());
		//ImageLoader.drawGradient();
		//State.setState(sm.$controls());
	}
	private void tick() {
		km.tick();
		//System.out.println(km.isAnyKeyPressed());
		if(km.keys[KeyEvent.VK_0]){
			System.exit(1);
		}
		if(!km.isAnyKeyPressed()){
			if(km.keys[KeyEvent.VK_5]){
				debug=!debug;
				//System.out.println("PRESSED");
			}
		}
		if (State.$State()!=null) {
			State.$State().tick();
		}
	}
	
	private void render() {
		bs=display.$canvas().getBufferStrategy();
		if (bs!=null) {
		} else {
			display.$canvas().createBufferStrategy(2);
			return;
		}
		g=(Graphics2D)bs.getDrawGraphics();
		//f=g.getFont();
		f=new Font("Lucida Console", Font.BOLD, 10);
		if(!display.isFullScreen())
			((Graphics2D) g).scale(scale,scale);
		else
			((Graphics2D) g).scale(display.$frame().getWidth()/(double)this.width,display.$frame().getHeight()/(double)this.height);
		g.clearRect(0, 0, width, height);
		if (State.$State()!=null) {
			State.$State().render(g);
		}
		
		g.setColor(Color.WHITE);
		g.setFont(f);
		if(debug){
			g.drawString("FPS "+$FPS()+"",// TPS "+$TPS()+" missed ticks "+missedTicks,
					2, 12);
			//g.drawString("[FPS "+(int)ae0+"]", 2, 12);
			g.drawString("[2P5DHavocPixel Engine][32bit]["+version+"]", 2, height-5);
		}
		
		//debug centerpoint
		//g.setColor(Color.BLUE);
		//g.drawRect(width/2-16, height/2-16, 32, 32);
		//g.setColor(Color.WHITE);
		
		bs.show();
		g.dispose();
	}
	float cFPS=0,dFPS=0,cTPS=0,dTPS=0,missedTicks=0;
	public float $FPS(){
		return (float)(Math.round(dFPS*10.0)/10.0);
	}
	public float $TPS(){
		return (float)(Math.round(dTPS*10.0)/10.0);
	}
	public void run(){
		init();
		// convert the time to seconds;
		double d=1.0D/TPSLimit;
		double l=(double)System.nanoTime()/1000000000.0D;
		double n=l;
		double dR=1.0D/FPSLimit;
		double nR=l;
		double lR=l;
		double maxD=0.5D;
		int sF=1;
		int msF=5;
		while(running){
			// convert the time to seconds
			n=(double)System.nanoTime()/1000000000.0D;
			nR=(double)System.nanoTime()/1000000000.0D;
			if((n-l)>maxD)
				l=n;
			//if(n-l>=d){
			if(n>=l){
				//cTPS=(float)(1.0F/(n-l));
				//if($tick()%20==0){
				//	dTPS=cTPS;
				//}
				// assign the time for the next update
				l+=d;
				//missedTicks+=(float)((n-l)/d);
				//if(missedTicks<0)
				//	missedTicks=0;
				//l=n;
				try{
					tick();
				}catch(Exception e){
					e.printStackTrace();
				}
				
				if(tick>59){
					tick=0;
				}else{
					tick++;
				}
				if(n<l){
				//if((n<l)||(sF>msF)){
					//if($tick()%2!=1){
					//render
					try{
						render();
					}catch(Exception e){
						e.printStackTrace();
					}
					cFPS=(float)(1.0F/(nR-lR));
					if($tick()%20==0)
						dFPS=cFPS;
					lR=nR;
					//}
				}else{
					sF++;
				}
			}
			//render
			//if(nR-lR>=dR){
			//	if(missedTicks<1){
			//		try{
			//			render();
			//		}catch(Exception e){
			//			e.printStackTrace();
			//		}
			//		cFPS=(float)(1.0F/(nR-lR));
			//		if($tick()%20==0)
			//			dFPS=cFPS;
			//		lR=nR;
			//	}else{
			//		missedTicks=0;
			//		//lR=nR;
			//	}
			//	//sF=1;
			//}
			else{
				double w=(l-n);
				if(w>0){
					double last=(double)System.nanoTime()/1000000000.0D;
					double now=last;
					while(true){
						now=(double)System.nanoTime()/1000000000.0D;
						if(now-last>=w){
							break;
						}else{
							Thread.yield();
						}
					}
				}
			}
		}
	stop();
	}
	/*
	long last=System.currentTimeMillis();
	long now=last;
	while(true){
		now=System.currentTimeMillis();
		if(now-last>=w){
			break;
		}
	}
	 */
	public synchronized void start() {
		if (running) {
			return;
		} else {
			running=true;
			thread=new Thread(this);
			thread.start();
			System.out.print("["+Timer.time()+"] Main Game Loop Initiated;\n");
		}
	}
	public synchronized void stop() {
		if (!running) {
			return;
		} else {
			running=false;
			try {
				thread.join();
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
			System.out.print("["+Timer.time()+"] Main Game Loop Terminated;\n");
		}
	}
	public int $tick() {
		return tick;
	}
	
	
}
