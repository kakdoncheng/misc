package havocpixel.main;

import havocpixel.Timer;
import havocpixel.gfx.Assets;
import havocpixel.gfx.Camera;
import havocpixel.gui.Display;
import havocpixel.input.KeyManager;
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
	public KeyManager $keyManager() {
		return km;
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
	
	public String version="d4w53f129alpha_rel2";
	public String define="129dB";
	public int FPSLimit=60;
	private double scale=0.75
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
	public double $renderScale(){
		return scale;
	}
	public Game(String title, int width, int height, boolean f) {
		if (title!=null) {
			this.title=title;
		} else {
			this.title=this.define;
		}
		this.width=width;
		this.height=height;
		hdlr=new Handler(this);
		km=new KeyManager();
		sm=new StateManager(hdlr);
		full=f;
	}
	
	public void toggleFullScreen(){
		new Game(null,1150,650,!full).start();
		display.$frame().setVisible(false);
		display.$canvas().getBufferStrategy().dispose();
		this.stop();
	}
	
	private void init() {
		Utils.init();
		Assets.load();
		camera=new Camera(hdlr, 0, 0,scale);
		display=new Display((int)(this.width*scale),(int)(this.height*scale),this.title,full);
		display.$frame().addKeyListener(km);
		sm.loadStates();
		State.setState(hdlr.$sm().$menu());
		//ImageLoader.drawGradient();
		//State.setState(sm.$controls());
	}
	private void tick() {
		km.tick();
		if(km.keys[KeyEvent.VK_ESCAPE]){
			System.exit(1);
		}
		if (State.$State()!=null) {
			State.$State().tick();
		}
	}
	private void render() {
		bs=display.$canvas().getBufferStrategy();
		if (bs!=null) {
		} else {
			display.$canvas().createBufferStrategy(3);
			return;
		}
		g=(Graphics2D)bs.getDrawGraphics();
		f=g.getFont();
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
		g.drawString("FPS "+Math.round(ae0*10.0)/10.0+"", 2, 12);
		//g.drawString("[FPS "+(int)ae0+"]", 2, 12);
		g.drawString("[2P5DHavocPixel Engine][32bit]["+version+"]", 2, height-5);
		bs.show();
		g.dispose();
	}
	float ae=0,ae0=0;
	public float $FPS(){
		return ae;
	}
	public void run(){
		init();
		// convert the time to seconds;
		double d=1.0D/FPSLimit;
		double l=(double)System.nanoTime()/1000000000.0D;
		double n=l;
		double lR=l;
		double maxD=0.5D;
		int sF=1;
		int msF=5;
		while(running){
			// convert the time to seconds
			n=(double)System.nanoTime()/1000000000.0D;
			if((n-l)>maxD)
				l=n;
			if(n>=l){
				// assign the time for the next update
				l+=d;
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
				if((n<l)||(sF>msF)){
					try{
						render();
					}catch(Exception e){
						e.printStackTrace();
					}
					ae=(float)(1.0F/(n-lR));
					if($tick()%20==0)
						ae0=ae;
					lR=n;
					sF=1;
				}else{
					sF++;
				}
			}else{
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
				/*
				int w=(int)(1000.0D*(l-n));
				if(w>0){
					try{
						Thread.sleep(w);
					}catch(InterruptedException e){
						e.printStackTrace();;
					}//
				}//*/
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
